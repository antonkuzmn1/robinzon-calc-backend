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

package cloud.robinzon.backend.security.user.resources;

import cloud.robinzon.backend.db.client.resources.ClientEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * the main entity
 *
 * @author Anton Kkuzmin
 * @since 2024.03.23
 */

@SuppressWarnings("unused")
@Entity
@Getter
@NoArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, length = 50)
    private String fullName;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(nullable = false)
    private String description;

    @UpdateTimestamp
    private Timestamp timestamp;

    @Setter
    @Column(nullable = false)
    private boolean deleted;

    @Setter
    @Column(nullable = false)
    private boolean admin;

    @Setter
    @ManyToMany(fetch = FetchType.EAGER)
    @JsonIgnoreProperties("users")
    @JoinTable
    private Set<ClientEntity> clients = new HashSet<>();

    public UserEntity(String username,
                      String password,
                      String fullName,
                      String title,
                      String description) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.title = title;
        this.description = description;
        this.clients = null;
        this.deleted = false;
    }

    public void update(String username,
                       String password,
                       String fullName,
                       String title,
                       String description) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.title = title;
        this.description = description;
        this.deleted = false;
    }

}