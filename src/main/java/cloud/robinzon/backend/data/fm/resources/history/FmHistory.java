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

package cloud.robinzon.backend.data.fm.resources.history;

import cloud.robinzon.backend.data.fm.resources.FmEntity;
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

import java.sql.Timestamp;

@Entity
@Getter
@NoArgsConstructor
@IdClass(FmHistoryKey.class)
public class FmHistory
        extends HistoryTemplate<FmEntity> {

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

    public FmHistory(FmEntity entity,
                     UserEntity changeBy) {
        this.timestamp = new Timestamp(System.currentTimeMillis());
        this.entity = entity;
        this.changeBy = changeBy;
        this.name = entity.getName();
        this.ip = entity.getIp();
        this.title = entity.getTitle();
        this.specifications = entity.getSpecifications();
        this.description = entity.getDescription();
        this.price = entity.getPrice();
        this.vm = entity.isVm();
        this.deleted = entity.isDeleted();
    }

}