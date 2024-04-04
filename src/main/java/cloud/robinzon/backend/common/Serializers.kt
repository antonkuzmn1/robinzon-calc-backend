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

@file:Suppress("PropertyName")

package cloud.robinzon.backend.common

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue

data class VmRawForm(
    val fm_id: String,
    val vm_hdd: Int,
    val vm_state: Int,
    val vm_name: String,
    val vm_ram: Int,
    val vm_id: String,
    val vm_cpu: Int,
    val vm_ssd: Int
)

fun serializeVm(jsonString: String): List<VmRawForm> {
    val mapper = jacksonObjectMapper()
    val vmList: List<VmRawForm> = mapper.readValue(jsonString)
    return vmList
}