/**
 * Copyright 2024 Anton Kuzmin http://github.com/antonkuzmn1
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

package cloud.robinzon.backend.tools;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * <h3>String Templates for Response Form</h3>
 * <p>
 * This interface has string build templates for all occasions.
 * </p>
 * <p>
 * Contains the next methods:
 * </p>
 * <p>
 * {@code inserted} - if new entity has been successfully inserted;
 * </p>
 * <p>
 * {@code updated} - if entity has been successfully updated;
 * </p>
 * <p>
 * {@code deleted} - if entity has been successfully deleted;
 * </p>
 * <p>
 * Read more in the attached documents for each method.
 * </p>
 *
 * @author Anton Kuzmin
 * @since 2024.03.13
 * @since 2024.03.21
 */

public interface ResponseStringTemplates {

    /**
     * String builder is used in each of the functions of this class,
     * so it is based at the root.
     */
    StringBuilder sb = new StringBuilder();

    /**
     * <h3>Check for unique</h3>
     * <p>
     * Use this pattern if the value must be unique.
     * </p>
     * <p>
     * Checking for the presence of an entity in the database.
     * If the entity is not in the database,
     * a message about a non-existent entity will be added to the error list
     * and the function will be interrupted.
     * </p>
     * <p>
     * false - is unique;
     * </p>
     * <p>
     * true - isn't unique;
     * </p>
     * </p>
     *
     * @param result     - result of checking;
     * @param columnName - the column name;
     * @param value      - the value;
     * @return A ready template string.
     * @author Anton Kuzmin
     * @since 2024.03.18
     */
    default String setUnique(boolean result, String columnName, Object value) {
        return !result ? ""
                : sb
                .append("\nEntity with ")
                .append(columnName)
                .append(" ")
                .append(value)
                .append(" already exists")
                .toString();
    }

    /**
     * <h3>Check for equals</h3>
     * <p>
     * Use this pattern if the value must be unique.
     * </p>
     * <p>
     * Compare each entity parameter with the new input data.
     * If all the data is the same,
     * then there is no point in making changes
     * and adding a new entry to the history,
     * so a new message will be added about this event as an error.
     * </p>
     * <p>
     * false - is unique;
     * </p>
     * <p>
     * true - isn't unique;
     * </p>
     * </p>
     *
     * @param result - result of checking;
     * @return A ready template string.
     * @author Anton Kuzmin
     * @since 2024.03.18
     */
    default String setEquals(boolean result, String EntityName) {
        return !result ? ""
                : sb
                .append("\nAll params of ")
                .append(EntityName)
                .append(" is equal")
                .toString();
    }

    /**
     * <h3>Find entity</h3>
     * <p>
     * Use this pattern if the entity cannot be null.
     * </p>
     * <p>
     * Checking for the presence of an entity in the database.
     * If the entity is not in the database,
     * a message about a non-existent entity will be added to the error list
     * and the function will be interrupted.
     * </p>
     *
     * @return A ready template string.
     * @author Anton Kuzmin
     * @since 2024.03.18
     */
    default String setFound(Object entity, Object id) {
        return entity != null ? ""
                : sb
                .append("\nEntity with id ")
                .append(id)
                .append(" not found")
                .toString();
    }

    /**
     * <h3>Deleted entity</h3>
     * <p>
     * Use this pattern if the entity cannot be deleted.
     * </p>
     * <p>
     * Compare each entity parameter with the new input data.
     * If all the data is the same,
     * then there is no point in making changes
     * and adding a new entry to the history,
     * so a new message will be added about this event as an error.
     * </p>
     *
     * @return A ready template string.
     * @author Anton Kuzmin
     * @since 2024.03.18
     */
    default String setDeleted(boolean isDeleted, Object id) {
        return !isDeleted ? ""
                : sb
                .append("\nEntity with id ")
                .append(id)
                .append(" already deleted")
                .toString();
    }

    default boolean hasIsDeletedMethod(Object obj) {
        try {
            obj.getClass().getMethod("isDeleted");
            return true;
        } catch (NoSuchMethodException e) {
            return false;
        }
    }

    /**
     * <h3>Template for method "delete"</h3>
     * <p>
     * During checks, all error messages will be accumulated here,
     * at the end of the checks the length of this object
     * will be checked and if it is greater than zero,
     * the response form will be returned with the status error
     * </p>
     *
     * @author Anton Kuzmin
     * @since 2024.03.19
     */
    default String deleteChecks(Object entity, Long id) throws NoSuchMethodException {
        try {
            if (!hasIsDeletedMethod(entity)) throw new NoSuchMethodException("Method isDeleted not found");

            Method method = entity.getClass().getMethod("isDeleted");
            return String.join("",
                    id == null ? "id cannot be null" : "",
                    Objects.requireNonNull(id) < 1 ? "id cannot be less than 1" : "",
                    setFound(entity, id),
                    setDeleted((boolean) method.invoke(entity), id));

        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new NoSuchMethodException(e.toString());
        }
    }

}
