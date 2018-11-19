package com.company;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Date;

import static com.company.Save.writeTasks;

public class GUI extends JFrame
{
    private JMenuBar mb = new JMenuBar();

    private JMenu m = new JMenu("Настройки");
    private JMenu m1 = new JMenu("Настройки сохранения");
    private JMenu m2 = new JMenu("Настройки оповещения");
    private JRadioButtonMenuItem alert1 = new JRadioButtonMenuItem("Базовое оповещение(вывод окна)");
    private JRadioButtonMenuItem alert2 = new JRadioButtonMenuItem("Оповещение сигналом");
    private JRadioButtonMenuItem alert3 = new JRadioButtonMenuItem("Оповещение другой программой");
    private JRadioButtonMenuItem save1 = new JRadioButtonMenuItem("Сохранение в виде текстового файла");
    private JRadioButtonMenuItem save2 = new JRadioButtonMenuItem("Сохранение в виде сериализованного объекта");
    private JRadioButtonMenuItem save3 = new JRadioButtonMenuItem("Сохранение в виде сжатого текстового файла(zip)");
    private Object[] columnsHeader = new String[] {"Название", "Описание", "Дата-время", "Контакты"};
    private DefaultTableModel tableModel;
    private JTable table1;



    public GUI(TaskManager tasks) {
        JFrame frame = new JFrame("Планировщик задач");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,300);
        mb.add(m);
        // организуем переключатели в логическую группу(чтобы можно было выбрать только 1 параметр)
        ButtonGroup bg1 = new ButtonGroup();
        bg1.add(alert1);
        bg1.add(alert2);
        bg1.add(alert3);
        ButtonGroup bg2 = new ButtonGroup();
        bg2.add(save1);
        bg2.add(save2);
        bg2.add(save3);
        // добавление компонентов в разные части меню
        m1.add(alert1);
        m1.add(alert2);
        m1.add(alert3);
        m2.add(save1);
        m2.add(save2);
        m2.add(save3);
        m.add(m1);
        m.add(m2);
        frame.getContentPane().add(BorderLayout.NORTH, mb);//добавление верхней панельки

        tableModel = new DefaultTableModel();
        // Определение столбцов
        tableModel.setColumnIdentifiers(columnsHeader);
        // Наполнение модели данными
        for (int i = 0; i < tasks.getNum(); i++)
            tableModel.addRow(new Object[] {tasks.getTask(i).getName(), tasks.getTask(i).getDescript(), tasks.getTask(i).getDate(), tasks.getTask(i).getContact()});
        // Создание таблицы на основании модели данных
        table1 = new JTable(tableModel);


        // Создание кнопки добавления строки таблицы
        JButton add = new JButton("Добавить");
        add.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Номер выделенной строки
                ArrayList <String> contact1 = new ArrayList();
                contact1.add("ФИО");
                int idx = table1.getSelectedRow();
                new CreatorTask();
                tasks.addTask(idx+1,new Task("Новая задача №" + String.valueOf(table1.getRowCount()), "Описание", new Date(), contact1));
                // Вставка новой строки после выделенной
                tableModel.insertRow(idx+1, new String[] {
                        tasks.getTask(idx+1).getName().toString(),
                        tasks.getTask(idx+1).getDescript().toString(),
                        tasks.getTask(idx+1).getDate().toString(),
                        tasks.getTask(idx+1).getContact().toString()});
                //Сохранение изменений
                try(Writer wr=new FileWriter("save.txt"))
                {
                    writeTasks(tasks,wr);
                }
                catch(IOException ioe) {System.err.println(ioe.getMessage());}
            }
        });


        // Создание кнопки удаления строки таблицы
        JButton remove = new JButton("Удалить");
        remove.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Номер выделенной строки
                int idx = table1.getSelectedRow();
                // Удаление выделенной строки
                tableModel.removeRow(idx);
                //Удаление задачи из массива
                tasks.deleteTask(idx);
                //Сохранение изменений
                try(Writer wr=new FileWriter("save.txt"))
                {
                    writeTasks(tasks,wr);
                }
                catch(IOException ioe) {System.err.println(ioe.getMessage());}
            }
        });


        // Формирование интерфейса
        Box contents = new Box(BoxLayout.Y_AXIS);
        contents.add(new JScrollPane(table1));
        frame.getContentPane().add(contents);
        JPanel buttons = new JPanel();
        buttons.add(add);
        buttons.add(remove);
        frame.getContentPane().add(buttons, "South");
        // Вывод окна на экран
        frame.setVisible(true);
    }

}
