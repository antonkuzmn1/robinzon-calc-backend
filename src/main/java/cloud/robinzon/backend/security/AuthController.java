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

package cloud.robinzon.backend.security;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * The simple REST controller
 *
 * @author Anton Kuzmin
 * @since 2024.03.23
 */

@RestController
public class
AuthController {

    private final
    AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/auth")
    public ResponseEntity<?>
    auth(@RequestBody AuthRequest request) {
        return authService.auth(request);
    }

}
