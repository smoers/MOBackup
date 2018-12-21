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

public class ConnectorDatabaseNotLoadedException extends Exception {

    private static final long serialVersionUID = 465113546878454683l;
    private final ErrorCode code;

    public ConnectorDatabaseNotLoadedException(ErrorCode code) {
        this.code = code;
    }

    public ConnectorDatabaseNotLoadedException(String message, ErrorCode code) {
        super(message);
        this.code = code;
    }

    public ConnectorDatabaseNotLoadedException(String message, Throwable cause, ErrorCode code) {
        super(message, cause);
        this.code = code;
    }

    public ConnectorDatabaseNotLoadedException(Throwable cause, ErrorCode code) {
        super(cause);
        this.code = code;
    }

    public ConnectorDatabaseNotLoadedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, ErrorCode code) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.code = code;
    }
}
