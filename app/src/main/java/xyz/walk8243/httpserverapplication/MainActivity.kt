package xyz.walk8243.httpserverapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.io.*
import java.net.Socket
import java.util.*
import java.util.logging.Logger

class MainActivity : AppCompatActivity() {
    companion object {
        private val logger = Logger.getLogger("MainActivity")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val server = HttpServer(10000)
        server.print()
        server.start()

        request()
    }

    private fun request() {
        Thread.sleep(3000)
        logger.info("request")
        Thread(Runnable {
            val socket = Socket("127.0.0.1", 10000)
            val inputStream = BufferedReader(InputStreamReader(socket.getInputStream()))
            val outputStream = BufferedOutputStream(socket.getOutputStream())
            outputStream.write(String.format("input").toByteArray())
            outputStream.flush()

            while (true) {
                logger.info(inputStream.readLine())
            }
        }).start()
    }
}
