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

package cloud.robinzon.backend.data.net.resources.history;

import cloud.robinzon.backend.data.net.resources.NetEntity;
import cloud.robinzon.backend.security.user.resources.UserEntity;
import cloud.robinzon.backend.common.HistoryTemplate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.IdClass;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.sql.Timestamp;

@Entity
@Getter
@NoArgsConstructor
@IdClass(NetHistoryKey.class)
public class NetHistory
        extends HistoryTemplate<NetEntity> {

    @Size(min = 5, max = 15)
    @Column(nullable = false, length = 50)
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

    public NetHistory(NetEntity entity,
                      UserEntity changeBy) {
        this.timestamp = new Timestamp(System.currentTimeMillis());
        this.entity = entity;
        this.changeBy = changeBy;
        this.domain = entity.getDomain();
        this.subnet = entity.getSubnet();
        this.mask = entity.getMask();
        this.dns1 = entity.getDns1();
        this.dns2 = entity.getDns2();
        this.dns3 = entity.getDns3();
        this.cloud = entity.isCloud();
        this.title = entity.getTitle();
        this.description = entity.getDescription();
        this.deleted = entity.isDeleted();
    }

}