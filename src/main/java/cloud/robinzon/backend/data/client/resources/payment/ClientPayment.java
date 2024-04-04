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

package cloud.robinzon.backend.data.client.resources.payment;

import cloud.robinzon.backend.data.client.resources.ClientEntity;
import cloud.robinzon.backend.security.user.resources.UserEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Getter
@NoArgsConstructor
@IdClass(ClientPaymentKey.class)
public class ClientPayment {

    @Id
    @ManyToOne
    @JoinColumn
    private ClientEntity entity;

    @Id
    private Timestamp timestamp;

    @Column(nullable = false)
    private int balance;

    @ManyToOne
    @JoinColumn
    private UserEntity changeBy;

    public ClientPayment(ClientEntity entity,
                         int balance,
                         UserEntity changeBy) {
        this.timestamp = new Timestamp(System.currentTimeMillis());
        this.entity = entity;
        this.balance = balance;
        this.changeBy = changeBy;
    }

}