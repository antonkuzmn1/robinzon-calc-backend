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

import cloud.robinzon.backend.common.Properties;
import cloud.robinzon.backend.security.user.resources.UserEntity;
import cloud.robinzon.backend.security.user.resources.UserEntityRepository;
import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

/**
 * <h3>This class includes the following methods:
 * <ol>
 * <li><strong>{@code getSecretKey}</strong> - a method to retrieve the SecretKey for JWT token signing and validation.</li>
 * <li><strong>{@code generateToken}</strong> - a method to generate a JWT token for a given username with an expiration time of 1 hour.</li>
 * <li><strong>{@code extractAllClaims}</strong> - a method to extract all claims from the provided JWT token.</li>
 * <li><strong>{@code extractClaim}</strong> - a method to extract a specific claim from the JWT token.</li>
 * <li><strong>{@code extractUsername}</strong> - a method to extract the username from the JWT token.</li>
 * <li><strong>{@code validateToken}</strong> - a method to validate the JWT token by checking if it is not expired and belongs to an enabled user.</li>
 * <li><strong>{@code extractExpiration}</strong> - a method to extract the expiration date of the JWT token.</li>
 * <li><strong>{@code isTokenExpired}</strong> - a method to check if the JWT token is expired.</li>
 * </ol>
 * <h3>This class relies on the following dependencies:
 * <ul>
 * <li><strong>{@code Properties}</strong> - for application properties including JWT secret key.</li>
 * <li><strong>{@code UserEntityRepository}</strong> - a repository for working with user entities.</li>
 * </ul>
 *
 * @author Anton Kuzmin
 * @since 2024.03.27
 * @see JwtUtilImpl
 * @see Properties
 * @see UserEntityRepository
 * @version 2024.03.27
 */

@Service
public interface JwtUtil {

    /**
     * Obtain the secret key used for token signing.
     *
     * @return The secret key
     * @author Anton Kuzmin
     * @since 2024.03.27
     */
    SecretKey getSecretKey();

    /**
     * Generate a JWT token for the specified username.
     *
     * @param username The username
     * @return The generated JWT token
     * @author Anton Kuzmin
     * @since 2024.03.27
     */
    String generateToken(String username);

    /**
     * Extract all claims from a JWT token.
     *
     * @param token The JWT token
     * @return The payload of the token
     * @author Anton Kuzmin
     * @since 2024.03.27
     */
    Claims extractAllClaims(String token);

    /**
     * Extract a specific claim from a JWT token.
     *
     * @param token The JWT token
     * @param claimsResolver The resolver function for extracting data
     * @return The extracted value
     * @author Anton Kuzmin
     * @since 2024.03.27
     */
    <T> T extractClaim(String token,
                       Function<Claims, T> claimsResolver);

    /**
     * Extract the username from a JWT token.
     *
     * @param token The JWT token
     * @return The username
     * @author Anton Kuzmin
     * @since 2024.03.27
     */
    String extractUsername(String token);

    /**
     * Extract the entity from a JWT token.
     *
     * @param token The JWT token
     * @return The entity
     * @author Anton Kuzmin
     * @since 2024.03.28
     */
    UserEntity extractEntity(String token);

    /**
     * Validate the JWT token for a specific user entity.
     *
     * @param token The JWT token
     * @return The validation result of the token
     * @author Anton Kuzmin
     * @since 2024.03.27
     */
    boolean validateToken(String token);

    /**
     * Extract the expiration date from a JWT token.
     *
     * @param token The JWT token
     * @return The expiration date
     * @author Anton Kuzmin
     * @since 2024.03.27
     */
    Date extractExpiration(String token);

    /**
     * Check if the JWT token has expired.
     *
     * @param token The JWT token
     * @return The result of the expiration check
     * @author Anton Kuzmin
     * @since 2024.03.27
     */
    boolean isTokenExpired(String token);

    /**
     * Check for admin rights.
     *
     * @param token The JWT token
     * @return The result of the expiration check
     * @author Anton Kuzmin
     * @since 2024.03.28
     */
    UserEntity isAdmin(String token);

    /**
     * Check for custom rights.
     *
     * @param token The JWT token
     * @return The result of the expiration check
     * @author Anton Kuzmin
     * @since 2024.03.28
     */
    @SuppressWarnings("unused")
    UserEntity hasRole(String token, String role);

}
