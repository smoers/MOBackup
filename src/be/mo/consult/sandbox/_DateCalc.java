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

import java.util.Calendar;
import java.util.Date;

public class _DateCalc {

    public static void main(String[] args) {

        Calendar calendar = Calendar.getInstance();
        int second = calendar.get(Calendar.SECOND);
        Date date1 = calendar.getTime();
        calendar.add(Calendar.SECOND,-1 * second);
        Date date = calendar.getTime();


        System.out.println(date1.toString());
        System.out.println(date.toString());
        System.out.println(second);
    }
}
