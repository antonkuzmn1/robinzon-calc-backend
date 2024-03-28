package cloud.robinzon.backend.security.tools

import cloud.robinzon.backend.security.jwt.JwtUtil
import cloud.robinzon.backend.security.user.resources.UserEntity
import lombok.AllArgsConstructor
import org.springframework.stereotype.Service

@Service
@AllArgsConstructor
object JwtUtilStatic {

    private lateinit var jwt: JwtUtil

    @JvmStatic
    fun extractEntity(token: String): UserEntity {
        return jwt.extractEntity(token)
    }

}