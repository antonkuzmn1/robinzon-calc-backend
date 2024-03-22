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

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Security configuration
 *
 * @author Anton Kuzmin
 * @since 2024.03.23
 */

@Configuration
@EnableWebSecurity
public class
SecurityConfig {

    @Bean
    public SecurityFilterChain
    securityFilterChain(HttpSecurity http
    ) throws Exception {
        return http
                .authorizeHttpRequests(SecurityConfig::customize)
                .formLogin(SecurityConfig::customize)
                .logout(LogoutConfigurer::permitAll)
                .build();
    }

    private static void
    customize(FormLoginConfigurer<HttpSecurity> form
    ) {
        form
                .loginPage("/api/auth").permitAll();
    }

    private static void
    customize(AuthorizeHttpRequestsConfigurer<HttpSecurity>
                      .AuthorizationManagerRequestMatcherRegistry requests
    ) {
        requests
                .requestMatchers("/api").permitAll()
                .anyRequest().authenticated();
    }

}
