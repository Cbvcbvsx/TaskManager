package com.company.gui;

import com.company.Task;
import com.company.Tasks;
import com.company.gui.ChangeTaskGUI;
import com.company.gui.CreatorTaskGUI;
import com.company.gui.ErrorGUI;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.io.*;


import static com.company.Save.writeTasks;

public class GUI extends JFrame {
    private JMenuBar mb = new JMenuBar();

    private JMenu m = new JMenu("Настройки");
    private JMenu m1 = new JMenu("Настройки сохранения");
    private JMenu m2 = new JMenu("Настройки оповещения");
    private JRadioButtonMenuItem alert1 = new JRadioButtonMenuItem("Вывод окна");
    private JRadioButtonMenuItem alert2 = new JRadioButtonMenuItem("Cигнал + вывод в консоль");
    private JRadioButtonMenuItem alert3 = new JRadioButtonMenuItem("Открытие другой программы");
    private JRadioButtonMenuItem save1 = new JRadioButtonMenuItem("Сохранение в виде текстового файла");
    private JRadioButtonMenuItem save2 = new JRadioButtonMenuItem("Сохранение в виде сериализованного объекта");
    private JRadioButtonMenuItem save3 = new JRadioButtonMenuItem("Сохранение в виде сжатого текстового файла(zip)");
    private Object[] columnsHeader = new String[]{"Название", "Описание", "Дата-время", "Контакты"};
    private DefaultTableModel tableModel;
    private JTable table1;

    //работа с tray
    private TrayIcon iconTr;
    private SystemTray sT = SystemTray.getSystemTray();
    public boolean chetTray = false; //переменная, чтобы был вывод сообщения в трее только при первом сворачивании


    public GUI(Tasks tasks) throws IOException {
        JFrame frame = new JFrame("Планировщик задач");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 300);
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
        m2.add(alert1);
        m2.add(alert2);
        m2.add(alert3);
        m1.add(save1);
        m1.add(save2);
        m1.add(save3);
        m.add(m1);
        m.add(m2);
        frame.getContentPane().add(BorderLayout.NORTH, mb);//добавление верхней панельки

        tableModel = new DefaultTableModel();
        // Определение столбцов
        tableModel.setColumnIdentifiers(columnsHeader);
        // Наполнение модели данными
        tasks.sortByDate();
        for (int i = 0; i < tasks.getNum(); i++)
            tableModel.addRow(new Object[]{tasks.getTask(i).getName(), tasks.getTask(i).getDescription(), tasks.getTask(i).getDate(), tasks.getTask(i).arrayListToString()});
        // Создание таблицы на основании модели данных
        table1 = new JTable(tableModel)
        {
            @Override
            public boolean isCellEditable ( int row, int column )// запрет изменения ячеек
            {
                return false;
            }
        };




        // Создание кнопки добавления строки таблицы
        JButton add = new JButton("Добавить");
        add.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Task task=new Task();
                new CreatorTaskGUI(frame, task);
                int idx = tasks.addTaskByDate(task);
                // Вставка новой строки после выделенной
                tableModel.insertRow(idx, new Object[]{
                        tasks.getTask(idx).getName(),
                        tasks.getTask(idx).getDescription(),
                        tasks.getTask(idx).getDate(),
                        tasks.getTask(idx).arrayListToString()});
                //Сохранение изменений
                try (Writer wr = new FileWriter("save.txt")) {
                    writeTasks(tasks, wr);
                } catch (IOException ioe) {
                    System.err.println(ioe.getMessage());
                }
            }
        });


        JButton change = new JButton("Изменить");//настроить окошко ошибки, когда строка не выбрана
        change.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int idx = table1.getSelectedRow();
                if(idx==-1)
                {
                    new ErrorGUI(frame,"Выберите строку");
                    return;
                }
                new ChangeTaskGUI(frame,tasks.getTask(idx));
                tableModel.removeRow(idx);
                tableModel.insertRow(idx, new Object[]{tasks.getTask(idx).getName(), tasks.getTask(idx).getDescription(),
                        tasks.getTask(idx).getDate(), tasks.getTask(idx).arrayListToString()});
                //Сохранение изменений
                try (Writer wr = new FileWriter("save.txt")) {
                    writeTasks(tasks, wr);
                } catch (IOException ioe) {
                    System.err.println(ioe.getMessage());
                }
            }
        });


        // Создание кнопки удаления строки таблицы
        JButton remove = new JButton("Удалить");//настроить окошко ошибки, когда строка не выбрана
        remove.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Номер выделенной строки
                int idx = table1.getSelectedRow();
                // Удаление выделенной строки
                tableModel.removeRow(idx);
                //Удаление задачи из массива
                tasks.deleteTask(idx);
                //Сохранение изменений
                try (Writer wr = new FileWriter("save.txt")) {
                    writeTasks(tasks, wr);
                } catch (IOException ioe) {
                    System.err.println(ioe.getMessage());
                }
            }
        });


        // Формирование интерфейса
        Box contents = new Box(BoxLayout.Y_AXIS);
        contents.add(new JScrollPane(table1));
        frame.getContentPane().add(contents);
        JPanel buttons = new JPanel();
        buttons.add(add);
        buttons.add(change);
        buttons.add(remove);
        frame.getContentPane().add(buttons, "South");


        /*iconTr = new TrayIcon(ImageIO.read(new File("4d87fef27a.jpg")), "Демонстрация сворачивания в трей");
        iconTr.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                setVisible(true);
                setState(JFrame.NORMAL);
                removeTr();
            }
        });
        //обработчик мыши
        MouseListener mouS = new MouseListener() {
            public void mouseClicked(MouseEvent ev) {
            }

            public void mouseEntered(MouseEvent ev) {
            }

            public void mouseExited(MouseEvent ev) {
            }

            public void mousePressed(MouseEvent ev) {
            }

            public void mouseReleased(MouseEvent ev) {
            }
        };
        iconTr.addMouseListener(mouS);
        MouseMotionListener mouM = new MouseMotionListener() {
            public void mouseDragged(MouseEvent ev) {
            }

            //при наведении
            public void mouseMoved(MouseEvent ev) {
                boolean flg = false;
                iconTr.setToolTip("Двойной щелчок - развернуть");
            }
        };

        iconTr.addMouseMotionListener(mouM);
        addWindowStateListener(new WindowStateListener() {
            public void windowStateChanged(WindowEvent ev) {
                if (ev.getNewState() == JFrame.ICONIFIED) {
                    setVisible(false);
                    addT();
                }
            }
        });


    }
    private void removeTr() {
        sT.remove(iconTr);
    }

    // метод добавления в трей
    private void addT() {
        try {
            sT.add(iconTr);
            if (chetTray == false) {
                iconTr.displayMessage("Демонстрация сворачивания в трей", "Программа свернулась", TrayIcon.MessageType.INFO);
            }
            chetTray = true;
        } catch (AWTException ex) {
            ex.printStackTrace();
        }*/
        frame.setVisible(true);
    }
}

