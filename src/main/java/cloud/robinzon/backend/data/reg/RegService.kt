package cloud.robinzon.backend.data.reg

import cloud.robinzon.backend.common.DeleteForm
import cloud.robinzon.backend.data.reg.resources.RegEntity
import cloud.robinzon.backend.data.reg.resources.RegEntityRepository
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class RegService(
    private val repository: RegEntityRepository,
    private val manager: RegEntityManager
) {

    fun getAll(): List<RegEntity> {
        return repository.findAll()
    }

    fun insert(form: RegInsertForm): ResponseEntity<*> {
        return manager.insert(form)
    }

    fun update(form: RegUpdateForm): ResponseEntity<*> {
        return manager.update(form)
    }

    fun delete(form: DeleteForm): ResponseEntity<*> {
        return manager.delete(form)
    }

}