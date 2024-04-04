package cloud.robinzon.backend.data.vpn.type

import cloud.robinzon.backend.common.DeleteForm
import cloud.robinzon.backend.data.vpn.type.resources.VpnTypeEntity
import cloud.robinzon.backend.data.vpn.type.resources.VpnTypeEntityRepository
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class VpnTypeService(
    private val repository: VpnTypeEntityRepository,
    private val manager: VpnTypeEntityManager
) {

    fun getAll(): List<VpnTypeEntity> {
        return repository.findAll()
    }

    fun insert(form: VpnTypeInsertForm): ResponseEntity<*> {
        return manager.insert(form)
    }

    fun update(form: VpnTypeUpdateForm): ResponseEntity<*> {
        return manager.update(form)
    }

    fun delete(form: DeleteForm): ResponseEntity<*> {
        return manager.delete(form)
    }

}