package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreatorTaskGUI extends JFrame {//создание задачи через отдельное окно
    private JMenuBar mb = new JMenuBar();
    private JMenu m = new JMenu("Настройки");

    public CreatorTaskGUI() {
        super("Событие ");
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        JButton hold = new JButton("Сохранить");
        JButton off = new JButton("Не сохранять");
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
        getContentPane().add(panel,"South");
        setSize(600, 300);
        setVisible(true);

    }
}