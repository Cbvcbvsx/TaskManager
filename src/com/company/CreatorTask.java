package com.company;

import javax.swing.*;

public class CreatorTask extends JFrame {
    private JMenuBar mb = new JMenuBar();
    private JMenu m = new JMenu("Настройки");

    public CreatorTask() {
        JFrame frame = new JFrame("Добавление задачи");

        frame.setSize(600,300);
        JButton add = new JButton("Добавить");
        mb.add(m);
        Box contents = new Box(BoxLayout.Y_AXIS);
        frame.getContentPane().add(contents);
        JPanel buttons = new JPanel();
        buttons.add(add);
        frame.getContentPane().add(buttons, "South");
        // Вывод окна на экран
        frame.setVisible(true);
    }


}