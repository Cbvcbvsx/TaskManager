package com.company.gui;

import com.company.Task;
import com.company.Tasks;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class TrayWindow {

    public TrayWindow(Tasks tasks)
    {
        JWindow window = new JWindow();
        window.setSize(400, 200);
        window.setLocation(1200,670);
        Object[] columnsHeader = new String[]{"Название", "Дата-время"};
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(columnsHeader);
        for (int i = 0; i < tasks.getNum(); i++)
            tableModel.addRow(new Object[]{tasks.getTask(i).getName(), tasks.getTask(i).getDate()});
        JTable table = new JTable(tableModel)
        {
            @Override
            public boolean isCellEditable ( int row, int column )// запрет изменения ячеек
            {
                return false;
            }
        };
        Box contents = new Box(BoxLayout.Y_AXIS);
        contents.add(new JScrollPane(table));
        window.getContentPane().add(contents);
        window.setVisible(true);

    }
}
