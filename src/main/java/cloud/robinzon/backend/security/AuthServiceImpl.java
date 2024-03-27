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
import cloud.robinzon.backend.security.user.resources.UserEntity;
import cloud.robinzon.backend.security.user.resources.UserEntityRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * The AuthServiceImpl class is an implementation of the AuthService interface, providing functionality for user authentication, token validation, and user logout.
 *
 * @author Anton Kuzmin
 * @see AuthService
 * @since 2024.03.26
 */

@Service
@AllArgsConstructor
public class AuthServiceImpl
        implements AuthService {

    private final UserEntityRepository repository;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    private final JwtUtil jwtUtil;

    @Override
    public ResponseEntity<?> auth(AuthRequest request) {

        final UserEntity entity = repository.findUserEntityByUsername(request.getUsername());

        if (entity == null || !encoder.matches(request.getPassword(), entity.getPassword()))
            return ResponseEntity.status(401).body("Incorrect username or password"); // 401

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                entity, null, entity.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtUtil.generateToken(entity.getUsername());

        return ResponseEntity.ok().body(token); // 200
    }

    @Override
    public ResponseEntity<?> check(String token) {
        if (!jwtUtil.validateToken(token))
            return ResponseEntity.status(401).body("Validation failed");

        final String username = jwtUtil.extractUsername(token);
        final UserEntity entity = repository.findUserEntityByUsername(username);
        final Collection<? extends GrantedAuthority> authorities = entity.getAuthorities();

        return ResponseEntity.ok().body(authorities);
    }

    @Override
    public ResponseEntity<?> logout() {
        SecurityContextHolder.clearContext();
        return ResponseEntity.ok().body("Logged out successfully");
    }

}
