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

import java.util.Calendar;
import java.util.Date;

public class DateCalculator {

    public static Calendar getCalendar(){
        return Calendar.getInstance();
    }

    public static Date getDate(){
        Calendar cal = DateCalculator.getCalendar();
        return cal.getTime();
    }

    public static Date getDate(int yearOffset, int monthOffset, int dayOffset, int hourOffset, int minuteOffset, int secondOffset){
        Calendar cal = DateCalculator.getCalendar();
        cal.add(Calendar.YEAR, yearOffset);
        cal.add(Calendar.MONTH, monthOffset);
        cal.add(Calendar.DAY_OF_MONTH, dayOffset);
        cal.add(Calendar.HOUR, hourOffset);
        cal.add(Calendar.MINUTE, minuteOffset);
        cal.add(Calendar.SECOND, secondOffset);
        return cal.getTime();
    }

    /**
     * Retourne une date à l'heure exacte précédente
     * ex: si Fri Dec 28 14:43:37 CET 2018 retourne Fri Dec 28 14:43:00 CET 2018
     * @return
     */
    public static Date getDateWithRoundSecond(){
        Calendar cal = DateCalculator.getCalendar();
        int second = cal.get(Calendar.SECOND);
        cal.add(Calendar.SECOND,-1 * second);
        return cal.getTime();
    }



}
