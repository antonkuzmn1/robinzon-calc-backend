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

package cloud.robinzon.backend.common;


import cloud.robinzon.backend.data.client.resources.ClientEntity;
import cloud.robinzon.backend.security.user.resources.UserEntity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Getter
@MappedSuperclass
@NoArgsConstructor
public abstract class RentTemplate<R> {

    @Id
    @ManyToOne
    @JoinColumn
    protected R entity;

    @Id
    @CreationTimestamp
    protected Timestamp timestamp;

    @ManyToOne
    @JoinColumn
    protected UserEntity changeBy;

    @ManyToOne
    @JoinColumn
    protected ClientEntity renter;

    @SuppressWarnings("unused")
    public void set(R entity,
                        UserEntity changeBy,
                        ClientEntity renter) {
        this.entity = entity;
        this.changeBy = changeBy;
        this.renter = renter;
    }

}
