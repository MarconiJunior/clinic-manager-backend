package com.una.plugins

import io.ktor.server.application.*
import org.jetbrains.exposed.sql.Database
import org.koin.ktor.ext.inject

fun Application.configureDatabases() {
    val database by inject<Database>()
}
