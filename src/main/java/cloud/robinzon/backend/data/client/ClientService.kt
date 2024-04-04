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

package cloud.robinzon.backend.data.client

import cloud.robinzon.backend.common.DeleteForm
import cloud.robinzon.backend.data.client.resources.ClientEntity
import cloud.robinzon.backend.data.client.resources.ClientEntityRepository
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class ClientService(
    private val repository: ClientEntityRepository,
    private val manager: ClientManager
) {

    fun getAll(): List<ClientEntity> {
        return repository.findAll()
    }

    fun insert(form: ClientInsertForm): ResponseEntity<*> {
        return manager.insert(form)
    }

    fun update(form: ClientUpdateForm): ResponseEntity<*> {
        return manager.update(form)
    }

    fun delete(form: DeleteForm): ResponseEntity<*> {
        return manager.delete(form)
    }

    fun balance(form: ClientBalanceForm): ResponseEntity<*> {
        return manager.balance(form)
    }

    fun pay(form: ClientPayForm): ResponseEntity<*> {
        return manager.pay(form)
    }

}