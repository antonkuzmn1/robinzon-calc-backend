package cloud.robinzon.backend.data.vpn.user

import cloud.robinzon.backend.common.DeleteForm
import cloud.robinzon.backend.data.vpn.user.resources.VpnUserEntity
import cloud.robinzon.backend.data.vpn.user.resources.VpnUserEntityRepository
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class VpnUserService(
    private val repository: VpnUserEntityRepository,
    private val manager: VpnUserEntityManager
) {

    fun getAll(): List<VpnUserEntity> {
        return repository.findAll()
    }

    fun insert(form: VpnUserInsertForm): ResponseEntity<*> {
        return manager.insert(form)
    }

    fun update(form: VpnUserUpdateForm): ResponseEntity<*> {
        return manager.update(form)
    }

    fun delete(form: DeleteForm): ResponseEntity<*> {
        return manager.delete(form)
    }

}