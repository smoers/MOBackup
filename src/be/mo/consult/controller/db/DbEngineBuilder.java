
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

import be.mo.consult.controller.configuration.Configuration;
import be.mo.consult.model.exceptions.ConfigurationFormatNotSupportedException;
import be.mo.consult.model.exceptions.DbEngineBuilderKeyNotExistException;
import be.mo.consult.model.exceptions.ErrorCode;
import be.mo.consult.tools.Convertor;
import be.mo.consult.model.exceptions.TypeNotDefineConvertorException;
import com.google.gson.JsonElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

/**
 * Gère les objects de type DBEngine
 */
public class DbEngineBuilder {

    private HashMap<UUID, DbEngine> dbEngines;
    private static DbEngineBuilder ourInstance = new DbEngineBuilder();

    /**
     *
     * @return
     */
    public static DbEngineBuilder getInstance() {
        return ourInstance;
    }

    /**
     * Constructeur privé
     */
    private DbEngineBuilder() {
        this.dbEngines = new HashMap<UUID, DbEngine>();
    }

    /**
     * Ajoute une instance d'un objet DBEngine
     * @param configuration
     * @return
     * @throws TypeNotDefineConvertorException
     * @throws ConfigurationFormatNotSupportedException
     */
    public DbEngine add(Configuration configuration) throws TypeNotDefineConvertorException, ConfigurationFormatNotSupportedException {

        JsonElement jsonElement = configuration.get("dbengine.command") ;
        UUID key = UUID.randomUUID();
        DbEngine dbEngine;
        if(jsonElement.isJsonArray()){
            ArrayList<String> arrayList = Convertor.jsonArrayToArrayList(jsonElement.getAsJsonArray(), Convertor.STRING_WITNESS);
            dbEngine = new DbEngine(key, arrayList);
            dbEngines.put(key,dbEngine);
        } else {
            throw new ConfigurationFormatNotSupportedException("The configuration format is not supported", ErrorCode.CONFIGURATION_NOT_CORRECTLY_FORMATED);
        }

        return dbEngine;

    }

    /**
     *
     * @param key
     * @return
     * @throws DbEngineBuilderKeyNotExistException
     */
    public DbEngine getByKey(UUID key) throws DbEngineBuilderKeyNotExistException{
        if(!dbEngines.containsKey(key)){
            throw new DbEngineBuilderKeyNotExistException("Key not exist in the DBEngine List", ErrorCode.DBENGINE_BUILDER_KEY_NOT_EXIST);
        }
        return dbEngines.get(key);
    }


}
