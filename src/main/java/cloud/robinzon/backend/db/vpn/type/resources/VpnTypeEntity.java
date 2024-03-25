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

package cloud.robinzon.backend.db.vpn.type.resources;

import cloud.robinzon.backend.db.vpn.server.resources.VpnServerEntity;
import cloud.robinzon.backend.db.vpn.server.resources.history.VpnServerHistory;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import java.security.Timestamp;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("unused")
@Entity
@Getter
@NoArgsConstructor
public class VpnTypeEntity {

    @Id
    private Long id;

    @Column(nullable = false, length = 20)
    private String name;

    @UpdateTimestamp
    private Timestamp timestamp;

    @Setter
    @Column(nullable = false)
    private boolean deleted;

    @ManyToMany(mappedBy = "vpnTypeEntity")
    @JsonIgnoreProperties("vpnTypeEntity")
    private final Set<VpnServerEntity> vpnServerEntity = new HashSet<>();

    @ManyToMany(mappedBy = "vpnTypeEntity")
    @JsonIgnoreProperties("vpnTypeEntity")
    private final Set<VpnServerHistory> vpnServerHistory = new HashSet<>();

    public VpnTypeEntity(String name) {
        this.name = name;
        this.deleted = false;
    }

    public void update(String name) {
        this.name = name;
    }

}