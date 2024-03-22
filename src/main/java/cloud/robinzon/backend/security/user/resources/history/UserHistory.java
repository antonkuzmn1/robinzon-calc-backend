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

package cloud.robinzon.backend.security.user.resources.history;

import cloud.robinzon.backend.security.user.resources.UserEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

/**
 * History class-entity
 *
 * @author Anton Kuzmin
 * @since 2024.03.23
 */

@SuppressWarnings("unused")
@Entity
@Getter
@NoArgsConstructor
@IdClass(UserHistoryKey.class)
public class UserHistory {

    @Id
    @ManyToOne
    @JoinColumn
    private UserEntity userEntity;

    @Id
    @CreationTimestamp
    private Timestamp timestamp;

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

    @ManyToOne
    @JoinColumn
    private UserEntity changeBy;

    @Column(nullable = false)
    private boolean deleted;

    public UserHistory(UserEntity userEntity,
                       String username,
                       String password,
                       String fullName,
                       String title,
                       String description,
                       UserEntity changeBy) {
        this.userEntity = userEntity;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.title = title;
        this.description = description;
        this.changeBy = changeBy;
        this.deleted = false;
    }

    public UserHistory(UserEntity userEntity,
                       UserEntity changeBy) {
        this.userEntity = userEntity;
        this.username = userEntity.getUsername();
        this.password = userEntity.getPassword();
        this.fullName = userEntity.getFullName();
        this.title = userEntity.getTitle();
        this.description = userEntity.getDescription();
        this.changeBy = changeBy;
        this.deleted = false;
    }

}
