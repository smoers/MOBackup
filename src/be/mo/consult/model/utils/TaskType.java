package be.mo.consult.model.utils;

public enum TaskType {

    WEEKLY(0),
    EACH_MONTH(1),
    EACH(2);

    private int type;

    TaskType(int type){

    }

    public int getType(){
        return type;
    }

}
