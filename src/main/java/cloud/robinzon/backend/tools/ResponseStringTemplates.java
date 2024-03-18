/**
 * Copyright 2024 Anton Kuzmin http://github.com/antonkuzmn1
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

package cloud.robinzon.backend.tools;

/**
 * <h3>String Templates for Response Form</h3>
 * <p>
 * This interface has string build templates for all occasions.
 * </p>
 * <p>
 * Contains the next methonds:
 * </p>
 * <p>
 * {@code setNull} - in obj cannot be null;
 * </p>
 * <p>
 * {@code setChar} - if obj contains more than X characters;
 * </p>
 * <p>
 * {@code setMore} - if value cannot be more than X;
 * </p>
 * <p>
 * {@code setLess} - if value cannot be less than X;
 * </p>
 * <p>
 * {@code inserted} - if new entity has been succefully inserted;
 * </p>
 * <p>
 * {@code setMore} - if entity has been succefully updated;
 * </p>
 * <p>
 * {@code setMore} - if entity has been succefully deleted;
 * </p>
 * <p>
 * Read more in the attached documents for each method.
 * </p>
 *
 * @since 2024.03.13
 * @author Anton Kuzmin
 */

public interface ResponseStringTemplates {

        /**
         * <h3>Initialize string builder for error collector.</h3>
         * <p>
         * During checks, all error messages will be accumulated here,
         * at the end of the checks the length of this object
         * will be checked and if it is greater than zero,
         * the response form will be returned with the status error
         * </p>
         */
        final StringBuilder err = new StringBuilder();

        /**
         * String builder is used in each of the functions of this class,
         * so it is based at the root.
         */
        final StringBuilder sb = new StringBuilder();

        /**
         * <h3>insert</h3>
         * <p>
         * Prepared variable for auto-completion when specifying a function name.
         * </p>
         */
        public final String insert = "insert";

        /**
         * <h3>update</h3>
         * <p>
         * Prepared variable for auto-completion when specifying a function name.
         * </p>
         */
        public final String update = "update";

        /**
         * <h3>delete</h3>
         * <p>
         * Prepared variable for auto-completion when specifying a function name.
         * </p>
         */
        public final String delete = "delete";

        /**
         * <h3>check error</h3>
         * <p>
         * This block of code should not be called at all!
         * If it was called,
         * then the error is guaranteed to be in the code itself,
         * and not in the input data.
         * </p>
         */
        public final String textError = "An unspecified error occurred during checks";

        /**
         * <h3>entity cannot be null</h3>
         * <p>
         * Prepared variable for auto-completion when specifying a function name.
         * </p>
         */
        public final String cannotBeNull = "Entity cannot be null in this block";

        /**
         * <h3>Object cannot be null</h3>
         * <p>
         * Use this pattern if the object must not be null
         * </p>
         *
         * @param column - enter column name;
         * @return A ready template string.
         *
         * @since 2024.03.13
         * @author Anton Kuzmin
         */
        default String setNull(Object value, String columnName) {
                return value == null
                                ? sb
                                                .append("\n")
                                                .append(columnName)
                                                .append(" cannot be null")
                                                .toString()
                                : "";
        }

        /**
         * <h3>String cannot contain more than X characters</h3>
         * <p>
         * Use this pattern if the string must cannot contain more than X characters
         * </p>
         *
         * @param column - the column name;
         * @param limit  - the line length limit;
         * @return A ready template string.
         *
         * @since 2024.03.13
         * @author Anton Kuzmin
         */
        default String setChar(String value, String columnName, int limit) {
                return value != null && value.length() > limit
                                ? sb
                                                .append("\n")
                                                .append(columnName)
                                                .append(" cannot contain more than ")
                                                .append(limit)
                                                .append(" characters")
                                                .toString()
                                : "";
        }

