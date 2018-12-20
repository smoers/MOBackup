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

import be.mo.consult.model.exceptions.ErrorCode;

/**
 * Lève une exception lorsque le format de la config ne correspond pas au format demandé par le client
 */
public class ConfigurationFormatNotSupportedException extends Exception {

    private static final long serialVersionUID = 5874598321574521475L;

    private final ErrorCode code;

    /**
     *
     * @param code
     */
    public ConfigurationFormatNotSupportedException(ErrorCode code){
        super();
        this.code = code;
    }

    /**
     *
     * @param message
     * @param cause
     * @param code
     */
    public ConfigurationFormatNotSupportedException(String message, Throwable cause, ErrorCode code) {
        super(message, cause);
        this.code = code;
    }

    /**
     *
     * @param message
     * @param code
     */
    public ConfigurationFormatNotSupportedException(String message, ErrorCode code) {
        super(message);
        this.code = code;
    }

    /**
     *
     * @param cause
     * @param code
     */
    public ConfigurationFormatNotSupportedException(Throwable cause, ErrorCode code) {
        super(cause);
        this.code = code;
    }

    /**
     *
     * @return
     */
    public ErrorCode getCode() {
        return this.code;
    }

}
