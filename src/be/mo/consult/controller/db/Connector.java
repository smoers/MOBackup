
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

package be.mo.consult.controller.db;

import be.mo.consult.controller.db.mongo.MongoDatabaseExtended;
import be.mo.consult.model.listeners.MongoConnectorServerMonitorListener;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.HashMap;
import java.util.UUID;

/**
 * Connecteur d'accès au moteur de DB
 */
public class Connector {

    private UUID keyOwner;
    private String host;
    private int port;
    private MongoClient mongoClient;
    private HashMap<String, MongoDatabase> databaseList = new HashMap<String, MongoDatabase>();

    /**
     * Cosntructeur
     * @param keyOwner
     * @param host
     * @param port
     */
    public Connector(UUID keyOwner, String host, int port) {

        this.keyOwner = keyOwner;
        this.host = host;
        this.port = port;
        MongoClientOptions.Builder builder = MongoClientOptions.builder();
        builder.connectTimeout(10)
               .addServerMonitorListener(new MongoConnectorServerMonitorListener(getKeyOwner()));
        MongoClientOptions mongoClientOptions = builder.build();
        mongoClient = new MongoClient(new ServerAddress(host, port), mongoClientOptions);
    }

    public MongoDatabaseExtended getDatabase(MongoDatabaseExtended mongoDatabaseExtended){
        if(isDatabaseExist(mongoDatabaseExtended.getDatabaseName())){
            mongoDatabaseExtended.setExist(true);
            mongoDatabaseExtended.setDatabase(mongoClient.getDatabase(mongoDatabaseExtended.getDatabaseName()));

        }
        return mongoDatabaseExtended;
    }

    public MongoDatabaseExtended createDatabase(MongoDatabaseExtended mongoDatabaseExtended){
        if(!isDatabaseExist(mongoDatabaseExtended.getDatabaseName())){
            System.out.println("Created");
            mongoDatabaseExtended.setExist(true);
            mongoDatabaseExtended.setDatabase(mongoClient.getDatabase(mongoDatabaseExtended.getDatabaseName()));;
        }
        return mongoDatabaseExtended;
    }

    /**
     * Retourne le client
     * @return MongoClient
     */
    public MongoClient getMongoClient() { return mongoClient; }

    /**
     * Retourne l'identifiant du propriétaire de l'instance
     * @return
     */
    public UUID getKeyOwner() {
        return keyOwner;
    }

    /**
     * Détermine si une DB existe
     * @param databaseName
     * @return
     */
    protected boolean isDatabaseExist(String databaseName){

        boolean isExist = false;
        MongoCursor<Document> mongoCursor = mongoClient.listDatabases().iterator();
        while (mongoCursor.hasNext()) {
            Document document = mongoCursor.next();
            if(document.containsKey("name")){
                if(document.getString("name").equalsIgnoreCase(databaseName)) { isExist = true; break; }
            }
        }
        return isExist;

    }
}
