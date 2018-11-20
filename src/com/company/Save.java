package com.company;

import java.io.*;


public class Save {

    public static void writeTasks (Tasks tasks, Writer out) //метод сохранения в файл
    {
        try(PrintWriter pw = new PrintWriter(out))
        {
            String s = "";
            for(int i=0; i<tasks.getNum(); i++)// проходя по массиву задач записываем//изменить на for each
            {
                s+= tasks.getTask(i).getName() + ", " + tasks.getTask(i).getDescription() + ", " + tasks.getTask(i).getDate() + ", " + tasks.getTask(i).getContacts() + "\n";
            }
            pw.print(s);
        }
    }
}
