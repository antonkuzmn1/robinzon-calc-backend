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

import cloud.robinzon.backend.security.user.resources.UserEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Getter
@MappedSuperclass
public abstract class HistoryTemplate<T> {

    @Id
    @ManyToOne
    @JoinColumn
    protected T entity;

    @Id
    @CreationTimestamp
    protected Timestamp timestamp;

    @ManyToOne
    @JoinColumn
    protected UserEntity changeBy;

    @Setter
    @Column(nullable = false,
            columnDefinition = "boolean default false")
    protected boolean deleted;

    @Column(nullable = false, length = 50)
    protected String title;

    @Column(nullable = false)
    protected String description;

}
