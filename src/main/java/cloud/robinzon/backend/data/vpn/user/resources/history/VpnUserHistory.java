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

package cloud.robinzon.backend.data.vpn.user.resources.history;

import cloud.robinzon.backend.data.vpn.server.resources.VpnServerEntity;
import cloud.robinzon.backend.data.vpn.user.resources.VpnUserEntity;
import cloud.robinzon.backend.security.user.resources.UserEntity;
import cloud.robinzon.backend.data.vpn.type.resources.VpnTypeEntity;
import cloud.robinzon.backend.common.HistoryTemplate;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Getter
@NoArgsConstructor
@IdClass(VpnUserHistoryKey.class)
public class VpnUserHistory
        extends HistoryTemplate<VpnUserEntity> {

    @ManyToOne
    @JoinColumn(nullable = false)
    private VpnServerEntity vpnServerEntity;

    @ManyToOne
    @JoinColumn(nullable = false)
    private VpnTypeEntity vpnTypeEntity;

    @Size(min = 7, max = 15)
    @Column(nullable = false, length = 15)
    private String ip;

    @Size(min = 2, max = 50)
    @Column(nullable = false, length = 50)
    private String username;

    @Size(min = 5, max = 50)
    @Column(nullable = false, length = 50)
    private String password;

    @Size(min = 2, max = 100)
    @Column(nullable = false, length = 100)
    private String fullName;

    public VpnUserHistory(VpnUserEntity entity,
                          UserEntity changeBy) {
        this.timestamp = new Timestamp(System.currentTimeMillis());
        this.entity = entity;
        this.changeBy = changeBy;
        this.vpnServerEntity = entity.getVpnServerEntity();
        this.vpnTypeEntity = entity.getVpnTypeEntity();
        this.ip = entity.getIp();
        this.username = entity.getUsername();
        this.password = entity.getPassword();
        this.fullName = entity.getFullName();
        this.title = entity.getTitle();
        this.description = entity.getDescription();
        this.deleted = entity.isDeleted();
    }

}
