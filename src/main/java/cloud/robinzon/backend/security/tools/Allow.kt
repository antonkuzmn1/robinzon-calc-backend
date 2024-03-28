package cloud.robinzon.backend.security.tools

import cloud.robinzon.backend.security.user.resources.UserEntity

fun isAdmin(token: String): UserEntity? {
    val user: UserEntity = JwtUtilStatic.extractEntity(token)
    return if (
            user.isAdmin
    ) user else null
}

fun hasRole(token: String, role: String): UserEntity? {
    val user: UserEntity = JwtUtilStatic.extractEntity(token)
    return if (
            user.authorities.any {
                it.authority == role
            }
    ) user else null
}