        /**
         * <h3>Value cannot be more than X</h3>
         * <p>
         * Use this pattern if the value must cannot be more than X
         * </p>
         *
         * @param column - the column name;
         * @param limit  - maximum value;
         * @return A ready template string.
         *
         * @since 2024.03.13
         * @author Anton Kuzmin
         */
        default String setMore(int value, String columnName, int limit) {
                return value > limit
                                ? sb
                                                .append("\n")
                                                .append(columnName)
                                                .append(" cannot be more than ")
                                                .append(limit)
                                                .toString()
                                : "";
        }

        /**
         * <h3>Value cannot be less than X</h3>
         * <p>
         * Use this pattern if the value must cannot be less than X
         * </p>
         *
         * @param column - the column name;
         * @param limit  - minimum value;
         * @return A ready template string.
         *
         * @since 2024.03.13
         * @author Anton Kuzmin
         */
        default String setLess(int value, String columnName, int limit) {
                return value < limit
                                ? sb
                                                .append("\n")
                                                .append(columnName)
                                                .append(" cannot be less than ")
                                                .append(limit)
                                                .toString()
                                : "";
        }

        /**
         * <h3>Value cannot be less than X</h3>
         * <p>
         * Use this pattern if the value must cannot be less than X
         * </p>
         *
         * @param column - the column name;
         * @param limit  - minimum value;
         * @return A ready template string.
         *
         * @since 2024.03.18
         * @author Anton Kuzmin
         */
        default String setLongLess(Long value, String columnName, int limit) {
                return value < limit
                                ? sb
                                                .append("\n")
                                                .append(columnName)
                                                .append(" cannot be less than ")
                                                .append(limit)
                                                .toString()
                                : "";
        }

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
         * true - is't unique;
         * </p>
         * </p>
         *
         * @param result - result of checking;
         * @param column - the column name;
         * @param value  - the value;
         * @return A ready template string.
         *
         * @since 2024.03.18
         * @author Anton Kuzmin
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
         * true - is't unique;
         * </p>
         * </p>
         *
         * @param result - result of checking;
         * @param column - the column name;
         * @param value  - the value;
         * @return A ready template string.
         *
         * @since 2024.03.18
         * @author Anton Kuzmin
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
         * <h3>The new entity was successfully inserted into the database</h3>
         * <p>
         * Use this method along with {@code super.success(here)};
         * </p>
         *
         * @param entity - inserted entity;
         * @param name   - information about the entity that will be displayed in the
         *               logs (name for example);
         * @return A ready template string.
         *
         * @since 2024.03.13
         * @author Anton Kuzmin
         */
        default String inserted(String name) {
                return sb
                                .append("Inserted: ")
                                .append(name)
                                .toString();
        }

        /**
         * <h3>The entity was successfully updated</h3>
         * <p>
         * Use this method along with {@code super.success(here)};
         * </p>
         *
         * @param entity - updated entity;
         * @param name   - information about the entity that will be displayed in the
         *               logs (name for example);
         * @return A ready template string.
         *
         * @since 2024.03.13
         * @author Anton Kuzmin
         */
        default String updated(String name) {
                return sb
                                .append("Updated: ")
                                .append(name)
                                .toString();
        }

        /**
         * <h3>The entity was successfully deleted</h3>
         * <p>
         * Use this method along with {@code super.success(here)};
         * </p>
         *
         * @param entity - deleted entity;
         * @param name   - information about the entity that will be displayed in the
         *               logs (name for example);
         * @return A ready template string.
         *
         * @since 2024.03.13
         * @author Anton Kuzmin
         */
        default String deleted(String name) {
                return sb
                                .append("Deleted: ")
                                .append(name)
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
         * @param result - result of checking;
         * @param column - the column name;
         * @param value  - the value;
         * @return A ready template string.
         *
         * @since 2024.03.18
         * @author Anton Kuzmin
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
         * @param result - result of checking;
         * @param column - the column name;
         * @param value  - the value;
         * @return A ready template string.
         *
         * @since 2024.03.18
         * @author Anton Kuzmin
         */
        default String setDeleted(boolean isDeleted, Object id) {
                return !isDeleted ? ""
                                : sb
                                                .append("\nEntity with id ")
                                                .append(id)
                                                .append(" already deleted")
                                                .toString();
        }

}
