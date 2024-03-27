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

import org.springframework.stereotype.Repository;


/**
 * This repository makes it easier to interact with the application.properties configuration file
 *
 * @author Anton Kuzmin
 * @since 2024.03.27
 * @see PropertiesImpl
 */

@Repository
public interface Properties {

    /**
     * Secret key for Json Web Token system
     *
     * @return secret key {@code (string)}
     * @author Anton Kuzmin
     * @since 2024.03.27
     */
    String getJwtSecretKey();

    @SuppressWarnings("unused")
    String getSshFmAdminLogin();

    @SuppressWarnings("unused")
    String getSshFmAdminPassword();

    @SuppressWarnings("unused")
    String getSshFmAdminCommand();

}
