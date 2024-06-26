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

package cloud.robinzon.backend.security.jwt;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.crypto.SecretKey;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class JwtUtilTest {

    @Autowired
    private JwtUtil jwtUtil;

    private final String username = "example";

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void init() {
    }

    @Test
    void getSecretKey() {
        SecretKey secretKey = jwtUtil.getSecretKey();
        System.out.println(secretKey);
        assertNotNull(secretKey);
    }

    @Test
    void generateToken() {
        final String token = jwtUtil.generateToken(username);
        System.out.println(token);
        assertNotNull(token);
    }

    @Test
    void extractAllClaims() {
    }

    @Test
    void extractClaim() {
    }

    @Test
    void extractUsername() throws InterruptedException {
        byte pauseSeconds = 0;

        //noinspection ConstantValue
        Thread.sleep(pauseSeconds * 1000);
        final String token = jwtUtil.generateToken(username);
        System.out.println(token);
        assertNotNull(token);

        //noinspection ConstantValue
        Thread.sleep(pauseSeconds * 1000);
        String result = jwtUtil.extractUsername(token);
        System.out.println(result);
        assertNotNull(result);
    }

    @Test
    void validateToken() {
    }

    @Test
    void extractExpiration() {
    }

    @Test
    void isTokenExpired() {
    }

}