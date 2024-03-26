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

package cloud.robinzon.backend.data.net.resources;

import cloud.robinzon.backend.data.client.resources.ClientEntity;
import cloud.robinzon.backend.common.EntityTemplate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Entity
@Getter
@NoArgsConstructor
public class NetEntity
        extends EntityTemplate {

    @Setter
    @ManyToOne
    @JoinColumn
    private ClientEntity client;

    @Size(min = 5, max = 50)
    @Column(length = 50)
    private String domain;

    @NonNull
    @Size(min = 7, max = 15)
    @Column(nullable = false, length = 15)
    private String subnet;

    @NonNull
    @Size(min = 7, max = 15)
    @Column(nullable = false, length = 15)
    private String mask;

    @NonNull
    @Size(min = 7, max = 15)
    @Column(nullable = false, length = 15)
    private String dns1;

    @Size(min = 7, max = 15)
    @Column(length = 15)
    private String dns2;

    @Size(min = 7, max = 15)
    @Column(length = 15)
    private String dns3;

    @Column(nullable = false,
            columnDefinition = "boolean default false")
    private boolean cloud;

    public NetEntity update(String domain,
                            String subnet,
                            String mask,
                            String dns1,
                            String dns2,
                            String dns3,
                            boolean cloud,
                            String title,
                            String description) {

        if (Objects.equals(this.domain, domain)
                && Objects.equals(this.subnet, subnet)
                && Objects.equals(this.mask, mask)
                && Objects.equals(this.dns1, dns1)
                && Objects.equals(this.dns2, dns2)
                && Objects.equals(this.dns3, dns3)
                && this.cloud == cloud
                && Objects.equals(this.title, title)
                && Objects.equals(this.description, description))
            return null;

        this.domain = domain;
        this.subnet = subnet;
        this.mask = mask;
        this.dns1 = dns1;
        this.dns2 = dns2;
        this.dns3 = dns3;
        this.cloud = cloud;
        this.title = title;
        this.description = description;
        return this;
    }

    public String toString() {
        //noinspection StringBufferReplaceableByString
        StringBuilder sb = new StringBuilder();
        sb.append("[id=").append(id);
        sb.append("][domain=").append(domain);
        sb.append("][subnet=").append(subnet);
        sb.append("][mask=").append(mask);
        sb.append("][dns1=").append(dns1);
        sb.append("][dns2=").append(dns2);
        sb.append("][dns3=").append(dns3);
        sb.append("][cloud=").append(cloud);
        sb.append("][title=").append(title);
        sb.append("][description=").append(description);
        sb.append("][deleted=").append(deleted).append("]");
        return sb.toString();
    }

    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("domain", domain);
        map.put("subnet", subnet);
        map.put("mask", mask);
        map.put("dns1", dns1);
        map.put("dns2", dns2);
        map.put("dns3", dns3);
        map.put("cloud", cloud);
        map.put("title", title);
        map.put("description", description);
        map.put("deleted", deleted);
        return map;
    }
}