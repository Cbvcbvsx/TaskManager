package com.company;

public class TaskManager {

    private Task [] tasks;

    public TaskManager (Task [] tasks) //конструктор по массиву задач
    {
        this.tasks=tasks;
    }

    public TaskManager (int num) // конструктор по кол-ву задач
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
}
