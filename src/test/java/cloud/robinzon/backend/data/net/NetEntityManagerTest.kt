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

package cloud.robinzon.backend.data.net

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class NetEntityManagerTest {

//    @Autowired
//    private lateinit var jwtUtil: JwtUtil;
//
//    @Autowired
//    private lateinit var entityManager: NetEntityManager
//
//    private lateinit var token: String
//    private val username = "root"
//    private val entityRepository = mock(NetEntityRepository::class.java)

    @BeforeEach
    fun setUp() {
//        token = jwtUtil.generateToken(username)
    }

    @AfterEach
    fun tearDown() {
    }

    @Test
    fun insert() {
//        `when`(entityRepository.checkUnique("anyString")).thenReturn(false)
//        val response1: ResponseEntity<*> = entityManager.insert(
//            "example.com",
//            "192.168.1.0",
//            "255.255.255.0",
//            "8.8.8.8",
//            "8.8.4.4",
//            null,
//            true,
//            "Test Title",
//            "Test Description",
//            token
//        )
//        assertNotNull(response1)
//        assertEquals(200, response1.statusCode.value())
//
//        // duplicate
//        `when`(entityRepository.checkUnique("192.168.1.0")).thenReturn(true)
//        val response2: ResponseEntity<*> = entityManager.insert(
//            "example.com",
//            "192.168.1.0",
//            "255.255.255.0",
//            "8.8.8.8",
//            "8.8.4.4",
//            null,
//            true,
//            "Test Title",
//            "Test Description",
//            token
//        )
//        assertNotNull(response2)
//        assertEquals(400, response2.statusCode.value())
//        assertEquals("Subnet must be unique", response2.body)
    }

}