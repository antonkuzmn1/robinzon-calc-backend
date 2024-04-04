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

import java.util.*

data class RegInsertForm(
    val brand: String,
    val name: String,
    val part: String,
    val serial: String,
    val buyDate: Date,
    val warrantyMonths: Int,
    val provider: String,
    val title: String,
    val description: String,
    val token: String
)

data class RegUpdateForm(
    val id: Long,
    val brand: String,
    val name: String,
    val part: String,
    val serial: String,
    val buyDate: Date,
    val warrantyMonths: Int,
    val provider: String,
    val title: String,
    val description: String,
    val token: String
)