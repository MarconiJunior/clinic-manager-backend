package com.una.utils

import org.mindrot.jbcrypt.BCrypt

fun isPasswordValid(providedPassword: String, storedHash: String): Boolean {
    return BCrypt.checkpw(providedPassword, storedHash)
}
