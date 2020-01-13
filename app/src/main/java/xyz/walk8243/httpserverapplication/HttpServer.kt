package xyz.walk8243.httpserverapplication

import java.io.BufferedInputStream
import java.io.BufferedOutputStream
import java.io.OutputStream
import java.net.InetSocketAddress
import java.net.ServerSocket
import java.util.*
import java.util.logging.Logger


class HttpServer {
    companion object {
        private val logger = Logger.getLogger("HttpServer")
        private const val REQUEST_CAPACITY = 1 * 1024 * 1024 // 1MB

        private const val SP: Byte = 0x20
        private const val LF: Byte = 0x0a
        private const val CR: Byte = 0x0d
    }

    private lateinit var server: ServerSocket
    private var inputStream: BufferedInputStream? = null
    private var outputStream: OutputStream? = null

    constructor() {
        createServer(8001)
    }
    constructor(port: Int) {
        createServer(port)
    }

    private fun createServer(port: Int) {
        server = ServerSocket()
        server.bind(InetSocketAddress(port))
    }

    fun start() {
        Thread(Runnable {
            logger.info("accept前")
            val socket = server.accept()
            logger.info("accept後")

            logger.info("InetAddress.HostAddress: " + socket.inetAddress.hostAddress)
            logger.info("LocalAddress.HostAddress: " + socket.localAddress.hostAddress)

            inputStream = BufferedInputStream(socket.getInputStream())
            outputStream = BufferedOutputStream(socket.getOutputStream())

            startInputThread()
        }).start()
    }

    private fun startInputThread() {
        Thread(Runnable {
            while (true) {
                logger.info("loop start.")
                val buf = ByteArray(REQUEST_CAPACITY)
                if (inputStream == null) {
                    break
                }

                val size = inputStream!!.read(buf)
                if (size > 0) {
                    val chunk = Arrays.copyOfRange(buf, 0, size)
                    logger.info(String(chunk))
                    output()
                    outputStream!!.flush()
                } else {
                    break
                }
            }
        }).start()
    }

    private fun output() {
        val text = "output"

        output(text)
    }
    private fun output(text: String) {
        val lineLastStr = String(byteArrayOf(CR, LF))
        val builder = StringBuilder()
//        builder.append(String.format("HTTP/1.1 %d", 200)).append(lineLastStr)
//        builder.append("Content-Type: text/plain; charset=UTF-8").append(lineLastStr)
//        builder.append(String.format("Content-Length: %d", text.toByteArray().size)).append(lineLastStr)
//        builder.append(lineLastStr)
        builder.append(text).append(lineLastStr)

        logger.info(builder.toString())
        outputStream!!.write(builder.toString().toByteArray())
    }

    fun print() {
        logger.info("HttpServer print")
    }
}