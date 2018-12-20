
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

package be.mo.consult.tools;

import be.mo.consult.model.exceptions.TypeNotDefineConvertorException;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.util.ArrayList;
import java.util.Iterator;

public class Convertor {

    public static final String STRING_WITNESS = "witness";
    public static final Integer INTEGER_WITNESS = 0;
    public static final boolean BOOLEAN_WITNESS = true;
    public static final float FLOAT_WITNESS = 0;
    public static final double DOUBLE_WITNESS = 0;
    public static final char CHARACTER_WITNESS = 'a';

    public static <T> ArrayList<T>  jsonArrayToArrayList(JsonArray jsonArray, T witness) throws TypeNotDefineConvertorException{

        ArrayList<T> arrayList = new ArrayList<T>();
        Iterator<JsonElement> iterator = jsonArray.iterator();
        while(iterator.hasNext()){
            JsonElement jsonElement = iterator.next();
            Object object = null;
            switch (witness.getClass().getTypeName()){
                case "java.lang.String": object = jsonElement.getAsString(); break;
                case "java.lang.Integer": object = jsonElement.getAsInt(); break;
                case "java.lang.Boolean": object = jsonElement.getAsBoolean(); break;
                case "java.lang.Float": object = jsonElement.getAsFloat(); break;
                case "java.lang.Double": object = jsonElement.getAsDouble(); break;
                case "java.lang.Character": object = jsonElement.getAsCharacter(); break;
                default: throw new TypeNotDefineConvertorException("Type return object is not available");
            }
            arrayList.add((T) object);
        }
        return arrayList;
    }

}
