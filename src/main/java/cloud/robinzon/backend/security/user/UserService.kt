package cloud.robinzon.backend.security.user

import cloud.robinzon.backend.common.DeleteForm
import cloud.robinzon.backend.security.user.resources.UserEntity
import cloud.robinzon.backend.security.user.resources.UserEntityRepository
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class UserService(
    private val repository: UserEntityRepository,
    private val manager: UserEntityManager
) {

    fun getAll(): List<UserEntity> {
        return repository.findAll()
    }

    fun insert(form: UserInsertForm): ResponseEntity<*> {
        return manager.insert(form)
    }

    fun update(form: UserUpdateForm): ResponseEntity<*> {
        return manager.update(form)
    }

    fun delete(form: DeleteForm): ResponseEntity<*> {
        return manager.delete(form)
    }

}