package be.mo.consult.model;

import be.mo.consult.model.utils.MonthDays;

public class MonthDay {

    private MonthDays monthDay;

    public MonthDay(MonthDays monthDay) {
        this.monthDay = monthDay;
    }

    public MonthDays getMonthDay(){
        return monthDay;
    }
}
