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

package cloud.robinzon.backend.db.client.resources;

import cloud.robinzon.backend.security.user.resources.UserEntity;
import cloud.robinzon.backend.tools.EntityTemplate;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

@Entity
@Getter
@NoArgsConstructor
public class ClientEntity
        extends EntityTemplate {

    @Size(min = 2, max = 50)
    @Column(nullable = false, length = 50)
    private String name;

    @Size(min = 8, max = 12)
    @Column(length = 12)
    private String inn;

    @Min(0)
    @Max(100)
    @Column(nullable = false)
    private int discount;

    @Column
    private int contractNumber;

    @Column
    private Date contractDate;

    @Column(nullable = false,
            columnDefinition = "int default 0")
    private int balance;

    @Setter
    @ManyToMany(mappedBy = "clients")
    @JsonIgnoreProperties("clients")
    private Set<UserEntity> users = new HashSet<>();

    public ClientEntity update(String name,
                               String inn,
                               int discount,
                               int contractNumber,
                               Date contractDate,
                               String title,
                               String description) {

        if (Objects.equals(this.name, name)
                && Objects.equals(this.inn, inn)
                && this.discount == discount
                && this.contractNumber == contractNumber
                && Objects.equals(this.title, title)
                && Objects.equals(this.description, description))
            return null;

        this.name = name;
        this.inn = inn;
        this.discount = discount;
        this.contractNumber = contractNumber;
        this.contractDate = contractDate;
        this.title = title;
        this.description = description;
        return this;
    }

    public String toString() {
        //noinspection StringBufferReplaceableByString
        StringBuilder sb = new StringBuilder();
        sb.append("[id=").append(id);
        sb.append("][domain=").append(name);
        sb.append("][subnet=").append(inn);
        sb.append("][mask=").append(discount);
        sb.append("][dns1=").append(contractNumber);
        sb.append("][title=").append(title);
        sb.append("][description=").append(description);
        sb.append("][deleted=").append(deleted).append("]");
        return sb.toString();
    }

    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("domain", name);
        map.put("subnet", inn);
        map.put("mask", discount);
        map.put("dns1", contractDate);
        map.put("title", title);
        map.put("description", description);
        map.put("deleted", deleted);
        return map;
    }
}
