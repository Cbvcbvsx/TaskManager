package com.company;

import com.company.gui.EventGUI;

public class Event {
   static boolean b=true;
    public static void evented(Tasks tasks) throws InterruptedException {
        if (b) {
            b=false;
            new EventGUI(tasks);
        }

    }
    public static void setB()
    {
        b=true;
    }
}