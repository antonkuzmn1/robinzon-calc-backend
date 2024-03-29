/*

Copyright 2024 Anton Kuzmin (http://github.com/antonkuzmn1)

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

package cloud.robinzon.backend.common

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class PropertiesTest {

    @Autowired
    private lateinit var properties: Properties

    @BeforeEach
    fun setUp() {
    }

    @AfterEach
    fun tearDown() {
    }

    @Test
    fun getJwtSecretKey() {
        val text: String = properties.jwtSecretKey
        println("jwt.secret.key: $text")
    }

    @Test
    fun getSshFmAdminLogin() {
        val text: String = properties.sshFmAdminLogin
        println("ssh.fm.admin.login: $text")
    }

    @Test
    fun getSshFmAdminPassword() {
        val text: String = properties.sshFmAdminPassword
        println("ssh.fm.admin.password: $text")
    }

    @Test
    fun getSshFmAdminCommand() {
        val text: String = properties.sshFmAdminCommand
        println("ssh.fm.admin.command: $text")
    }
}