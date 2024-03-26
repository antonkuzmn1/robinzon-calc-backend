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
import cloud.robinzon.backend.tools.EntityTemplate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Entity
@Getter
@NoArgsConstructor
public class VpnUserEntity
        extends EntityTemplate {

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

    public VpnUserEntity update(VpnServerEntity vpnServerEntity,
                                VpnTypeEntity vpnTypeEntity,
                                String ip,
                                String username,
                                String password,
                                String fullName,
                                String title,
                                String description) {

        if (Objects.equals(this.vpnServerEntity.getId(), vpnServerEntity.getId())
                && Objects.equals(this.vpnTypeEntity.getId(), vpnTypeEntity.getId())
                && Objects.equals(this.ip, ip)
                && Objects.equals(this.username, username)
                && Objects.equals(this.password, password)
                && Objects.equals(this.fullName, fullName)
                && Objects.equals(this.title, title)
                && Objects.equals(this.description, description))
            return null;

        this.vpnServerEntity = vpnServerEntity;
        this.vpnTypeEntity = vpnTypeEntity;
        this.ip = ip;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.title = title;
        this.description = description;
        return this;
    }

    public String toString() {
        //noinspection StringBufferReplaceableByString
        StringBuilder sb = new StringBuilder();
        sb.append("[id=").append(id);
        sb.append("][vpnServerEntityId=").append(vpnServerEntity.getId());
        sb.append("][vpnServerEntityIp=").append(vpnServerEntity.getIp());
        sb.append("][vpnTypeEntityId=").append(vpnTypeEntity.getId());
        sb.append("][vpnTypeEntityName=").append(vpnTypeEntity.getName());
        sb.append("][ip=").append(ip);
        sb.append("][username=").append(username);
        sb.append("][password=").append(password);
        sb.append("][fullName=").append(fullName);
        sb.append("][title=").append(title);
        sb.append("][description=").append(description);
        sb.append("][deleted=").append(deleted).append("]");
        return sb.toString();
    }

    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("vpnServerEntityId", vpnServerEntity.getId());
        map.put("vpnServerEntityIp", vpnServerEntity.getIp());
        map.put("vpnTypeEntityId", vpnTypeEntity.getId());
        map.put("vpnTypeEntityName", vpnTypeEntity.getName());
        map.put("ip", ip);
        map.put("username", username);
        map.put("password", password);
        map.put("fullName", fullName);
        map.put("title", title);
        map.put("description", description);
        map.put("deleted", deleted);
        return map;
    }

}
