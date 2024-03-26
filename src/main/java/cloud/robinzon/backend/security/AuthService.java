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

import cloud.robinzon.backend.security.objects.AuthRequest;
import org.springframework.http.ResponseEntity;

/**
 * Security service
 *
 * @author Anton Kuzmin
 * @since 2024.03.26
 */

public interface AuthService {

    /**
     * This method is used to authenticate a user based on the provided username and password.
     * If the username and password are correct, the user is authenticated and a success response is returned.
     * If the username or password is incorrect, a bad request response is returned.
     *
     * @param request the authentication request containing the username and password
     * @return ResponseEntity representing the authentication result
     *
     * @author Anton Kuzmin
     * @since 2024.03.26
     */
    ResponseEntity<?> auth(AuthRequest request);

}
