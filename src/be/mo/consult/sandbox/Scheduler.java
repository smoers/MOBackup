package be.mo.consult.sandbox;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class Scheduler {

    public static void main(String[] args) {
        Scheduler scheduler = new Scheduler();
    }

    public Scheduler(){
        init();
    }

    private void init(){

        Calendar calendar = Calendar.getInstance();
        Calendar calendar1 = Calendar.getInstance();
        calendar.set(2018,10,23,15,34,0);
        calendar1.set(2018,10,23,15,34,30);
        System.out.println(calendar.getTime());
        System.out.println(calendar1.getTime());
        TimerTask timerTask1 = new Task("Task 1");
        TimerTask timerTask2 = new Task("Task 2");
        Timer timer = new Timer("Timer", true);
        timer.schedule(timerTask1, calendar.getTime());
        timer.schedule(timerTask2, calendar1.getTime());

        try {
            int ii = 300;
            for(int i=1;i<=ii; i++) {
                Thread.sleep(1000);
                System.out.println(i);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
