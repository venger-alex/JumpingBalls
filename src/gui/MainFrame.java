package gui;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * Main window of the program with the ball panel
 * @see gui.FieldPanel
 *
 * @version 1.3 2017-10-12
 * @author Alex Venger
 * */
public class MainFrame extends JFrame {
    FieldPanel fieldPanel;

    public MainFrame(String title) {
        super(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        fieldPanel = new FieldPanel();
        add(fieldPanel);

        createMenu();

        pack();
        setVisible(true);
    }

    private void createMenu() {
        ActionListener menuEventHandler = new MenuEventHandler(fieldPanel);

        JMenuBar jMenuBar = new JMenuBar();

        JMenu jMenuActions = new JMenu("Actions");

        JMenuItem jMenuItemAdd = new JMenuItem("Add");
        jMenuItemAdd.addActionListener(menuEventHandler);
        jMenuActions.add(jMenuItemAdd);

        JCheckBoxMenuItem jCheckBoxMenuItemPause = new JCheckBoxMenuItem("Pause");
        jCheckBoxMenuItemPause.addActionListener(menuEventHandler);
        jMenuActions.add(jCheckBoxMenuItemPause);

        JMenuItem jMenuItemClear = new JMenuItem("Clear");
        jMenuItemClear.addActionListener(menuEventHandler);
        jMenuActions.add(jMenuItemClear);

        jMenuActions.addSeparator();

        JMenuItem jMenuItemExit = new JMenuItem("Exit");
        jMenuItemExit.addActionListener(menuEventHandler);
        jMenuActions.add(jMenuItemExit);
        jMenuBar.add(jMenuActions);

        setJMenuBar(jMenuBar);
    }
}
