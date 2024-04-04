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

import java.util.*

data class ClientInsertForm(
    val name: String,
    val inn: String,
    val discount: Int,
    val contractNumber: Int,
    val contractDate: Date,
    val title: String,
    val description: String,
    val token: String
)

data class ClientUpdateForm(
    val id: Long,
    val name: String,
    val inn: String,
    val discount: Int,
    val contractNumber: Int,
    val contractDate: Date,
    val title: String,
    val description: String,
    val token: String
)

data class ClientBalanceForm(
    val id: Long,
    val balance: Int,
    val token: String
)

data class ClientPayForm(
    val id: Long,
    val sum: Int,
    val token: String
)