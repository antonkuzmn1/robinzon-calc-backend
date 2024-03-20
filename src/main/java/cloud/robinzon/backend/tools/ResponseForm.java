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

/**
 * <h3>The Response Form</h3>
 * <p>
 * This class has main tools for Entity-Management-Logging for all occasions.
 * </p>
 * <p>
 * Contains the next methods:
 * </p>
 * <p>
 * {@code set} - Setting the class name;
 * </p>
 * <p>
 * It is recommended to install immediately in the constructor.
 * </p>
 * <p>
 * Example:
 * </p>
 * <blockquote>
 *
 * <pre>
 * public VpnServerEntityManager() {
 *     // Setting the class name for the logging class
 *     super.set(getClass().getSimpleName());
 * }
 * </blockquote>
 * </pre>
 * <p>
 * {@code function} - Setting the function name;
 * <p>
 * You can use prepared variables for the insert, update, delete methods.
 * </p>
 * </p>
 * <p>
 * {@code error} - Returns the status ERROR;
 * </p>
 * <p>
 * {@code success} - Returns the status SUCCESS;
 * </p>
 *
 * @author Anton Kuzmin
 * @since 2024.03.13
 */

public class ResponseForm {

    private String className;
    private String functionName;
    private String status; // response status
    private String text; // response message

    public ResponseForm() {
    }

    /**
     * <h3>Sets the name of the function</h3>
     * <p>
     * Recommended for use in a constructor
     * </p>
     * <p>
     * Example
     * </p>
     * <blockquote>
     *
     * <pre>
     * public VpnServerEntityManager() {
     *     // Setting the class name for the logging class
     *     super.set(getClass().getSimpleName());
     * }
     * </blockquote>
     * </pre>
     *
     * @param className - the class name;
     * @author Anton Kuzmin
     * @since 2024.03.13
     */
    public void set(String className) {
        this.className = className;
    }

    /**
     * <h3>Sets the name of the class</h3>
     * <p>
     * You can use prepared variables for the insert, update, delete methods
     * </p>
     *
     * @param functionName - the function name;
     * @author Anton Kuzmin
     * @since 2024.03.13
     */
    public void function(String functionName) {
        this.functionName = functionName;
        this.log();
    }

    /**
     * <h3>Status error</h3>
     * <p>
     * Use this if you need to return the ERROR status
     * </p>
     *
     * @param text - Error message text;
     * @return Form with status "ERROR"
     * @author Anton Kuzmin
     * @since 2024.03.13
     */
    public ResponseForm error(String text) {
        this.status = "ERROR";
        this.text = text;
        this.log();
        return this;
    }

    /**
     * <h3>Status successful</h3>
     * <p>
     * Use this if you need to return the SUCCESS status
     * </p>
     *
     * @param text - Success message text;
     * @return Form with status "ERROR"
     * @author Anton Kuzmin
     * @since 2024.03.13
     */
    public ResponseForm success(String text) {
        this.status = "SUCCESS";
        this.text = text;
        this.log();
        return this;
    }

    /**
     * <h3>Logging method</h3>
     * <p>
     * That method make logs into console
     * </p>
     * <p>
     * The program will stop if a class is not
     * specified first using {@code set()}
     * and also a function using {@code function()}
     * </p>
     *
     * @author Anton Kuzmin
     * @since 2024.03.13
     * @since 2024.03.15
     */
    private void log() {
        if (this.className == null) throw new IllegalArgumentException(
                "[ResponseForm] className cannot be null");
        if (this.functionName == null) throw new IllegalArgumentException(
                String.format("[ResponseForm][%s] functionName cannot be null", this.className));
        if (this.status == null && this.text != null) throw new IllegalArgumentException(
                String.format("[ResponseForm][%s] status cannot be null if the text is not null", this.className));

        System.out.printf(
                "[%s][%s]%s%s%n",
                this.className,
                this.functionName,
                (this.status == null ? "" : String.format("[%s]", this.status)),
                (this.text == null ? "" : String.format(" %s", this.text)));
    }

}