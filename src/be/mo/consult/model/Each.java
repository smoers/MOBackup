package be.mo.consult.model;

import java.util.Date;

/**
 *
 */
public class Each {

    private Date lastRun = null;
    private int each = 0;

    public Each(int each){
        this.each = each;
    }

    public Each() {
    }

    public Date getLastRun() {
        return lastRun;
    }

    public void setLastRun(Date lastRun) {
        this.lastRun = lastRun;
    }

    public int getEach() {
        return each;
    }

    public void setEach(int each) {
        this.each = each;
    }

}
