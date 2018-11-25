package com.company.gui;


import com.company.Task;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;
import javax.swing.JFrame;




public class CreatorTaskGUI extends JFrame {//создание задачи через отдельное окно
    private JMenuBar mb = new JMenuBar();
    private JMenu m = new JMenu("Настройки");
    private Component label1 = new JLabel("Название");
    private Component label2 = new JLabel("Описание");
    private Component label3 = new JLabel("Дата");
    private Component label4 = new JLabel("Контакты");
    private Component field1 = new JTextField(15);
    private Component field2 = new JTextField(15);
    private Component field3 = new JTextField(15);
    private Component field4 = new JTextField(15);


    public CreatorTaskGUI(JFrame frame, Task task) {
        JDialog dialog = new JDialog(frame, "Событие", true);
        JButton hold = new JButton("Сохранить");
        JButton off = new JButton("Не сохранять");
        JButton add = new JButton("Добавить");
        hold.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    task.setName(((JTextField) field1).getText());
                    task.setDescription(((JTextField) field2).getText());
                    SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd hh:mm:ss ZZZ yyyy", Locale.US);
                    task.setDate((format.parse(((JTextField) field3).getText())));
                    ArrayList<String> contact1 = new ArrayList();
                    contact1.add(((JTextField) field4).getText());
                    task.setContacts(contact1);
                }
                catch(Exception ex) {new ErrorGUI(frame,"Неверная запись даты");}
                dialog.dispose();
            }
        });

        off.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });

        Container contentPane = getContentPane();

        SpringLayout layout = new SpringLayout();
        contentPane.setLayout(layout);
        contentPane.add(hold);
        contentPane.add(off);
        contentPane.add(add);
        contentPane.add(field1);
        contentPane.add(label1);
        contentPane.add(field2);
        contentPane.add(label2);
        contentPane.add(field3);
        contentPane.add(label3);
        contentPane.add(field4);
        contentPane.add(label4);

        layout.putConstraint(SpringLayout.WEST , label1, 10, SpringLayout.WEST , contentPane);
        layout.putConstraint(SpringLayout.NORTH, label1, 25, SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.NORTH, field1, 25, SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.WEST , field1, 80, SpringLayout.WEST , contentPane);

        layout.putConstraint(SpringLayout.WEST , label2, 10, SpringLayout.WEST , contentPane);
        layout.putConstraint(SpringLayout.NORTH, label2, 55, SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.NORTH, field2, 55, SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.WEST , field2, 80, SpringLayout.WEST , contentPane);

        layout.putConstraint(SpringLayout.WEST , label3, 10, SpringLayout.WEST , contentPane);
        layout.putConstraint(SpringLayout.NORTH, label3, 85, SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.NORTH, field3, 85, SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.WEST , field3, 80, SpringLayout.WEST , contentPane);

        layout.putConstraint(SpringLayout.WEST , label4, 10, SpringLayout.WEST , contentPane);
        layout.putConstraint(SpringLayout.NORTH, label4, 115, SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.NORTH, field4, 115, SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.WEST , field4, 80, SpringLayout.WEST , contentPane);
        layout.putConstraint(SpringLayout.NORTH, add, 115, SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.WEST , add, 250, SpringLayout.WEST , contentPane);


        layout.putConstraint(SpringLayout.NORTH, hold, 150, SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.WEST, hold, 20, SpringLayout.WEST, contentPane);

        layout.putConstraint(SpringLayout.NORTH, off, 150, SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.WEST, off, 120, SpringLayout.WEST, contentPane);
        dialog.setContentPane(contentPane);
        dialog.setLocationRelativeTo(frame);//вывод окна в области frame
        dialog.setResizable(false);//запрет на изменение размеров окна
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setSize(450, 400);
        dialog.setVisible(true);
    }
}
