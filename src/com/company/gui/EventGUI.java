package com.company.gui;

import com.company.Event;
import com.company.Tasks;
import com.company.TimeDemon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class EventGUI extends JFrame {//вывод информации о срабатываниии события и принятия решений о дальнейших действиях с ним

    public EventGUI(Tasks tasks) throws InterruptedException {
        super("Уведомление ");
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        JButton hold = new JButton("Отложить");
        JButton off = new JButton("Выключить");
        hold.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tasks.getTask(0).setDate(new Date(tasks.getTask(0).getDate().getTime()+60000));//прибавить ко времени 10 мин
                tasks.sortByDate();
                Event.setB();
                dispose();
            }
        });

        off.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tasks.deleteTask(0);
                tasks.sortByDate();
                Event.setB();
                dispose();
            }
        });
        panel.add(hold);
        panel.add(off);
        setContentPane(panel);
        setSize(600, 300);
        setVisible(true);
    }


}