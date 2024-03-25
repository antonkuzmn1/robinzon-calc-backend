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

package cloud.robinzon.backend.db.vpn.type.resources.history;

import cloud.robinzon.backend.db.vpn.type.resources.VpnTypeEntity;
import cloud.robinzon.backend.security.user.resources.UserEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@SuppressWarnings("unused")
@Entity
@Getter
@NoArgsConstructor
@IdClass(VpnTypeHistoryKey.class)
public class VpnTypeHistory {

    @Id
    @ManyToOne
    @JoinColumn
    private VpnTypeEntity vpnTypeEntity;

    @Id
    @CreationTimestamp
    private Timestamp timestamp;

    @Column(nullable = false, length = 20)
    private String name;

    @ManyToOne
    @JoinColumn(nullable = false)
    private UserEntity changeBy;

    @Column(nullable = false)
    private boolean deleted;

    public VpnTypeHistory(VpnTypeEntity vpnTypeEntity,
                          String name,
                          UserEntity changeBy) {
        this.vpnTypeEntity = vpnTypeEntity;
        this.name = name;
        this.changeBy = changeBy;
        this.deleted = false;
    }

    public VpnTypeHistory(VpnTypeEntity vpnTypeEntity,
                          UserEntity changeBy) {
        this.vpnTypeEntity = vpnTypeEntity;
        this.name = vpnTypeEntity.getName();
        this.changeBy = changeBy;
        this.deleted = true;
    }

}
