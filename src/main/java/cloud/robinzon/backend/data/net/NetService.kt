/*

Copyright 2024 Anton Kuzmin (https://github.com/antonkuzmn1)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    https://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

*/

package cloud.robinzon.backend.data.net

import cloud.robinzon.backend.common.DeleteForm
import cloud.robinzon.backend.common.RentForm
import cloud.robinzon.backend.data.net.resources.NetEntity
import cloud.robinzon.backend.data.net.resources.NetEntityRepository
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