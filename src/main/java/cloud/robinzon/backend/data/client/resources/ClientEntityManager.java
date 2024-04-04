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

package cloud.robinzon.backend.data.client.resources;

import cloud.robinzon.backend.common.DeleteForm;
import cloud.robinzon.backend.data.client.ClientInsertForm;
import cloud.robinzon.backend.data.client.ClientManager;
import cloud.robinzon.backend.data.client.ClientUpdateForm;
import cloud.robinzon.backend.data.client.resources.history.ClientHistory;
import cloud.robinzon.backend.data.client.resources.history.ClientHistoryRepository;
import cloud.robinzon.backend.security.jwt.JwtUtil;
import cloud.robinzon.backend.security.user.resources.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;

import static cloud.robinzon.backend.common.Allow.check;
import static cloud.robinzon.backend.common.Log.*;

/**
 * <h3>Entity Management Tools</h3>
 * <p>
 * This class handles the management of Entities in the database.
 * </p>
 * <p>
 * Contains the next methods:
 * </p>
 * <p>
 * {@code insert} - for insert new entity
 * </p>
 * <p>
 * {@code update} - for update an existing entity
 * </p>
 * <p>
 * {@code delete} - for delete the entity
 * </p>
 * <p>
 * Read more in the attached documents for each method.
 * </p>
 *
 * @author Anton Kuzmin
 * @see ClientManager
 * @since 2024.03.25
 */

@Service
@AllArgsConstructor
public class ClientEntityManager {

    private final ClientEntityRepository entityRepository;
    private final ClientHistoryRepository historyRepository;
    private final JwtUtil jwtUtil;

    /**
     * Returns a ResponseEntity with status code 200 (OK) and the updated NetEntity as the response body.
     * Logs the new values of the entity, saves the entity to the repository, saves a new entry in the history repository,
     * and logs the success message.
     *
     * @param entity   the updated entity
     * @param changeBy the UserEntity making the change
     * @return a ResponseEntity with status code 200 (OK) and the updated NetEntity as the response body
     * @author Anton Kuzmin
     * @since 2024.03.25
     */
    private ResponseEntity<?> ok(ClientEntity entity,
                                 UserEntity changeBy) {
        log("New values:");
        System.out.println(entity.toMap());

        log("Saving entity...");
        entityRepository.save(entity);

        log("Saving history...");
        historyRepository.save(new ClientHistory(entity, changeBy));

        log("Success!");
        return ResponseEntity.ok().body(entity);
    }

    public ResponseEntity<?> insert(ClientInsertForm form) {
        String name = form.getName();
        String token = form.getToken();
        String inn = form.getInn();
        int contractNumber = form.getContractNumber();
        int discount = form.getDiscount();
        Date contractDate = form.getContractDate();
        String title = form.getTitle();
        String description = form.getDescription();

        set(getClass(), "insert");
        log(String.join(" ", "Insert:", name));

        UserEntity changeBy = check(jwtUtil, token);
        if (changeBy == null) return err("Access denied");

        log("Checks...");
        if (entityRepository.checkUniqueInn(inn))
            return err("INN must be unique");
        if (entityRepository.checkUniqueContractNumber(contractNumber))
            return err("Contract number must be unique");

        ClientEntity entity = new ClientEntity()
                .update(name,
                        inn,
                        discount,
                        contractNumber,
                        contractDate,
                        title,
                        description);

        return ok(entity, changeBy);
    }

    public ResponseEntity<?> update(ClientUpdateForm form) {
        Long id = form.getId();
        String name = form.getName();
        String inn = form.getInn();
        int discount = form.getDiscount();
        int contractNumber = form.getContractNumber();
        Date contractDate = form.getContractDate();
        String title = form.getTitle();
        String description = form.getDescription();
        String token = form.getToken();

        set(getClass(), "update");
        log(String.join(" ", "Update:", name));

        UserEntity changeBy = check(jwtUtil, token);
        if (changeBy == null) return err("Access denied");

        log("Entity search...");
        ClientEntity entity = entityRepository.findById(id).orElse(null);
        if (entity == null) return err("Entity not found");

        log("Current values:");
        System.out.println(entity.toMap());

        log("Checks...");
        if (!entity.getInn().equals(inn) && entityRepository.checkUniqueInn(inn))
            return err("INN must be unique");
        if (!(entity.getContractNumber() == contractNumber) && entityRepository.checkUniqueContractNumber(contractNumber))
            return err("Contract number must be unique");

        if (entity
                .update(name,
                        inn,
                        discount,
                        contractNumber,
                        contractDate,
                        title,
                        description) == null)
            return err("All parameters are equal");

        return ok(entity, changeBy);
    }

    public ResponseEntity<?> delete(DeleteForm form) {
        Long id = form.getId();
        String token = form.getToken();

        set(getClass(), "delete");

        UserEntity changeBy = check(jwtUtil, token);
        if (changeBy == null) return err("Access denied");

        log("Entity search...");
        ClientEntity entity = entityRepository.findById(id).orElse(null);
        if (entity == null) return err("Entity not found");

        log("Current values:");
        System.out.println(entity.toMap());

        log("Checks...");
        if (entity.isDeleted())
            return err("Entity already deleted");

        entity.setDeleted(true);

        return ok(entity, changeBy);
    }

}
