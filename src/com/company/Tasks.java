package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;

public class Tasks {
    static BufferedReader reader;

    static {
        try {
            reader = new BufferedReader((new FileReader(new File("Tasks.txt"))));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            try {
                FileWriter fw = new FileWriter(new File("Tasks.txt"));
                fw.write("0");
                fw.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    static StringTokenizer tokenizer;


    public static String tokenizer() throws Exception {
        while (tokenizer == null || !tokenizer.hasMoreTokens()) {
            tokenizer = new StringTokenizer(reader.readLine());
        }
        return (tokenizer.nextToken());
    }

    public static int nextInt() throws Exception {
        return (Integer.parseInt(tokenizer()));
    }

    private Task [] tasks;

    public Tasks(Task [] tasks) //конструктор по массиву задач
    {
        this.tasks=tasks;
    }

    public Tasks(int num) // конструктор по кол-ву задач
    {
        this.tasks=new Task [num];
    }

    public int getNum()// получение количества задач
    {
        return tasks.length;
    }

    public Task [] getArrTask()
    {
        return tasks;
    }

    public Task getTask (int num) //получение задачи по номеру
    {
        try {
            return tasks[num];
        }
        catch(ArrayIndexOutOfBoundsException e) {System.err.println(e.getMessage());}
        return null;
    }

    public void setTask(int num, Task task) //изменение задачи по номеру
    {
        try {
            tasks[num] = task; // реализовать исключение по выходу за рамки массива
        }
        catch(ArrayIndexOutOfBoundsException e) {System.err.println(e.getMessage());}
    }

    public void addTask(int num, Task task) //добавление задачи по номеру
    {
        try {
            Task[] arr = new Task[tasks.length + 1];
            for (int i = 0; i < arr.length; i++) {
                if (i < num)
                    arr[i] = tasks[i];
                if (i == num)
                    arr[i] = task;
                if (i > num)
                    arr[i] = tasks[i - 1];
            }
            this.tasks = arr;
        }
        catch(ArrayIndexOutOfBoundsException e) {System.err.println(e.getMessage());}
    }

    public int addTaskByDate(Task task)//вставка задачи в отсортированный массив по времени, возвращает номер на который вставил
    {
        Task[] arr = new Task[tasks.length + 1];
        boolean flag = false;
        int cnt=0;
        for (int i = 0; i < arr.length; i++) {
            if(i<tasks.length) {
                if (tasks[i].getDate().before(task.getDate())) {
                    arr[i] = tasks[i];
                    cnt++;
                    continue;
                }
                if (task.getDate().before(tasks[i].getDate()) & !flag) {
                    arr[i] = task;
                    flag = true;
                } else {
                    if (task.getDate().before(tasks[i].getDate()))
                        arr[i] = tasks[i - 1];
                }
            }
            else
                arr[i]=task;
        }
        this.tasks=arr;
        return cnt;
    }

    public void deleteTask(int num) //удаление задачи по номеру
    {
        try {
            Task[] arr = new Task[tasks.length - 1];
            for (int i = 0; i < tasks.length; i++) {
                if (i > num)
                    arr[i - 1] = tasks[i];
                if (i == num)
                    continue;
                if (i < num)
                    arr[i] = tasks[i];
            }
            this.tasks = arr;
        }
        catch(ArrayIndexOutOfBoundsException e) {System.err.println(e.getMessage());}
    }

    public Task getTaskMinDate()
    {
        Task minDate = tasks[0];
        for(int i=1; i<tasks.length; i++)
        {
            if(tasks[i].getDate().before( minDate.getDate()))
                minDate = tasks[i];
        }
        return minDate;
    }

    public void sortByDate()
    {
        for(int i=0; i<tasks.length-1; i++)
        {
            for(int j=i+1; j<tasks.length; j++)
            {
                if(tasks[i].getDate().before( tasks[j].getDate()))
                {
                    Task temp=tasks[i];
                    tasks[i]=tasks[j];
                    tasks[j]=temp;
                }
            }
        }
    }
    public boolean writerTasks() throws IOException//запись в txt
    {
        try {
            File file = new File("Tasks.txt");
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(this.tasks.length+"");
            fileWriter.write('\n');
            for (int i=0;i<tasks.length;i++)
            {
                fileWriter.write(tasks[i].getName()+"");
                fileWriter.write('\n');
                fileWriter.write(tasks[i].getDescription()+"");
                fileWriter.write('\n');
                fileWriter.write(tasks[i].getDate()+"");
                fileWriter.write('\n');
                fileWriter.write(tasks[i].getContacts().size()+"");
                fileWriter.write('\n');
                for (int j=0;j<tasks[i].getContacts().size();j++)
                {
                    fileWriter.write(tasks[i].getContacts().get(j)+"");
                    fileWriter.write('\n');
                }
            }
            fileWriter.close();
            return true;
        }
        catch (Exception e)
        {
            File file = new File("Tasks.txt");
            FileWriter fileReader = new FileWriter(file);
            fileReader.close();
            return false;
        }
    }
    public Tasks readerTasks() throws Exception//чтение из txt
    {
        Tasks tasks;
        ArrayList<String> contacts;


            tasks = new Tasks(nextInt());
            for (int i=0;i<tasks.tasks.length;i++)
            {
                contacts=new ArrayList<String>();
                tasks.tasks[i].setName(reader.readLine());
                tasks.tasks[i].setDescription(reader.readLine());
                tasks.tasks[i].setDateOfString(reader.readLine());
                for (int j=0;j<nextInt();j++)
                {
                   contacts.add(reader.readLine());
                }
                tasks.tasks[i].setContacts(contacts);
            }
            return tasks;

    }
}
