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

package be.mo.consult.model.listeners;

import be.mo.consult.controller.db.DbEngineBuilder;
import be.mo.consult.controller.logger.Loggers;
import be.mo.consult.model.exceptions.DbEngineBuilderKeyNotExistException;
import be.mo.consult.model.exceptions.ErrorCode;
import com.mongodb.event.ServerHeartbeatSucceededEvent;
import com.mongodb.event.ServerMonitorListenerAdapter;

import java.util.Collections;
import java.util.UUID;

public class MongoConnectorServerMonitorListener extends ServerMonitorListenerAdapter {

    private UUID keyOwner;

    public MongoConnectorServerMonitorListener(UUID keyOwner) {
        super();
        this.keyOwner = keyOwner;

    }

    @Override
    public void serverHeartbeatSucceeded(ServerHeartbeatSucceededEvent event) {
        super.serverHeartbeatSucceeded(event);
        Loggers loggers = Loggers.getInstance();
        DbEngineBuilder dbEngineBuilder = DbEngineBuilder.getInstance();
        try {
            System.out.println(keyOwner.toString());
            dbEngineBuilder.getByKey(keyOwner).isPing = true;
        } catch (DbEngineBuilderKeyNotExistException e) {
            loggers.error(loggers.getLogger("be.mo.consult"), loggers.messageFactory.newMessage(
                    ErrorCode.DBENGINE_BUILDER_KEY_NOT_EXIST.getMessage(),
                    ErrorCode.DBENGINE_BUILDER_KEY_NOT_EXIST,
                    e.fillInStackTrace()));
        }
    }
}
