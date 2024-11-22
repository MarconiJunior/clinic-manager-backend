package com.una.models.user

import java.util.UUID

data class User(
    val id: UUID? = null,
    val name: String,
    val username: String,
    val email: String,
    val password: String,
    val role: UserRole,
    val createdAt: String? = null,
    val updatedAt: String? = null,
)