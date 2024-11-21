package com.una

import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    configureSecurity()
    configureHTTP()
    configureSockets()
    configureFrameworks()
    configureSerialization()
    configureDatabases()
    configureTemplating()
    configureMonitoring()
    configureRouting()
}