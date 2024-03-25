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

package cloud.robinzon.backend.db.vpn.user.resources;

import cloud.robinzon.backend.db.vpn.server.resources.VpnServerEntity;
import cloud.robinzon.backend.db.vpn.type.resources.VpnTypeEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@SuppressWarnings("unused")
@Entity
@Getter
@NoArgsConstructor
public class VpnUserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @UpdateTimestamp
    private Timestamp timestamp;

    @ManyToOne
    @JoinColumn(nullable = false)
    private VpnServerEntity vpnServerEntity;

    @ManyToOne
    @JoinColumn
    private VpnTypeEntity vpnTypeEntity;

    @Column(nullable = false, length = 15)
    private String ip;

    @Column(nullable = false, length = 50)
    private String username;

    @Column(nullable = false, length = 50)
    private String password;

    @Column(nullable = false, length = 100)
    private String fullName;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(nullable = false)
    private String description;

    @Setter
    @Column(nullable = false)
    private boolean deleted;

    public VpnUserEntity(VpnServerEntity vpnServerEntity,
                         VpnTypeEntity vpnTypeEntity,
                         String ip,
                         String username,
                         String password,
                         String fullName,
                         String title,
                         String description) {
        this.vpnServerEntity = vpnServerEntity;
        this.vpnTypeEntity = vpnTypeEntity;
        this.ip = ip;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.title = title;
        this.description = description;
        this.deleted = false;
    }

    public void update(VpnServerEntity vpnServerEntity,
                       VpnTypeEntity vpnTypeEntity,
                       String ip,
                       String username,
                       String password,
                       String fullName,
                       String title,
                       String description) {
        this.vpnServerEntity = vpnServerEntity;
        this.vpnTypeEntity = vpnTypeEntity;
        this.ip = ip;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.title = title;
        this.description = description;
    }

}
