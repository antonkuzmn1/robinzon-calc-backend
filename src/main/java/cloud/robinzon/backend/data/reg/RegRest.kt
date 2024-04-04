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

package cloud.robinzon.backend.data.reg

import cloud.robinzon.backend.common.DeleteForm
import cloud.robinzon.backend.data.reg.resources.RegEntity
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/data/reg")
class RegRest(private val service: RegService) {

    @GetMapping
    fun getAll(): List<RegEntity> {
        return service.getAll()
    }

    @PostMapping("/insert")
    fun insert(@RequestBody form: RegInsertForm): ResponseEntity<*> {
        return service.insert(form)
    }

    @PostMapping("/update")
    fun update(@RequestBody form: RegUpdateForm): ResponseEntity<*> {
        return service.update(form)
    }

    @PostMapping("/delete")
    fun update(@RequestBody form: DeleteForm): ResponseEntity<*> {
        return service.delete(form)
    }

}