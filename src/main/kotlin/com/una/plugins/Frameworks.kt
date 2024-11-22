package com.una.plugins

import com.una.database.dao.UserDao
import com.una.database.hikari
import com.una.services.HelloService
import io.ktor.server.application.*
import org.jetbrains.exposed.sql.Database
import org.koin.dsl.module
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger

fun Application.configureFrameworks() {
    install(Koin) {
        slf4jLogger()
        modules(module {
            single<HelloService> {
                HelloService {
                    println(environment.log.info("Hello, World!"))
                }
            }
            single { Database.connect(hikari()) }
            single { UserDao(get()) }
        })
    }
}
