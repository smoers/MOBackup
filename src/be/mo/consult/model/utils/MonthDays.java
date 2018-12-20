package be.mo.consult.model.utils;

/**
 * Enum la liste des jours d'un mois
 */
public enum MonthDays {

    DAY_01(1),
    DAY_02(2),
    DAY_03(3),
    DAY_04(4),
    DAY_05(5),
    DAY_06(6),
    DAY_07(7),
    DAY_08(8),
    DAY_09(9),
    DAY_10(10),
    DAY_11(11),
    DAY_12(12),
    DAY_13(13),
    DAY_14(14),
    DAY_15(15),
    DAY_16(16),
    DAY_17(17),
    DAY_18(18),
    DAY_19(19),
    DAY_20(20),
    DAY_21(21),
    DAY_22(22),
    DAY_23(23),
    DAY_24(24),
    DAY_25(25),
    DAY_26(26),
    DAY_27(27),
    DAY_28(28),
    DAY_29(29),
    DAY_30(30),
    DAY_31(31),
    DAY_00(0),
    ;


    private int day;

    MonthDays(int day){
        this.day = day;
    }

    public int getDay(){
        return day;
    }
}
