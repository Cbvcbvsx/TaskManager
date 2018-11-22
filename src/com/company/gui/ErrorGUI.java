package com.company.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ErrorGUI extends JFrame{
    public ErrorGUI(JFrame frame,String error) {
        JDialog dialog = new JDialog(frame, "Ошибка", true);
        JButton ok = new JButton("OK");
        Component label = new JLabel(error);
        Container container = getContentPane();
        container.add(label);
        container.add(ok,"South");
        ok.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });
        dialog.setResizable(false);//запрет на изменение размеров окна
        dialog.setLocationRelativeTo(frame);//вывод окна в области frame
        dialog.setContentPane(container);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setSize(200, 100);
        dialog.setVisible(true);
    }
}
