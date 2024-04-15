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

package cloud.robinzon.backend.data.vm

import cloud.robinzon.backend.data.vm.resources.VmEntity
import cloud.robinzon.backend.data.vm.resources.history.VmHistory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/data/vm")
class VmRest(private val service: VmService) {

    @GetMapping
    fun getAll(): List<VmEntity> {
        return service.getAll()
    }

    @PostMapping("/insert")
    fun insert(@RequestBody form: VmInsertUpdateForm): ResponseEntity<*> {
        return service.insert(form)
    }

    @PostMapping("/update")
    fun update(@RequestBody form: VmInsertUpdateForm): ResponseEntity<*> {
        return service.update(form)
    }

    @PostMapping("/delete")
    fun update(@RequestBody form: VmDeleteForm): ResponseEntity<*> {
        return service.delete(form)
    }

    @PostMapping("/rent")
    fun update(@RequestBody form: VmRentForm): ResponseEntity<*> {
        return service.rent(form)
    }

    @GetMapping("/history")
    fun historyAll(): List<VmHistory> {
        return service.historyAll()
    }

    @GetMapping("/ssh/update")
    fun updateBySsh(): List<ResponseEntity<*>> {
        return service.updateBySsh()
    }

    @GetMapping("/time-to-delete")
    fun timeToDelete(): List<VmEntity> {
        return service.getAllByTimestampLessThanHour()
    }

}