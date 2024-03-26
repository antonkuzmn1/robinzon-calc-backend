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

package cloud.robinzon.backend.data.net;

import cloud.robinzon.backend.data.net.resources.NetEntity;
import cloud.robinzon.backend.data.net.resources.NetEntityRepository;
import cloud.robinzon.backend.data.net.resources.history.NetHistoryRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class NetEntityManagerTest {

    private NetEntityRepository entityRepository;
    private NetEntityManager entityManager;

    @BeforeEach
    void setUp() {
        entityRepository = mock(NetEntityRepository.class);
        NetHistoryRepository historyRepository = mock(NetHistoryRepository.class);
        entityManager = new NetEntityManager(entityRepository, historyRepository);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void insert() {
        // correct
        when(entityRepository.checkUnique(anyString())).thenReturn(false);
        ResponseEntity<?> response1 = entityManager
                .insert("example.com",
                        "192.168.1.0",
                        "255.255.255.0",
                        "8.8.8.8",
                        "8.8.4.4",
                        null,
                        true,
                        "Test Title",
                        "Test Description");
        assertNotNull(response1);
        assertEquals(200, response1.getStatusCode().value());

        // duplicate
        when(entityRepository.checkUnique("192.168.1.0")).thenReturn(true);
        ResponseEntity<?> response2 = entityManager
                .insert("example.com",
                        "192.168.1.0",
                        "255.255.255.0",
                        "8.8.8.8",
                        "8.8.4.4",
                        null,
                        true,
                        "Test Title",
                        "Test Description");
        assertNotNull(response2);
        assertEquals(400, response2.getStatusCode().value());
        assertEquals("Subnet must be unique", response2.getBody());
    }

    @Test
    void update() {
        // vars
        Optional<NetEntity> entity1 = Optional.of(
                Objects.requireNonNull(new NetEntity()
                        .update("example.com",
                                "192.168.1.0",
                                "255.255.255.0",
                                "8.8.8.8",
                                "8.8.4.4",
                                null,
                                true,
                                "Test Title",
                                "Test Description")));
        Optional<NetEntity> entity2 = Optional.of(
                Objects.requireNonNull(new NetEntity()
                        .update("example2.com",
                                "192.168.1.0",
                                "255.255.255.0",
                                "8.8.8.8",
                                "8.8.4.4",
                                null,
                                true,
                                "Test Title",
                                "Test Description")));

        // correct
        when(entityRepository.checkUnique("192.168.1.0")).thenReturn(true);
        when(entityRepository.findById(1L)).thenReturn(entity1);
        ResponseEntity<?> response1 = entityManager
                .update(1L,
                        "example2.com",
                        "192.168.1.0",
                        "255.255.255.0",
                        "8.8.8.8",
                        "8.8.4.4",
                        null,
                        true,
                        "Test Title",
                        "Test Description");
        assertNotNull(response1);
        assertEquals(200, response1.getStatusCode().value());

        // equals
        when(entityRepository.checkUnique("192.168.1.0")).thenReturn(true);
        when(entityRepository.findById(1L)).thenReturn(entity2);
        ResponseEntity<?> response2 = entityManager
                .update(1L,
                        "example2.com",
                        "192.168.1.0",
                        "255.255.255.0",
                        "8.8.8.8",
                        "8.8.4.4",
                        null,
                        true,
                        "Test Title",
                        "Test Description");
        assertNotNull(response2);
        assertEquals(400, response2.getStatusCode().value());
        assertEquals("All parameters are equal", response2.getBody());
    }

    @Test
    void delete() {
        // vars
        Optional<NetEntity> entity1 = Optional.of(
                Objects.requireNonNull(new NetEntity()
                        .update("example.com",
                                "192.168.1.0",
                                "255.255.255.0",
                                "8.8.8.8",
                                "8.8.4.4",
                                null,
                                true,
                                "Test Title",
                                "Test Description")));
        NetEntity entity2raw = new NetEntity()
                .update("example.com",
                        "192.168.1.0",
                        "255.255.255.0",
                        "8.8.8.8",
                        "8.8.4.4",
                        null,
                        true,
                        "Test Title",
                        "Test Description");
        entity2raw.setDeleted(true);
        Optional<NetEntity> entity2 = Optional.of(entity2raw);

        // correct
        when(entityRepository.findById(1L)).thenReturn(entity1);
        ResponseEntity<?> response1 = entityManager.delete(1L);
        assertNotNull(response1);
        assertEquals(200, response1.getStatusCode().value());

        // null
        when(entityRepository.findById(1L)).thenReturn(Optional.empty());
        ResponseEntity<?> response2 = entityManager.delete(1L);
        assertNotNull(response2);
        assertEquals(400, response2.getStatusCode().value());
        assertEquals("Entity not found", response2.getBody());

        // deleted
        when(entityRepository.findById(1L)).thenReturn(entity2);
        ResponseEntity<?> response3 = entityManager.delete(1L);
        assertNotNull(response3);
        assertEquals(400, response3.getStatusCode().value());
        assertEquals("Entity already deleted", response3.getBody());
    }
}