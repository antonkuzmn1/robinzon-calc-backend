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

package cloud.robinzon.backend.data.vpn.type.resources;

import cloud.robinzon.backend.data.vpn.server.resources.VpnServerEntity;
import cloud.robinzon.backend.data.vpn.server.resources.history.VpnServerHistory;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import java.security.Timestamp;
import java.util.*;

@Entity
@Getter
@NoArgsConstructor
public class VpnTypeEntity {

    @SuppressWarnings("unused")
    @Id
    private Long id;

    @Column(nullable = false, length = 20)
    private String name;

    @SuppressWarnings("unused")
    @UpdateTimestamp
    private Timestamp timestamp;

    @Setter
    @Column(nullable = false,
            columnDefinition = "boolean default false")
    private boolean deleted;

    @ManyToMany(mappedBy = "vpnTypeEntity")
    @JsonIgnoreProperties("vpnTypeEntity")
    private final Set<VpnServerEntity> vpnServerEntity = new HashSet<>();

    @ManyToMany(mappedBy = "vpnTypeEntity")
    @JsonIgnoreProperties("vpnTypeEntity")
    private final Set<VpnServerHistory> vpnServerHistory = new HashSet<>();

    public VpnTypeEntity(String name) {
        this.name = name;
    }

    public VpnTypeEntity update(String name) {

        if (Objects.equals(this.name, name))
            return null;

        this.name = name;
        return this;
    }

    public String toString() {
        //noinspection StringBufferReplaceableByString
        StringBuilder sb = new StringBuilder();
        sb.append("[id=").append(id);
        sb.append("][name=").append(name);
        sb.append("][deleted=").append(deleted).append("]");
        return sb.toString();
    }

    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("name", name);
        map.put("deleted", deleted);
        return map;
    }

}