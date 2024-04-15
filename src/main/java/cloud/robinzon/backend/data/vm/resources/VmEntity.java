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

package cloud.robinzon.backend.data.vm.resources;

import cloud.robinzon.backend.data.client.resources.ClientEntity;
import cloud.robinzon.backend.data.fm.resources.FmEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Entity
@Getter
@NoArgsConstructor
public class VmEntity {

    @Id
    public String id; // replaced cuz string

    public Timestamp timestamp;

    @Setter
    @Column(nullable = false,
            columnDefinition = "boolean default false")
    public boolean deleted;

    @Column(nullable = false, length = 50)
    public String title;

    @Column(nullable = false)
    public String description;

    // end of template

    @Setter
    @ManyToOne
    @JoinColumn
    public ClientEntity clientEntity;

    @Size(min = 2, max = 100)
    @Column(nullable = false, length = 100)
    public String name;

    @Min(0)
    @Max(64)
    @Column(nullable = false)
    public int cpu;

    @Min(0)
    @Max(128)
    @Column(nullable = false)
    public int ram;

    @Min(0)
    @Max(20000)
    @Column(nullable = false)
    public int ssd;

    @Min(0)
    @Max(50000)
    @Column(nullable = false)
    public int hdd;

    @Column(nullable = false)
    public boolean running;

    @ManyToOne
    @JoinColumn(nullable = false)
    public FmEntity fmEntity;

    public VmEntity(String id) {
        this.id = id;
    }

    public VmEntity update(String name,
                           int cpu,
                           int ram,
                           int ssd,
                           int hdd,
                           boolean running,
                           FmEntity fmEntity,
                           String title,
                           String description) {

        if (Objects.equals(this.name, name)
                && this.cpu == cpu
                && this.ram == ram
                && this.ssd == ssd
                && this.hdd == hdd
                && this.running == running
                && Objects.equals(this.fmEntity.getId(), fmEntity.getId())
                && Objects.equals(this.title, title)
                && Objects.equals(this.description, description)) {
            return null;
        }

        this.name = name;
        this.cpu = cpu;
        this.ram = ram;
        this.ssd = ssd;
        this.hdd = hdd;
        this.running = running;
        this.fmEntity = fmEntity;
        this.title = title;
        this.description = description;
        return this;
    }

    public String toString() {
        //noinspection StringBufferReplaceableByString
        StringBuilder sb = new StringBuilder();
        sb.append("[id=").append(id);
        sb.append("][cpu=").append(cpu);
        sb.append("][ram=").append(ram);
        sb.append("][ssd=").append(ssd);
        sb.append("][hdd=").append(hdd);
        sb.append("][running=").append(running);
        sb.append("][fmEntityId=").append(fmEntity.getId());
        sb.append("][fmEntityName=").append(fmEntity.getName());
        sb.append("][title=").append(title);
        sb.append("][description=").append(description);
        sb.append("][deleted=").append(deleted).append("]");
        return sb.toString();
    }

    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("name", name);
        map.put("cpu", cpu);
        map.put("ram", ram);
        map.put("ssd", ssd);
        map.put("hdd", hdd);
        map.put("running", running);
        map.put("fmEntityId", fmEntity.getId());
        map.put("fmEntityName", fmEntity.getName());
        map.put("title", title);
        map.put("description", description);
        map.put("deleted", deleted);
        return map;
    }

}