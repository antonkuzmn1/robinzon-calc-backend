/*

Copyright 2024 Anton Kuzmin (https://github.com/antonkuzmn1)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    https://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

*/

package cloud.robinzon.backend.data.fm;

import cloud.robinzon.backend.common.DeleteForm;
import cloud.robinzon.backend.common.RentForm;
import cloud.robinzon.backend.data.fm.resources.FmEntity;
import cloud.robinzon.backend.data.fm.resources.FmEntityRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FmService {

    private FmEntityRepository repository;
    private FmManager manager;

    public List<FmEntity> getAll() {
        return repository.findAll();
    }

    public List<FmEntity> getByVm() {
        return repository.findByVm(true);
    }

    public ResponseEntity<?> insert(FmInsertForm form) {
        return manager.insert(form);
    }

    public ResponseEntity<?> update(FmUpdateForm form) {
        return manager.update(form);
    }

    public ResponseEntity<?> delete(DeleteForm form) {
        return manager.delete(form);
    }

    public ResponseEntity<?> rent(RentForm form) {
        return manager.rent(form);
    }

}