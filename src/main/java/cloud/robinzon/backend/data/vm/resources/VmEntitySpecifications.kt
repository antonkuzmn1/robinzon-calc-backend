/*

Copyright 2024 Anton Kuzmin (https://github.com/antonkuzmn1)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

*/

package cloud.robinzon.backend.data.vm.resources

import cloud.robinzon.backend.data.fm.resources.FmEntity
import cloud.robinzon.backend.data.vm.VmFindByForm
import jakarta.persistence.criteria.Predicate
import org.springframework.data.jpa.domain.Specification

class VmEntitySpecifications {
    companion object {
        @Suppress("UNUSED_ANONYMOUS_PARAMETER")
        fun filterByForm(form: VmFindByForm): Specification<VmEntity?> {
            return Specification { root, query, criteriaBuilder ->
                val predicates = mutableListOf<Predicate>()

                predicates.add(criteriaBuilder.isFalse(root.get("deleted")))

                form.name.takeIf { it.isNotEmpty() }?.let {
                    predicates.add(criteriaBuilder.like(root.get("name"), "%$it%"))
                }
                form.cpuFrom.takeIf { it >= 0 }?.let {
                    predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("cpu"), it))
                }
                form.cpuTo.takeIf { it >= 0 }?.let {
                    predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("cpu"), it))
                }
                form.ramFrom.takeIf { it >= 0 }?.let {
                    predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("ram"), it))
                }
                form.ramTo.takeIf { it >= 0 }?.let {
                    predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("ram"), it))
                }
                form.ssdFrom.takeIf { it >= 0 }?.let {
                    predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("ssd"), it))
                }
                form.ssdTo.takeIf { it >= 0 }?.let {
                    predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("ssd"), it))
                }
                form.hddFrom.takeIf { it >= 0 }?.let {
                    predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("hdd"), it))
                }
                form.hddTo.takeIf { it >= 0 }?.let {
                    predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("hdd"), it))
                }
                if (form.runningTrue) {
                    predicates.add(criteriaBuilder.isTrue(root.get("running")))
                }
                if (form.runningFalse) {
                    predicates.add(criteriaBuilder.isFalse(root.get("running")))
                }
                if (form.fmEntityIdList.isNotEmpty()) {
                    println(form.fmEntityIdList)
                    predicates.add(root.get<FmEntity>("fmEntity").get<Int>("id").`in`(form.fmEntityIdList))
                }
                form.title.takeIf { it.isNotEmpty() }?.let {
                    predicates.add(criteriaBuilder.like(root.get("title"), "%$it%"))
                }
                form.description.takeIf { it.isNotEmpty() }?.let {
                    predicates.add(criteriaBuilder.like(root.get("description"), "%$it%"))
                }

                criteriaBuilder.and(*predicates.toTypedArray())
            }
        }
    }
}
