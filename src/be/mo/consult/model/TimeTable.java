package be.mo.consult.model;

public class TimeTable {

    private int hour;
    private int minute;

    public TimeTable(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }
}
