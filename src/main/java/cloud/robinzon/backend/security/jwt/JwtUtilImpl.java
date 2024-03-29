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
import io.jsonwebtoken.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Objects;
import java.util.function.Function;

/**
 * The JwtUtilImpl class is an implementation of the JwtUtil interface, providing functionality for JWT token creation, validation, and extraction of claims. This class handles the generation, parsing, and verification of JWT tokens.
 *
 * @author Anton Kuzmin
 * @see JwtUtil
 * @since 2024.03.27
 */

@Component
@AllArgsConstructor
public class JwtUtilImpl
        implements JwtUtil {

    @SuppressWarnings("FieldCanBeLocal")
    private final long EXPIRATION_TIME = 3600_000;

    @Autowired
    private Properties properties;

    private final UserEntityRepository userEntityRepository;

    @Override
    public SecretKey getSecretKey() {
//        System.out.println(properties.getSshFmAdminCommand());
//        System.out.println(properties.getJwtSecretKey());
        final byte[] secretBytes = properties.getJwtSecretKey().getBytes(StandardCharsets.UTF_8);
        return new SecretKeySpec(secretBytes, 0, secretBytes.length, "HMACSHA256");
    }

    @Override
    public String generateToken(String username) {
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(getSecretKey())
                .compact();
    }

    @Override
    public Claims extractAllClaims(String token) {
        JwtParserBuilder jwtParserBuilder = Jwts.parser().verifyWith(getSecretKey());
        JwtParser jwtParser = jwtParserBuilder.build();
        try {
            Jwt<JwsHeader, Claims> jwt = jwtParser.parseSignedClaims(token);
            return jwt.getPayload();
        } catch (JwtException e) {
            return null;
        }
    }

    @Override
    public <T> T extractClaim(String token,
                              Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    @Override
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    @Override
    public UserEntity extractEntity(String token) {
        return userEntityRepository.findUserEntityByUsername(extractUsername(token));
    }


    @Override
    public boolean validateToken(String token) {
        final String username = extractUsername(token);
        UserEntity userEntity = userEntityRepository.findUserEntityByUsername(username);
        return (userEntity != null
                && userEntity.isEnabled()
                && !isTokenExpired(token));
    }

    @Override
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    @Override
    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    @Override
    public UserEntity isAdmin(String token) {
        UserEntity user = extractEntity(token);
        return user.admin ? user : null;
    }

    @Override
    public UserEntity hasRole(String token, String role) {
        UserEntity user = extractEntity(token);
        return user.getAuthorities().stream().anyMatch(it ->
                Objects.equals(it.getAuthority(), role)
        ) ? user : null;
    }

}
