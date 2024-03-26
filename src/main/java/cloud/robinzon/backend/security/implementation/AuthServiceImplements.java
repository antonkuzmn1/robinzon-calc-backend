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

package cloud.robinzon.backend.security.implementation;

import cloud.robinzon.backend.security.AuthService;
import cloud.robinzon.backend.security.objects.AuthRequest;
import cloud.robinzon.backend.security.user.resources.UserEntity;
import cloud.robinzon.backend.security.user.resources.UserEntityRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * default implement of AuthService with default overrides
 *
 * @author Anton Kuzmin
 * @since 2024.03.26
 */

@Service
@AllArgsConstructor
public class AuthServiceImplements
        implements AuthService {

    private final UserEntityRepository repository;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public ResponseEntity<?> auth(AuthRequest request) {

        final UserEntity entity = repository.findUserEntityByUsername(request.getUsername());

        if (entity == null || !encoder.matches(request.getPassword(), entity.getPassword()))
            return ResponseEntity.badRequest().body("Incorrect username or password"); // 401

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                entity, null, entity.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return ResponseEntity.ok().build(); // 200
    }

}
