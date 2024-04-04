package cloud.robinzon.backend.common

import cloud.robinzon.backend.security.jwt.JwtUtil
import cloud.robinzon.backend.security.user.resources.UserEntity

object Allow {
    @JvmStatic
    fun check(jwtUtil: JwtUtil, token: String): UserEntity? {
        if (!jwtUtil.validateToken(token)) return null
        val changeBy: UserEntity = jwtUtil.extractEntity(token)
        val allow: Boolean = changeBy.admin
        if (!allow) return null
        return changeBy
    }
}