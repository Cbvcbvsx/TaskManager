package com.company;

import java.io.*;


public class Save {

    public static void writeTasks (Tasks tasks, Writer out) //метод сохранения в файл
    {
        try(DataOutputStream	dos	=	new	DataOutputStream(new FileOutputStream("data.txt"))){
            dos.writeInt(tasks.getNum());
            for(int i=0; i<tasks.getNum(); i++) {
                dos.writeUTF(tasks.getTask(i).getName());
                dos.writeUTF(tasks.getTask(i).getDescription());
                dos.writeUTF(tasks.getTask(i).getDate().toString());
                dos.writeUTF(tasks.getTask(i).arrayListToString());
            }
             System.out.println("Запись	в	файл	произведена");
             }
             catch(IOException	ex){
            System.out.println(ex.getMessage());
        }
        /*try(PrintWriter pw = new PrintWriter(out))
        {
            String s = "";
            for(int i=0; i<tasks.getNum(); i++)// проходя по массиву задач записываем//изменить на for each
            {
                s+= tasks.getTask(i).getName() + ", " + tasks.getTask(i).getDescription() + ", " + tasks.getTask(i).getDate() + ", " + tasks.getTask(i).getContacts() + "\n";
            }
            pw.print(s);
        }*/
    }


}
