package com.una.routes

import com.una.database.dao.UserDao
import com.una.models.user.User
import com.una.utils.extractParameterUserId
import com.una.utils.isUUID
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.util.UUID
import kotlin.uuid.Uuid

fun Route.userRoute(userDao: UserDao) {
    route("/user") {
        get {
            call.respond(userDao.read())
        }
        delete("/{userId}") {
            val userId = call.extractParameterUserId()
            call.respond(userDao.delete(UUID.fromString(userId)))
        }
        put("/{userId}") {
            val userId = call.extractParameterUserId()
            val user = call.receive<User>()
            call.respond(userDao.update(UUID.fromString(userId), user))
        }
        post {
            val user = call.receive<User>()
            call.respond(userDao.create(user))
        }
    }
}