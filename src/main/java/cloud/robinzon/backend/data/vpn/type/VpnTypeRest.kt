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

package cloud.robinzon.backend.data.vpn.type

import cloud.robinzon.backend.common.DeleteForm
import cloud.robinzon.backend.data.vpn.type.resources.VpnTypeEntity
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/data/vpn/type")
class VpnTypeRest(private val service: VpnTypeService) {

    @GetMapping
    fun getAll(): List<VpnTypeEntity> {
        return service.getAll()
    }

    @PostMapping("/insert")
    fun insert(@RequestBody form: VpnTypeInsertForm): ResponseEntity<*> {
        return service.insert(form)
    }

    @PostMapping("/update")
    fun update(@RequestBody form: VpnTypeUpdateForm): ResponseEntity<*> {
        return service.update(form)
    }

    @PostMapping("/delete")
    fun update(@RequestBody form: DeleteForm): ResponseEntity<*> {
        return service.delete(form)
    }

}