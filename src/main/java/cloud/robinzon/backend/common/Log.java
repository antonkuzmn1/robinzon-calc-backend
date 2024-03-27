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

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Utility class Log for logging messages and errors with timestamp, class name, and method name.
 *
 * @author Anton Kuzmin
 * @since 2024.03.25
 */

public class Log {

    private static String className;
    private static String methodName;

    /**
     * Sets the class name and method name for logging purposes.
     *
     * @param obj    the Class object for which the logging is being set
     * @param method the name of the method for which the logging is being set
     * @author Anton Kuzmin
     * @since 2024.03.25
     */
    public static void set(Class<?> obj, String method) {
        className = obj.getSimpleName();
        methodName = method;
    }

    /**
     * Logs a message with timestamp, class name, method name, and the provided text.
     *
     * @param text the text to be logged
     * @author Anton Kuzmin
     * @since 2024.03.25
     */
    public static void log(String text) {
        System.out.printf("[%s][%s][%s][%s] %s%n",
                getDateTime().date,
                getDateTime().time,
                className,
                methodName,
                text);
    }

    /**
     * Logs an error message with timestamp, class name, method name, "ERROR" tag, and the provided text.
     * Returns a ResponseEntity with status code 400 (Bad Request) and the error message as the response body.
     *
     * @param text the error message to be logged and returned
     * @return a ResponseEntity with status code 400 (Bad Request) and the error message as the response body
     * @author Anton Kuzmin
     * @since 2024.03.25
     */
    public static ResponseEntity<?> err(String text) {
        System.out.printf("[%s][%s][%s][%s][ERROR] %s%n",
                getDateTime().date,
                getDateTime().time,
                className,
                methodName,
                text);
        return ResponseEntity.badRequest().body(text);
    }

    private static DateTime getDateTime() {
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter datePattern = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timePattern = DateTimeFormatter.ofPattern("HH:mm:ss");
        String date = currentTime.format(datePattern);
        String time = currentTime.format(timePattern);
        return new DateTime(date, time);
    }

    @Getter
    @AllArgsConstructor
    private static class DateTime {
        private String date;
        private String time;
    }

}
