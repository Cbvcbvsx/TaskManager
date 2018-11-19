package com.company;

public class Event {
    public static void evented(Tasks tasks)
    {
        new EventGUI(tasks);
        tasks.deleteTask(1);
    }

}