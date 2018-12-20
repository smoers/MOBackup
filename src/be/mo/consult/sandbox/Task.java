package be.mo.consult.sandbox;

import java.util.TimerTask;

public class Task extends TimerTask {

    private String message;

    public Task(String message) {
        this.message = message;
    }

    @Override
    public void run() {
        System.out.println(message);
    }

}
