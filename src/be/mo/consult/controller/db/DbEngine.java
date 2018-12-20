
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

import com.mongodb.MongoSocketOpenException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.UUID;

/**
 * Gestion du moteur de DB
 * Ici MongoDB
 */
public class DbEngine {

    private UUID key;
    private Connector connector = null;
    private ArrayList<String> command;
    private int port;
    private ProcessBuilder processBuilder;
    private Process process;
    public boolean isPing = false;

    /**
     * Constructeur
     * @param key
     * @param command
     */
    public DbEngine(UUID key, ArrayList<String> command) {
        this.key = key;
        this.command = command;

        processBuilder = new ProcessBuilder();
        processBuilder.command(command);
        //recherche le port pour permettre la création d'une connexion
        Iterator<String> iterator = command.iterator();
        while(iterator.hasNext()){
            String item = iterator.next();
            if(item.equalsIgnoreCase("--port") && iterator.hasNext()){
                port = Integer.valueOf(iterator.next());
                break;
            }
        }

    }

    /**
     * Démarre le processus
     * @throws IOException
     */
    public void start() throws IOException {
        process = processBuilder.start();
    }

    /**
     * Stop le processus
     */
    public void stop(){
        process.destroyForcibly();
    }

    /**
     * Détermine si le processus tourne
     * @return
     */
    public boolean isAlive() {
        return process.isAlive();
    }

    /**
     * Info sur le processus
     * @return
     */
    public ProcessHandle.Info getInfo(){
        return process.info();
    }

    /**
     * Retourne le connecteur d'accès au moteur
     * @return
     */
    public Connector getConnector(){
        if(connector == null){
            try {
                connector = new Connector(getKey(), "localhost", port);
            } catch (MongoSocketOpenException e) {}
        }
        return connector;
    }

    /**
     * Détermine si le moteur est prêt à recevoir des demandes
     * @return
     */
    public boolean ping(){

        return isPing;
    }

    /**
     * Retourne l'identifiant de l'instance
     * @return
     */
    public UUID getKey() {
        return key;
    }
}
