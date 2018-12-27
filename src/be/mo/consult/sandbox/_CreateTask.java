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

package be.mo.consult.sandbox;

import be.mo.consult.model.Task;
import be.mo.consult.model.TimeTable;
import be.mo.consult.model.WeekDay;
import be.mo.consult.model.utils.TaskType;
import be.mo.consult.model.utils.WeekDays;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class _CreateTask {

    public static Task getTask() {

        Task task = new Task(TaskType.WEEKLY);
        ArrayList operations = new ArrayList();
        ArrayList<TimeTable> timeTables = new ArrayList<TimeTable>();
        operations.add(new WeekDay(WeekDays.MONDAY));
        operations.add(new WeekDay(WeekDays.FRIDAY));
        timeTables.add(new TimeTable(12,30));
        timeTables.add(new TimeTable(19,30));
        task.setOperations(operations);
        task.setTimeTables(timeTables);
        return task;

    }

}
