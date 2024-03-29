/**
 * Copyright 2024 Anton Kuzmin (http://github.com/antonkuzmn1)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cloud.robinzon.backend.data.fm;

import cloud.robinzon.backend.data.fm.resources.FmEntity;
import cloud.robinzon.backend.data.fm.resources.FmEntityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FmService {

    private FmEntityRepository fmRepository;
    private FmManager fmManager;

    public List<FmEntity> getAll() {
        System.out.println("[FmService][getAll]");
        return fmRepository.findAll();
    }

    @SuppressWarnings("unused")
    public List<FmEntity> getByVm(Boolean vm) {
        System.out.println("[FmService][getByVm]");
        return fmRepository.findByVm(vm);
    }

    @SuppressWarnings("unused")
    public void test(String token) {
        System.out.println("[FmService][test]");
        fmManager
                .insert("TEST",
                        "000.000.000.000",
                        "",
                        "",
                        "",
                        0,
                        false,
                        token);
    }

}