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

package cloud.robinzon.backend.data.vm.resources.price;

import cloud.robinzon.backend.security.user.resources.UserEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity
@Getter
@NoArgsConstructor
public class VmPrice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    private Timestamp timestamp;

    @ManyToOne
    private UserEntity changeBy;

    @Min(0)
    @Max(999)
    @Column(nullable = false)
    private int cpu;

    @Min(0)
    @Max(999)
    @Column(nullable = false)
    private int ram;

    @Min(0)
    @Max(99)
    @Column(nullable = false)
    private int ssd;

    @Min(0)
    @Max(99)
    @Column(nullable = false)
    private int hdd;

    @SuppressWarnings("unused")
    public VmPrice(UserEntity changeBy,
                   int cpu,
                   int ram,
                   int ssd,
                   int hdd) {
        this.changeBy = changeBy;
        this.cpu = cpu;
        this.ram = ram;
        this.ssd = ssd;
        this.hdd = hdd;
    }

}
