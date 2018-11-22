package com.company;

import com.company.gui.EventGUI;

public class Event {
    public static void evented(Tasks tasks)
    {
        new EventGUI(tasks);
        tasks.deleteTask(0);
    }
}