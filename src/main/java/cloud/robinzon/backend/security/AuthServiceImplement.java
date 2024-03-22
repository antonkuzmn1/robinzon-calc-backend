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

import cloud.robinzon.backend.security.user.resources.UserEntity;
import cloud.robinzon.backend.security.user.resources.UserEntityRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 * default implement of AuthService with default overrides
 *
 * @author Anton Kuzmin
 * @since 2024.03.23
 */

@Service
public class
AuthServiceImplement
        implements AuthService {

    private final
    UserEntityRepository repository;

    private final
    PasswordEncoderImplement passwordEncoder;

    public AuthServiceImplement(UserEntityRepository repository,
                                PasswordEncoderImplement passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public ResponseEntity<?>
    auth(AuthRequest request) {

        UserEntity entity
                = repository.findUserEntityByUsername(
                request.getUsername());

        if (entity == null
                || !passwordEncoder.matches(
                request.getPassword(),
                entity.getPassword())) return ResponseEntity
                .badRequest()
                .body("Incorrect username or password"); // 401

        UserDetails userDetails
                = new UserDetailsImplement(entity);

        Authentication authentication
                = new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                userDetails.getAuthorities());

        SecurityContextHolder
                .getContext()
                .setAuthentication(authentication);

        return ResponseEntity
                .ok()
                .build(); // 200
    }

}
