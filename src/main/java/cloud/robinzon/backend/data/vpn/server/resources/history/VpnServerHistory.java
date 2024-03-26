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

package cloud.robinzon.backend.data.vpn.server.resources.history;

import cloud.robinzon.backend.data.net.resources.NetEntity;
import cloud.robinzon.backend.data.vpn.server.resources.VpnServerEntity;
import cloud.robinzon.backend.data.vpn.type.resources.VpnTypeEntity;
import cloud.robinzon.backend.security.user.resources.UserEntity;
import cloud.robinzon.backend.common.HistoryTemplate;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor
@IdClass(VpnServerHistoryKey.class)
public class VpnServerHistory
        extends HistoryTemplate<VpnServerEntity> {

    @Size(min = 7, max = 15)
    @Column(nullable = false, length = 15)
    private String ip;

    @Column(length = 50)
    private String publicKey;

    @ManyToOne
    @JoinColumn(nullable = false)
    private NetEntity netEntity;

    @ManyToMany
    @JsonIgnoreProperties("vpnServerHistory")
    @JoinTable
    private Set<VpnTypeEntity> vpnTypeEntity = new HashSet<>();

    public VpnServerHistory(VpnServerEntity entity,
                            UserEntity changeBy) {
        this.entity = entity;
        this.changeBy = changeBy;
        this.title = entity.getTitle();
        this.description = entity.getDescription();
        this.ip = entity.getIp();
        this.publicKey = entity.getPublicKey();
        this.netEntity = entity.getNetEntity();
        this.vpnTypeEntity = entity.getVpnTypeEntity();
        this.deleted = entity.isDeleted();
    }

}