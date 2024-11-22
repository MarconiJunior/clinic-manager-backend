package com.una.plugins

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.una.database.dao.UserDao
import com.una.models.user.User
import com.una.utils.isPasswordValid
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import org.mindrot.jbcrypt.BCrypt

fun Application.configureLogin() {
    val userDao by inject<UserDao>()
    routing {
        post("/login") {
            val user = call.receive<User>()
            val userFromDb = userDao.findByUsername(user.username)
            if (userFromDb != null && isPasswordValid(user.password, userFromDb.password)) {
                val token = JWT.create()
                    .withIssuer("ktor")
                    .withAudience("userApi")
                    .withClaim("username", userFromDb.username)
                    .withClaim("role", userFromDb.role.ordinal)
                    .sign(Algorithm.HMAC256("secret"))
                call.respond(HttpStatusCode.OK, mapOf("token" to token))
            } else {
                call.respondText("Invalid credentials", ContentType.Text.JavaScript, HttpStatusCode.BadRequest)
            }
        }
    }
}