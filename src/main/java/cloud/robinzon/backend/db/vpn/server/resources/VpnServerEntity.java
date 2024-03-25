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

package cloud.robinzon.backend.db.vpn.server.resources;

import cloud.robinzon.backend.db.net.resources.NetEntity;
import cloud.robinzon.backend.db.vpn.type.resources.VpnTypeEntity;
import cloud.robinzon.backend.tools.EntityTemplate;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.*;

@Entity
@Getter
@NoArgsConstructor
public class VpnServerEntity
        extends EntityTemplate {

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

    public VpnServerEntity update(String title,
                                  String description,
                                  String ip,
                                  String publicKey,
                                  NetEntity netEntity,
                                  Set<VpnTypeEntity> vpnTypeEntity) {

        if (Objects.equals(this.title, title)
                && Objects.equals(this.description, description)
                && Objects.equals(this.ip, ip)
                && Objects.equals(this.publicKey, publicKey)
                && Objects.equals(this.netEntity.getId(), netEntity.getId())
                && Objects.equals(this.vpnTypeEntity, vpnTypeEntity))
            return null;

        this.title = title;
        this.description = description;
        this.ip = ip;
        this.publicKey = publicKey;
        this.netEntity = netEntity;
        this.vpnTypeEntity = vpnTypeEntity;
        return this;
    }

    public String toString() {
        //noinspection StringBufferReplaceableByString
        StringBuilder sb = new StringBuilder();
        sb.append("[id=").append(id);
        sb.append("][ip=").append(ip);
        sb.append("][publicKey=").append(publicKey);
        sb.append("][netEntityId=").append(netEntity.getId());
        sb.append("][netEntitySubnet=").append(netEntity.getSubnet());
        sb.append("][vpnTypeEntityString=").append(vpnTypeEntity.toString());
        sb.append("][title=").append(title);
        sb.append("][description=").append(description);
        sb.append("][deleted=").append(deleted).append("]");
        return sb.toString();
    }

    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("ip", ip);
        map.put("publicKey", publicKey);
        map.put("netEntityId", netEntity.getId());
        map.put("netEntitySubnet", netEntity.getSubnet());
        map.put("vpnTypeEntityString", vpnTypeEntity.toString());
        map.put("title", title);
        map.put("description", description);
        map.put("deleted", deleted);
        return map;
    }

}
