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

import be.mo.consult.controller.task.CollectionAccessor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Gestion de la collection Mongo
 * @param <T>
 */
public class MongoCollectionExtended<T> implements CollectionAccessor<T> {

    private String collectionName;
    private MongoCollection mongoCollection = null;
    private boolean isExist = false;
    private Type type;

    /**
     * Constructeur
     * @param collectionName
     * @param type
     */
    public MongoCollectionExtended(String collectionName, Type type) {

        this.collectionName = collectionName;
        this.type = type;

    }

    /**
     * Nom de la collection
     * @return
     */
    public String getCollectionName() {
        return collectionName;
    }

    /**
     * Mongo Collection
     * @param mongoCollection
     */
    public void setMongoCollection(MongoCollection mongoCollection) {
        this.mongoCollection = mongoCollection;
    }

    /**
     * Si la collection existe dans la database Mongo
     * @return
     */
    public boolean isExist() {
        return isExist;
    }

    /**
     * Place si la collection exist
     * @param exist
     */
    public void setExist(boolean exist) {
        isExist = exist;
    }

    /**
     * Retourne le nombre de document dans la collection
     * @return
     */
    public long count(){
        return mongoCollection.countDocuments();
    }

    /**
     * Sauvegarde la chaine JSON
     * @param json
     */
    public void save(String json){
        mongoCollection.insertOne(Document.parse(json));
    }

    /**
     * Sauvegarde l'object
     * @param object
     */
    public void save(T object){
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        String json = gson.toJson(object);
        save(json);
    }

    /**
     * Retourne les objets de la collection sans pouvoir appliquer un filtre
     * @return
     */
    public ArrayList<T> getObjectList(){
        //retourne un tableau d'objets T
        ArrayList<T> list = new ArrayList<T>();
        //GSON
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        //Mongo collection
        String json = "";
        FindIterable<Document> findIterable = mongoCollection.find();
        MongoCursor<Document> mongoCursor = findIterable.iterator();
        while (mongoCursor.hasNext()){
            json = mongoCursor.next().toJson();
            list.add(gson.fromJson(json,type));
        }
        return list;
    }
}
