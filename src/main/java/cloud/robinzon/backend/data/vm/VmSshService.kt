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

import cloud.robinzon.backend.common.VmRawForm
import cloud.robinzon.backend.data.fm.FmService
import cloud.robinzon.backend.data.vm.resources.VmEntity
import cloud.robinzon.backend.data.vm.resources.VmEntityRepository
import cloud.robinzon.backend.security.jwt.JwtUtil
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.sql.Timestamp

@Service
class VmSshService(
    private val fmService: FmService,
    private val jwtUtil: JwtUtil,
    private val vmEntityRepository: VmEntityRepository,
    private val vmManager: VmManager
) {

    private fun convert(rawList: List<VmRawForm>, token: String): List<VmInsertUpdateForm> {
        return rawList.map {
            VmInsertUpdateForm(
                id = it.vm_id,
                name = it.vm_name,
                cpu = it.vm_cpu,
                ram = it.vm_ram,
                ssd = it.vm_ssd,
                hdd = it.vm_hdd,
                running = it.vm_state == 1,
                fmEntity = fmService.getFmByName(it.fm_id),
                title = "",
                description = "",
                token = token
            )
        }
    }

    fun commit(rawList: List<VmRawForm>): List<ResponseEntity<*>> {
        val responseList: List<ResponseEntity<*>> = ArrayList()

        val token: String = jwtUtil.generateToken("system")
        if (!jwtUtil.validateToken(token)) throw Error("Invalid JWT")
        val forms: List<VmInsertUpdateForm> = convert(rawList, token)

        // insert or update
        for (form in forms) {
            val vmEntity: VmEntity? = vmEntityRepository.findById(form.id).orElse(null)

//            println("\n\n\n\n\nID: ${form.id}\n\n\n\n\n")

            if (vmEntity == null) responseList.plusElement(
                vmManager.insert(form)
            )
            else responseList.plusElement(
                vmManager.update(
                    VmInsertUpdateForm(
                        id = form.id,
                        name = form.name,
                        cpu = form.cpu,
                        ram = form.ram,
                        ssd = form.ssd,
                        hdd = form.hdd,
                        running = form.running,
                        fmEntity = form.fmEntity,
                        title = vmEntity.title,
                        description = vmEntity.description,
                        token = form.token
                    )
                )
            )
        }

        // delete by timestamp
        val oneHourAgo = Timestamp(System.currentTimeMillis() - 600000)
        val vmEntityForDeleteList: List<VmEntity> = vmEntityRepository.findAllByTimestampLessThan(oneHourAgo)
        for (vmEntityForDelete in vmEntityForDeleteList) {
            vmManager.delete(
                VmDeleteForm(
                    id = vmEntityForDelete.id,
                    token = token
                )
            )
        }

        return responseList
    }
}