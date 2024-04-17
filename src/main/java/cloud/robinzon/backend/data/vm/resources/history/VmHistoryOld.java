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

package cloud.robinzon.backend.data.vm.resources.history;

//import cloud.robinzon.backend.data.fm.resources.FmEntity;
//import cloud.robinzon.backend.data.vm.resources.VmEntity;
//import cloud.robinzon.backend.security.user.resources.UserEntity;
//import cloud.robinzon.backend.common.HistoryTemplate;
//import jakarta.persistence.*;
//import jakarta.validation.constraints.Max;
//import jakarta.validation.constraints.Min;
//import jakarta.validation.constraints.Size;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//
//import java.sql.Timestamp;
//
//@Entity
//@Getter
//@NoArgsConstructor
//@IdClass(VmHistoryKey.class)
//public class VmHistoryOld
//        extends HistoryTemplate<VmEntity> {
//
//    @Size(min = 2, max = 100)
//    @Column(nullable = false, length = 100)
//    private String name;
//
//    @Min(0)
//    @Max(64)
//    @Column(nullable = false)
//    private int cpu;
//
//    @Min(0)
//    @Max(128)
//    @Column(nullable = false)
//    private int ram;
//
//    @Min(0)
//    @Max(20000)
//    @Column(nullable = false)
//    private int ssd;
//
//    @Min(0)
//    @Max(50000)
//    @Column(nullable = false)
//    private int hdd;
//
//    @Column(nullable = false)
//    private boolean running;
//
//    @ManyToOne
//    @JoinColumn(nullable = false)
//    private FmEntity fmEntity;
//
//    public VmHistoryOld(VmEntity entity,
//                        UserEntity changeBy) {
//        this.timestamp = new Timestamp(System.currentTimeMillis());
//        this.entity = entity;
//        this.changeBy = changeBy;
//        this.name = entity.getName();
//        this.cpu = entity.getCpu();
//        this.ram = entity.getRam();
//        this.ssd = entity.getSsd();
//        this.hdd = entity.getHdd();
//        this.running = entity.isRunning();
//        this.title = entity.getTitle();
//        this.description = entity.getDescription();
//        this.fmEntity = entity.getFmEntity();
//        this.deleted = entity.isDeleted();
//    }
//
//}