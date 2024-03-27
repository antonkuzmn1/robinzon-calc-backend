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

import cloud.robinzon.backend.security.jwt.JwtUtil;
import cloud.robinzon.backend.security.objects.AuthRequest;
import cloud.robinzon.backend.security.user.resources.UserEntityRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * <h3>This class provides the following methods:
 * <ol>
 * <li>{@code auth} - a method for authenticating a user based on the provided credentials. Upon successful authentication, a JWT token is created for the user.</li>
 * <li>{@code check} - a method for validating the passed JWT token and retrieving the user's access rights.</li>
 * <li>{@code logout} - a method for logging out a user by clearing the security context.</li>
 * </ol>
 *
 * <h3>The AuthServiceImpl class uses the following dependencies:
 * <ul>
 * <li>{@code UserEntityRepository} - a repository for working with user entities.</li>
 * <li>{@code BCryptPasswordEncoder} - for password hashing and validation.</li>
 * <li>{@code JwtUtil} - a utility class for working with JWT tokens.</li>
 * </ul>
 *
 * <h3>Example of usage:
 * <blockquote><code>
 * AuthService authService = new AuthServiceImpl(userEntityRepository, jwtUtil);
 * AuthRequest authRequest = new AuthRequest("username", "password");
 * ResponseEntity<?> authResponse = authService.auth(authRequest);
 * </code></blockquote>
 *
 * <p>Upon successful authentication, the auth() method returns an HTTP 200 with a JWT token that can be used for subsequent requests.
 * The check() method returns the user's access rights upon token validation. The logout() method ends the user's session.
 *
 * <h3>Key principles of the class:
 * <ul>
 * <li>Verifying user data during authentication.</li>
 * <li>Generating and validating JWT tokens for security purposes.</li>
 * <li>Clearing the security context upon user logout.</li>
 * </ul>
 *
 * @author Anton Kuzmin
 * @version 2024.03.27
 * @see AuthServiceImpl
 * @see UserEntityRepository
 * @see BCryptPasswordEncoder
 * @see JwtUtil
 * @since 2024.03.27
 */

public interface AuthService {

    /**
     * Authenticates a user based on the provided credentials.
     *
     * @param request The authentication request containing username and password.
     * @return ResponseEntity with a JWT token if authentication is successful (HTTP 200),
     * or an error message if authentication fails (HTTP 401).
     */
    ResponseEntity<?> auth(AuthRequest request);

    /**
     * Method for validating a JWT token and retrieving the user's access rights.
     *
     * @param token The JWT token to be validated.
     * @return ResponseEntity with user authorities if the token is valid (HTTP 200),
     * or an error message if validation fails (HTTP 401).
     */
    ResponseEntity<?> check(String token);

    /**
     * Method for logging out a user by clearing the security context.
     *
     * @return ResponseEntity indicating successful logout (HTTP 200).
     */
    ResponseEntity<?> logout();

}
