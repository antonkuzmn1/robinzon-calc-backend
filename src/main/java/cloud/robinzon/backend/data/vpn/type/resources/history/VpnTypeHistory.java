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

package cloud.robinzon.backend.data.vpn.type.resources.history;

import cloud.robinzon.backend.data.vpn.type.resources.VpnTypeEntity;
import cloud.robinzon.backend.security.user.resources.UserEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Getter
@NoArgsConstructor
@IdClass(VpnTypeHistoryKey.class)
public class VpnTypeHistory {

    @Id
    @ManyToOne
    @JoinColumn
    private VpnTypeEntity entity;

    @Id
    private Timestamp timestamp;

    @ManyToOne
    @JoinColumn
    private UserEntity changeBy;

    @Setter
    @Column(nullable = false,
            columnDefinition = "boolean default false")
    private boolean deleted;

    @Column(nullable = false, length = 20)
    private String name;

    public VpnTypeHistory(VpnTypeEntity entity,
                          UserEntity changeBy) {
        this.timestamp = new Timestamp(System.currentTimeMillis());
        this.entity = entity;
        this.changeBy = changeBy;
        this.name = entity.getName();
        this.deleted = entity.isDeleted();
    }

}
