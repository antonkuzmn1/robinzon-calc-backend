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

package cloud.robinzon.backend.data.client.resources.history;

import cloud.robinzon.backend.data.client.resources.ClientEntity;
import cloud.robinzon.backend.security.user.resources.UserEntity;
import cloud.robinzon.backend.common.HistoryTemplate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.IdClass;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Getter
@NoArgsConstructor
@IdClass(ClientHistoryKey.class)
public class ClientHistory
        extends HistoryTemplate<ClientEntity> {

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

    public ClientHistory(ClientEntity entity,
                         UserEntity changeBy) {
        this.entity = entity;
        this.changeBy = changeBy;
        this.name = entity.getName();
        this.inn = entity.getInn();
        this.discount = entity.getDiscount();
        this.contractNumber = entity.getContractNumber();
        this.contractDate = entity.getContractDate();
        this.title = entity.getTitle();
        this.description = entity.getDescription();
        this.deleted = entity.isDeleted();
    }

}