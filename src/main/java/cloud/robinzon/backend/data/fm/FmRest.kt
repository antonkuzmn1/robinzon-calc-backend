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

package cloud.robinzon.backend.data.fm

import cloud.robinzon.backend.common.DeleteForm
import cloud.robinzon.backend.common.RentForm
import cloud.robinzon.backend.data.fm.resources.FmEntity
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/data/fm")
class FmRest(private val fmService: FmService) {

    @GetMapping
    fun getAll(): List<FmEntity> {
        return fmService.all
    }

    @GetMapping("/vm")
    fun getByVm(): List<FmEntity> {
        return fmService.byVm
    }

    @PostMapping("/insert")
    fun insert(@RequestBody form: FmInsertForm): ResponseEntity<*> {
        return fmService.insert(form)
    }

    @PostMapping("/update")
    fun update(@RequestBody form: FmUpdateForm): ResponseEntity<*> {
        return fmService.update(form)
    }

    @PostMapping("/delete")
    fun update(@RequestBody form: DeleteForm): ResponseEntity<*> {
        return fmService.delete(form)
    }

    @PostMapping("/rent")
    fun update(@RequestBody form: RentForm): ResponseEntity<*> {
        return fmService.rent(form)
    }

}