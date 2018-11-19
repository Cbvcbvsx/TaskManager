package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Event extends JFrame {

    public Event() {
        super("Уведомление ");
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        JButton hold = new JButton("Отложить");
        JButton off = new JButton("Выключить");
        hold.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        off.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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