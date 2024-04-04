package cloud.robinzon.backend.data.vpn.server

import cloud.robinzon.backend.common.DeleteForm
import cloud.robinzon.backend.data.vpn.server.resources.VpnServerEntity
import cloud.robinzon.backend.data.vpn.server.resources.VpnServerEntityRepository
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class VpnServerService(
    private val repository: VpnServerEntityRepository,
    private val manager: VpnServerEntityManager
) {

    fun getAll(): List<VpnServerEntity> {
        return repository.findAll()
    }

    fun insert(form: VpnServerInsertForm): ResponseEntity<*> {
        return manager.insert(form)
    }

    fun update(form: VpnServerUpdateForm): ResponseEntity<*> {
        return manager.update(form)
    }

    fun delete(form: DeleteForm): ResponseEntity<*> {
        return manager.delete(form)
    }

}