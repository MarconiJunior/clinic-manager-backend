package com.una.database.injection

import com.una.database.dao.UserDao
import org.jetbrains.exposed.sql.Database
import org.koin.dsl.module

object DaoInjection {
    val userDao = module {
        single { UserDao(get<Database>()) }
    }
}