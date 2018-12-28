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

package be.mo.consult;

import be.mo.consult.controller.configuration.ConfigurationBuilder;
import be.mo.consult.controller.db.Connector;
import be.mo.consult.controller.db.DbEngine;
import be.mo.consult.controller.db.DbEngineBuilder;
import be.mo.consult.controller.db.mongo.MongoCollectionExtended;
import be.mo.consult.controller.db.mongo.MongoDatabaseExtended;
import be.mo.consult.controller.task.TasksList;
import be.mo.consult.controller.db.mongo.MongoUser;
import be.mo.consult.controller.logger.Loggers;
import be.mo.consult.controller.scheduler.DbSchedulerTask;
import be.mo.consult.controller.scheduler.Scheduler;
import be.mo.consult.model.Task;
import be.mo.consult.model.exceptions.ConfigurationFormatNotSupportedException;
import be.mo.consult.model.exceptions.DbEngineBuilderKeyNotExistException;
import be.mo.consult.model.exceptions.ErrorCode;
import be.mo.consult.model.exceptions.TypeNotDefineConvertorException;
import be.mo.consult.tools.DateCalculator;

import java.io.IOException;
import java.util.UUID;

public class Launcher {

    private DbEngineBuilder _dbEngineBuilder = DbEngineBuilder.getInstance();
    private ConfigurationBuilder _configurationBuilder = ConfigurationBuilder.getInstance();
    private UUID _dbEngineKey;
    private static Launcher launcher = new Launcher();
    private static final Loggers loggers = Loggers.getInstance();

    public static void main(String[] args) throws IOException {

        loggers.setDefaultLogger("be.mo.consult");
        try {
            getInstance().init();
            Thread.sleep(300000);
            getInstance().destroy();
        } catch (ConfigurationFormatNotSupportedException e) {
            loggers.error(loggers.messageFactory.newMessage(e.getMessage() + " : " + e.getCode(), e.getCause()));
        } catch (TypeNotDefineConvertorException e) {
            loggers.error(loggers.messageFactory.newMessage(e.getMessage(), e.getCause()));
        } catch (DbEngineBuilderKeyNotExistException e) {
            loggers.error(loggers.messageFactory.newMessage(e.getMessage() + " : " + e.getCode()));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static Launcher getInstance() { return launcher;}

    private Launcher() {

    }

    /**
     *
     * @throws ConfigurationFormatNotSupportedException
     * @throws TypeNotDefineConvertorException
     */
    private void init() throws ConfigurationFormatNotSupportedException, TypeNotDefineConvertorException, DbEngineBuilderKeyNotExistException, IOException {

        //charge les fichiers de configuration
        _configurationBuilder.autoLoad(ConfigurationBuilder.DEFAULT_CONFIGURATION_FOLDER);
        loggers.info(loggers.messageFactory.newMessage("Configuration Folder Loaded"));

        //charge le DBEngine
        DbEngine _dbEngine = _dbEngineBuilder.add(_configurationBuilder.get("dbengine"));
        _dbEngineKey = _dbEngine.getKey();
        _dbEngine.start();
        loggers.info(loggers.messageFactory.newMessage("DBEngine started with ID : " + _dbEngineKey.toString()));

        //Charge l'utilisateur pour la DB
        String username = _configurationBuilder.get("dbengine").get("dbengine.user.username").getAsString();
        String password = _configurationBuilder.get("dbengine").get("dbengine.user.password").getAsString();
        MongoUser user = new MongoUser(username, password);

        //Obtient le connecteur
        Connector _connector = _dbEngine.getConnector();
        loggers.info(loggers.messageFactory.newMessage("The connector instance loaded"));
        //
        while (_dbEngine.ping() == false){
            loggers.info(loggers.messageFactory.newMessage("Waiting DB Engine loaded ... (1s)"));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                loggers.error(loggers.messageFactory.newMessage(ErrorCode.MASTER_THREAD_PROBLEN.getMessage(), ErrorCode.MASTER_THREAD_PROBLEN, e.fillInStackTrace()));
            }
        }
        loggers.info(loggers.messageFactory.newMessage("DB Engine loaded"));

        //Load database
        MongoDatabaseExtended mongoDatabaseExtended = new MongoDatabaseExtended("mobackup");
        MongoCollectionExtended<Task> mongoCollectionExtended = new MongoCollectionExtended<Task>("tasks", Task.class);
        loggers.info(loggers.messageFactory.newMessage("Loading Database"));
        mongoDatabaseExtended = _connector.getDatabase(mongoDatabaseExtended);
        if(!mongoDatabaseExtended.isExist()){
            mongoDatabaseExtended = _connector.createDatabase(mongoDatabaseExtended);
        }
        loggers.info(loggers.messageFactory.newMessage("Loading Collection"));
        mongoCollectionExtended = mongoDatabaseExtended.getCollection(mongoCollectionExtended);
        if(!mongoCollectionExtended.isExist()){
            mongoCollectionExtended = mongoDatabaseExtended.createCollection(mongoCollectionExtended);
        }

        //TaskList
        loggers.info(loggers.messageFactory.newMessage("TaskList Instance"));
        TasksList tasksList = TasksList.getInstance();
        tasksList.setCollectionAccessor(mongoCollectionExtended);
        //Charge le timer task de la db dans le timer principale
        DbSchedulerTask dbSchedulerTask = new DbSchedulerTask(tasksList);
        Scheduler.getInstance().getMainTimer().schedule(dbSchedulerTask, DateCalculator.getDateWithRoundSecond(),60000);

    }

    private void destroy() throws DbEngineBuilderKeyNotExistException {
        DbEngine _dbEngine = _dbEngineBuilder.getByKey(_dbEngineKey);
        _dbEngine.stop();
        loggers.info(loggers.messageFactory.newMessage("The DBEngine with ID " + _dbEngine.getKey().toString() + " stopped"));
        Scheduler.getInstance().getMainTimer().cancel();
    }
}
