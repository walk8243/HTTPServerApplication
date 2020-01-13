package xyz.walk8243.httpserverapplication

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.SpringApplication

class HttpServer {
    private val logger: Logger = LoggerFactory.getLogger(HttpServer::class.java)

    constructor() {
        createServer(8001)
    }
    constructor(port: Int) {
        createServer(port)
    }

    fun createServer(port: Int) {
        val server = SpringApplication.run(HttpServer::class.java, "")
//        server.
    }

    fun print() {
        logger.debug("HttpServer print")
    }
}