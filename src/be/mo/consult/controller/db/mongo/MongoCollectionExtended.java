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

import com.mongodb.client.MongoCollection;

public class MongoCollectionExtended {

    private String collectionName;
    private MongoCollection mongoCollection = null;
    private boolean isExist = false;

    public MongoCollectionExtended(String collectionName) {
        this.collectionName = collectionName;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setMongoCollection(MongoCollection mongoCollection) {
        this.mongoCollection = mongoCollection;
    }

    public boolean isExist() {
        return isExist;
    }

    public void setExist(boolean exist) {
        isExist = exist;
    }

    public long count(){
        return mongoCollection.countDocuments();
    }
}
