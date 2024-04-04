package cloud.robinzon.backend.data.net.resources

import cloud.robinzon.backend.common.DeleteForm
import cloud.robinzon.backend.common.RentForm
import cloud.robinzon.backend.data.net.NetInsertForm
import cloud.robinzon.backend.data.net.NetManager
import cloud.robinzon.backend.data.net.NetUpdateForm
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class NetService(
    private val repository: NetEntityRepository,
    private val manager: NetManager
) {

    fun getAll(): List<NetEntity> {
        return repository.findAll()
    }

    fun insert(form: NetInsertForm): ResponseEntity<*> {
        return manager.insert(form)
    }

    fun update(form: NetUpdateForm): ResponseEntity<*> {
        return manager.update(form)
    }

    fun delete(form: DeleteForm): ResponseEntity<*> {
        return manager.delete(form)
    }

    fun balance(form: RentForm): ResponseEntity<*> {
        return manager.rent(form)
    }

}