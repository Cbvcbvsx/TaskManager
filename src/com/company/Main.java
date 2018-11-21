package com.company;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Main {

   public static void main(String[] args) throws IOException {
       /* try {
            Scanner sc = new Scanner(new FileReader("setting"));
            Setting.setOptionSave(sc.nextInt());
            Setting.setOptionNotification(sc.nextInt());
        } catch (Exception e) {
            Setting.setOptionSave(1);
            Setting.setOptionNotification(1);
            FileWriter fileWriter = new FileWriter("setting",false);
            fileWriter.write("1 1");
            fileWriter.close();

        }*/
      ArrayList <String> contact1 = new ArrayList();
      contact1.add("ФИО");
      contact1.add("Телефон");
      ArrayList <String> contact2 = new ArrayList();
      contact2.add("ФИО1");

      Task task1 = new Task ("Курсач","Очень срочно", new Date(), contact1);
      Task task2 = new Task ("ДЗ по радиотеху","Не сильно срочно", new Date(), contact2);
      Task [] arrTask = new Task[] {task1,task2};
      Tasks tasks= new Tasks(arrTask);
      GUI app = new GUI(tasks);
   }
}