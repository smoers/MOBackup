/*
 * *
 *  * Copyright (c) 2018.  MO Consult
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *     http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 *
 */

package be.mo.consult.model.exceptions;

public enum ErrorCode {

    CONFIGURATION_NOT_CORRECTLY_FORMATED(1001, "The configuration object is not formatted as requested by the instance"),

    CONVERTOR_TYPE_REQUESTED_IS_NOT_DEFINED(2001, "The converter type requested is not defined"),

    DBENGINE_BUILDER_KEY_NOT_EXIST(3001, "This key is not exist into the collection DBEngine"),

    MASTER_THREAD_PROBLEN(4001, "A problem is occurred in the main Thread");

    private int errorCode;
    private String message;

    ErrorCode(int errorCode, String message) {

        this.errorCode = errorCode;
        this.message =message;
    }

    public int getErrorCode() {
        return errorCode;
    }
    public String getMessage() { return message; }
}
