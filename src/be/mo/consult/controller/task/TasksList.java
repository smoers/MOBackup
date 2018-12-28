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

package be.mo.consult.controller.task;

import be.mo.consult.model.Task;

import java.util.ArrayList;

/**
 * Cette classe de type singleton stocke les tâches luent dans la DB Mongo
 * Les tâches sont disponibles sous forme d'un tableau.
 */
public class TasksList extends ArrayList<Task> {

    private static TasksList ourInstance = new TasksList();
    private CollectionAccessor<Task> collectionAccessor = null;

    public static TasksList getInstance() {
        return ourInstance;
    }

    private TasksList() {
        super();
    }

    /**
     * Défini la collection Mongo pour l'accès au données de la DB
     * @param collectionAccessor
     */
    public void setCollectionAccessor(CollectionAccessor collectionAccessor){
        this.collectionAccessor = collectionAccessor;

    }

    /**
     * Charge la liste avec les données de la DB
     */
    public void update(){
        this.clear();
        this.addAll(collectionAccessor.getObjectList());
    }

}
