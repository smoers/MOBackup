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

import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class MongoDatabaseExtended  {

    private String databaseName;
    private MongoDatabase mongoDatabase = null;
    private boolean isExist = false;

    /**
     * Contructeur
     * @param databaseName
     */
    public MongoDatabaseExtended(String databaseName) {
        this.databaseName = databaseName;
    }

    /**
     * Nom de la database
     * @return
     */
    public String getDatabaseName() {
        return databaseName;
    }

    /**
     * Charge la database
     * @param mongoDatabase
     */
    public void setDatabase(MongoDatabase mongoDatabase) {
        this.mongoDatabase = mongoDatabase;
    }

    /**
     * Retourne une collection si elle existe
     * @param mongoCollectionExtended
     * @return
     */
    public MongoCollectionExtended getCollection(MongoCollectionExtended mongoCollectionExtended){
        if (isMongoCollectionExist(mongoCollectionExtended.getCollectionName())){
            mongoCollectionExtended.setExist(true);
            mongoCollectionExtended.setMongoCollection(mongoDatabase.getCollection(mongoCollectionExtended.getCollectionName()));
        }
        return mongoCollectionExtended;
    }

    /**
     * Crée une collection
     * @param mongoCollectionExtended
     * @return
     */
    public MongoCollectionExtended createCollection(MongoCollectionExtended mongoCollectionExtended){
        if(!isMongoCollectionExist(mongoCollectionExtended.getCollectionName())){
            mongoDatabase.createCollection(mongoCollectionExtended.getCollectionName());
            mongoCollectionExtended = getCollection(mongoCollectionExtended);
        }
        return mongoCollectionExtended;
    }

    public boolean isExist() {
        return isExist;
    }

    public void setExist(boolean exist) {
        isExist = exist;
    }

    public MongoDatabase getDatabase(){
        return mongoDatabase;
    }

    /**
     * Détermine si une collection existe
     * @param mongoCollectionName
     * @return
     */
    protected boolean isMongoCollectionExist(String mongoCollectionName){

        boolean result = false;
        if(mongoDatabase.listCollectionNames() != null) {
            MongoCursor<String> mongoCursor = mongoDatabase.listCollectionNames().iterator();
            while (mongoCursor.hasNext()) {
                String name = mongoCursor.next();
                if (mongoCollectionName.equalsIgnoreCase(name)) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }

}
