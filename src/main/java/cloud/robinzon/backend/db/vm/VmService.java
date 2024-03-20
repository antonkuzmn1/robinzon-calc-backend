/**
 * Copyright 2024 Anton Kuzmin (http://github.com/antonkuzmn1)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cloud.robinzon.backend.db.vm;

import org.springframework.stereotype.Service;

@Service
public class VmService {

    /*
     private VmEntityRepository vmRepository;
     private FmService fmService;
     private FmSshService fmSshService;
     public VmService(
             VmEntityRepository vmRepository,
             FmService fmService,
             FmSshService fmSshService) {
         this.vmRepository = vmRepository;
         this.fmService = fmService;
         this.fmSshService = fmSshService;
     }
     private ObjectMapper objectMapper = new ObjectMapper();
     public List<VmEntity> getAll() {
         System.out.println("[VmService][getVm]");
         return vmRepository.findAll();
     }
     public boolean updateAll() throws JSchException, IOException {
         System.out.println("[VmService][updateAll]");
         List<FmEntity> fmList = fmService.getByVm(true);
         List<VmEntity> vmList = new ArrayList<>();
         for (FmEntity fm : fmList)
             for (JsonNode json : objectMapper.readTree(fmSshService.get(fm.getIp())))
                 vmList.add(new VmEntity(
                         json.get("vm_id").toString().replace("\"", ""),
                         json.get("vm_name").toString().replace("\"", ""),
                         json.get("vm_cpu").asInt(),
                         json.get("vm_ram").asInt(),
                         json.get("vm_ssd").asInt(),
                         json.get("vm_hdd").asInt(),
                         json.get("vm_state").asBoolean(),
                         fm));
         vmRepository.saveAll(vmList);
         return true;
     }
    */

}
