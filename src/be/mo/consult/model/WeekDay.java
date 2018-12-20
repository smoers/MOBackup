package be.mo.consult.model;

import be.mo.consult.model.utils.WeekDays;

/**
 *
 */
public class WeekDay {

    private WeekDays weekDay;

    public WeekDay(WeekDays weekDay) {
        this.weekDay = weekDay;
    }

    public WeekDays getWeekDay(){
        return weekDay;
    }
}
