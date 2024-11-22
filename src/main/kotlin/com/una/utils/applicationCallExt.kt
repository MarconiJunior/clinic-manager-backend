package com.una.utils

import io.ktor.server.application.*

fun ApplicationCall.extractParameterUserId(): String {
    val userId = parameters["userId"] ?: throw IllegalArgumentException("Parameter user id not found")
    if (!userId.isUUID()) throw IllegalArgumentException("Invalid user id")
    return userId
}