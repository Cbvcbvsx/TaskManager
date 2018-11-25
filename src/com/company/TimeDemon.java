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
        for (; ; ) {
            date = new Date();
try {
    if (((date).compareTo(tasks.getTask(0).getDate())) >= 0) {
        try {
            Event.evented(tasks);
            sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
catch (Exception e)
{
    e.printStackTrace();
}
            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}