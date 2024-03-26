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
import cloud.robinzon.backend.tools.HistoryTemplate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.IdClass;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * History class-entity
 *
 * @author Anton Kuzmin
 * @since 2024.03.25
 */

@Entity
@Getter
@NoArgsConstructor
@IdClass(UserHistoryKey.class)
public class UserHistory
        extends HistoryTemplate<UserEntity> {

    @Size(min = 2, max = 50)
    @Column(nullable = false, length = 50)
    private String username;

    @Size(min = 60, max = 60)
    @Column(nullable = false, length = 60)
    private String password;

    @Size(min = 2, max = 50)
    @Column(nullable = false, length = 50)
    private String fullName;

    public UserHistory(UserEntity entity,
                       UserEntity changeBy) {
        this.entity = entity;
        this.changeBy = changeBy;
        this.username = entity.getUsername();
        this.password = entity.getPassword();
        this.fullName = entity.getFullName();
        this.title = entity.getTitle();
        this.description = entity.getDescription();
        this.deleted = entity.isDeleted();
    }

}
