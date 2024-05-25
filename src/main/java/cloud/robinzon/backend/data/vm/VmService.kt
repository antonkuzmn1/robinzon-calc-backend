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
import cloud.robinzon.backend.data.vm.resources.VmEntitySpecifications
import cloud.robinzon.backend.data.vm.resources.history.VmHistory
import cloud.robinzon.backend.data.vm.resources.history.VmHistoryRepository
import org.springframework.data.jpa.domain.Specification
import org.springframework.http.ResponseEntity
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import java.sql.Timestamp


@Service
@EnableScheduling
class VmService(
    private val repository: VmEntityRepository,
    private val history: VmHistoryRepository,
    private val manager: VmManager,
    private val fmService: FmService,
    private val vmSshService: VmSshService
) {

    fun getAll(form: VmFindByForm): List<VmEntity> {
//        println("\n\nform:\n\n")
//        println(form)
        val spec: Specification<VmEntity?> = VmEntitySpecifications.filterByForm(form)
        return repository.findAll(spec)
//        return repository.findAllByDeletedFalseOrderByNameAsc()
    }

    fun insert(form: VmInsertUpdateForm): ResponseEntity<*> {
        return manager.insert(form)
    }

    fun update(form: VmInsertUpdateForm): ResponseEntity<*> {
        return manager.update(form)
    }

    fun delete(form: VmDeleteForm): ResponseEntity<*> {
        return manager.delete(form)
    }

    fun rent(form: VmRentForm): ResponseEntity<*> {
        return manager.rent(form)
    }

    fun historyAll(): List<VmHistory> {
        return history.findAll()
    }

    //    @Scheduled(fixedDelay = 10 * 60 * 1000) // every 10 min
    @Scheduled(cron = "0 */10 * * * *")
    fun updateBySsh(): List<ResponseEntity<*>> {
        println("Start updating via SSH...")
        val rawList: List<VmRawForm> = fmService.sshRequest()
        println(rawList)
        return vmSshService.commit(rawList)
    }

    @Suppress("unused")
    fun getAllByTimestampLessThanHour(): List<VmEntity> {
        val oneHourAgo = Timestamp(System.currentTimeMillis() - 3600000)
        return repository.findAllByTimestampLessThan(oneHourAgo)
    }

}