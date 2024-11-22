package com.una.database.dao

import com.una.models.user.User
import com.una.models.user.UserRole
import com.una.utils.dbQuery
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

class UserDao(database: Database) {
    object Users : Table() {
        val id = uuid("id").autoGenerate()
        override val primaryKey = PrimaryKey(id)
        val name = varchar("name", 255)
        val username = varchar("username", 255)
        val email = varchar("email", 255)
        val password = varchar("password", 255)
        val role = enumerationByName("role", 50, UserRole::class)
        val createdAt = varchar("created_at", 255)
        val updatedAt = varchar("updated_at", 255)
    }

    init {
        transaction(database) {
            SchemaUtils.create(Users)
        }
    }

    suspend fun read(): List<User> {
        return dbQuery {
            Users.selectAll().map {
                User(
                    id = it[Users.id],
                    name = it[Users.name],
                    username = it[Users.username],
                    email = it[Users.email],
                    password = it[Users.password],
                    role = it[Users.role],
                    createdAt = it[Users.createdAt],
                    updatedAt = it[Users.updatedAt]
                )
            }
        }
    }

    suspend fun create(user: User) {
        return dbQuery {
            Users.insert {
                it[name] = user.name
                it[username] = user.username
                it[email] = user.email
                it[password] = user.password
                it[role] = user.role
                it[createdAt] = user.createdAt
                it[updatedAt] = user.updatedAt
            }[Users.id]
        }
    }

    suspend fun update(id: UUID, user: User) {
        return dbQuery {
            Users.update({ Users.id eq id }) {
                it[name] = user.name
                it[username] = user.username
                it[email] = user.email
                it[password] = user.password
                it[role] = user.role
                it[createdAt] = user.createdAt
                it[updatedAt] = user.updatedAt
            }
        }
    }

    suspend fun delete(id: UUID) {
        return dbQuery {
            Users.deleteWhere { Users.id eq id }
        }
    }
}
