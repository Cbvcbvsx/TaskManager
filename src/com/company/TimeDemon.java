package com.company;


import java.util.Date;

public class TimeDemon extends Thread{
    Date date;
    Tasks tasks;
    public TimeDemon(Tasks tasks)
    {
        this.tasks=tasks;
    }
    @Override
    public void run() {
        date = new Date() ;

        if (((date).compareTo(tasks.getTask(0).getDate()))<=0)
            Event.evented(tasks);
        try {
            sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}