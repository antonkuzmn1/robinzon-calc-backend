package cloud.robinzon.backend.data.vm

import cloud.robinzon.backend.data.vm.resources.VmEntity
import cloud.robinzon.backend.data.vm.resources.VmEntityRepository
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class VmService(
    private val repository: VmEntityRepository,
    private val manager: VmManager
) {

    fun getAll(): List<VmEntity> {
        return repository.findAll()
    }

    fun insert(form: VmInsertUpdateForm): ResponseEntity<*> {
        return manager.insert(form)
    }

    fun update(form: VmInsertUpdateForm): ResponseEntity<*> {
        return manager.update(form)
    }

    fun delete(form: VmDeleteForm): ResponseEntity<*> {
        return manager.delete(form)
    }

    fun balance(form: VmRentForm): ResponseEntity<*> {
        return manager.rent(form)
    }

}