package be.mo.consult.model.utils;

/**
 * Enum la liste des jours de la semaines
 */
public enum WeekDays {
    //Jour de la semaine avec le convertion en chiffre
    SUNDAY(1),
    MONDAY(2),
    TUESDAY(3),
    WEDNESDAY(4),
    THURSDAY(5),
    FRIDAY(6),
    SATURDAY(7);

    private int day;

    WeekDays(int day) {
        this.day = day;
    }

    public int getDay(){
        return day;
    }
}
