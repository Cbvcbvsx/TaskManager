package com.company;


public class Setting {
    private static int optionSave;
    private static int optionNotification;

    public static void setOptionNotification(int optionNotification1) {
        optionNotification = optionNotification1;
    }

    public static void setOptionSave(int optionSave1) {
        optionSave = optionSave1;
    }

    public int getOptionNotification() {
        return optionNotification;
    }

    public int getOptionSave() {
        return optionSave;
    }
}
