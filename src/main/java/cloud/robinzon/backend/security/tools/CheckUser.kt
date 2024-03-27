package cloud.robinzon.backend.security.tools

import cloud.robinzon.backend.security.jwt.JwtUtil
import cloud.robinzon.backend.security.user.resources.UserEntity
import cloud.robinzon.backend.security.user.resources.UserEntityRepository
import lombok.AllArgsConstructor
import org.springframework.stereotype.Service

@Service
@AllArgsConstructor
object CheckUser {

    private lateinit var jwt: JwtUtil
    private lateinit var repo: UserEntityRepository

    @JvmStatic
    fun extractEntity(token: String): UserEntity {
        return repo.findUserEntityByUsername(jwt.extractUsername(token))
    }

    @Suppress("unused")
    @JvmStatic
    fun hasRole(user: UserEntity, role: String): Boolean {
        return user.authorities.any { it.authority == role }
    }

}