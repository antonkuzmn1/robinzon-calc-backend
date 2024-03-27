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

package cloud.robinzon.backend.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Implementations for Properties interface
 *
 * @author Anton Kuzmin
 * @see Properties
 * @since 2024.03.27
 */

@Getter
@Component
@NoArgsConstructor
@AllArgsConstructor
public class PropertiesImpl
        implements Properties {

    @Value("${jwt.secret.key}")
    private String jwtSecretKey;

    @Value("${ssh.fm.admin.login}")
    private String sshFmAdminLogin;

    @Value("${ssh.fm.admin.password}")
    private String sshFmAdminPassword;

    @Value("${ssh.fm.admin.command}")
    private String sshFmAdminCommand;

}
