package com.una.database

import org.jetbrains.exposed.sql.Database
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.jetbrains.exposed.sql.transactions.transaction
import org.koin.dsl.module

val databaseModule = module {
    single { Database.connect(hikari()) }
}

private fun hikari(): HikariDataSource {
    val config = HikariConfig().apply {
        jdbcUrl = "localhost:5432"
        driverClassName = "org.postgresql.Driver"
        username = "postgres"
        password = "postgres"
        maximumPoolSize = 3
        isAutoCommit = false
        transactionIsolation = "TRANSACTION_REPEATABLE_READ"
        validate()
    }
    return HikariDataSource(config)
}
