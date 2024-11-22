package com.una.plugins

import com.una.database.dao.UserDao
import com.una.routes.userRoute
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Application.configureRouting() {
    val userDao by inject<UserDao>()
    install(StatusPages) {
        status(HttpStatusCode.NotFound) { call, status ->
            call.respondText(
                text = "404: The resource you are looking for was not found. 🤷",
                status = status
            )
        }
        exception<Throwable> { call, cause ->
            call.respondText(
                text = "500: Server error occurred! Details: ${cause.localizedMessage}",
                status = HttpStatusCode.InternalServerError
            )
        }
        exception<IllegalArgumentException> { call, cause ->
            call.respondText(
                text = "400: Invalid request. Error: ${cause.localizedMessage}",
                status = HttpStatusCode.BadRequest
            )
        }
    }
    routing {
        get("/") {
            call.respondText("Hello World!")
        }
        userRoute(userDao)
    }
}
