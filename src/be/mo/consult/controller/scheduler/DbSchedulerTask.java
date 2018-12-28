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

package be.mo.consult.controller.scheduler;

import be.mo.consult.controller.logger.Loggers;
import be.mo.consult.controller.task.TasksList;

import java.util.TimerTask;

/**
 * Ce timer se charge de mettre à jour la liste des tâches
 * en chargeant celle-ci depuis la DB
 */
public class DbSchedulerTask extends TimerTask {

    private static final Loggers loggers = Loggers.getInstance();
    private TasksList list;

    /**
     * Constructeur
     * @param list
     */
    public DbSchedulerTask(TasksList list) {
        loggers.setDefaultLogger("be.mo.consult");
        this.list = list;
    }

    @Override
    public void run() {
        list.update();
        loggers.info(loggers.messageFactory.newMessage("The TasksList has been updated ; count : " + list.size()));
    }
}
