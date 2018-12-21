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

package be.mo.consult.controller.db.mongo;

import be.mo.consult.model.TaskInterface;
import com.mongodb.client.MongoCollection;

public class MongoTask<T> implements MongoCollectionInterface<T> {

    private TaskInterface task = null;
    private MongoCollection<T> collectionName = null;

    public MongoTask() {
    }

    public MongoTask(TaskInterface task) {
        this.task = task;
    }

    @Override
    public void setCollection(MongoCollection<T> mongoCollection) {

    }

    @Override
    public MongoCollection<T> getMongoCollection() {
        return null;
    }

    public TaskInterface getTask() {
        return task;
    }

    public void setTask(TaskInterface task) {
        this.task = task;
    }
}
