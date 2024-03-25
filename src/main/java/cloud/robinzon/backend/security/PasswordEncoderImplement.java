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

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * implementation of an inherited class
 *
 * @author Anton Kuzmin
 * @since 2024.03.23
 */

@Service
public class
PasswordEncoderImplement
        implements PasswordEncoder {

    /**
     * Encode the raw password. Generally, a good encoding algorithm applies an SHA-1 or
     * greater hash combined with an 8-byte or greater randomly generated salt.
     *
     * @param rawPassword raw password for encode
     */
    @Override
    public String
    encode(CharSequence rawPassword) {
        return null;
    }

    /**
     * Verify the encoded password obtained from storage matches the submitted raw
     * password after it too is encoded. Returns true if the passwords match, false if
     * they do not. The stored password itself is never decoded.
     *
     * @param rawPassword     the raw password to encode and match
     * @param encodedPassword the encoded password from storage to compare with
     * @return true if the raw password, after encoding, matches the encoded password from
     * storage
     */
    @Override
    public boolean
    matches(CharSequence rawPassword,
            String encodedPassword) {
        return false;
    }
}