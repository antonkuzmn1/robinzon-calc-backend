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

package cloud.robinzon.backend.db.fm.resources;

import cloud.robinzon.backend.db.client.resources.ClientEntity;
import cloud.robinzon.backend.tools.EntityTemplate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Entity
@Getter
@NoArgsConstructor
public class FmEntity
        extends EntityTemplate {

    @Setter
    @ManyToOne
    @JoinColumn
    private ClientEntity client;

    @Size(min = 2, max = 50)
    @Column(nullable = false, length = 50)
    private String name;

    @Size(min = 7, max = 15)
    @Column(length = 15)
    private String ip;

    @Column(nullable = false)
    private String specifications;

    @Min(0)
    @Max(99999)
    @Column(nullable = false,
            columnDefinition = "int default 0")
    private int price;

    @Column(nullable = false,
            columnDefinition = "boolean default false")
    private boolean vm;

    public FmEntity update(String name,
                           String ip,
                           String title,
                           String specifications,
                           String description,
                           int price,
                           boolean vm) {

        if (Objects.equals(this.name, name)
                && Objects.equals(this.ip, ip)
                && Objects.equals(this.specifications, specifications)
                && this.price == price
                && this.vm == vm
                && Objects.equals(this.title, title)
                && Objects.equals(this.description, description))
            return null;

        this.name = name;
        this.ip = ip;
        this.title = title;
        this.specifications = specifications;
        this.description = description;
        this.price = price;
        this.vm = vm;
        return this;
    }

    public String toString() {
        //noinspection StringBufferReplaceableByString
        StringBuilder sb = new StringBuilder();
        sb.append("[id=").append(id);
        sb.append("][name=").append(name);
        sb.append("][ip=").append(ip);
        sb.append("][specifications=").append(specifications);
        sb.append("][price=").append(price);
        sb.append("][vm=").append(vm);
        sb.append("][title=").append(title);
        sb.append("][description=").append(description);
        sb.append("][deleted=").append(deleted).append("]");
        return sb.toString();
    }

    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("name", name);
        map.put("ip", ip);
        map.put("specifications", specifications);
        map.put("price", price);
        map.put("vm", vm);
        map.put("title", title);
        map.put("description", description);
        map.put("deleted", deleted);
        return map;
    }
}
