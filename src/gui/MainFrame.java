package gui;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

/**
 * Main window of the program with the ball panel
 * @see gui.FieldPanel
 *
 * @version 1.4 2017-10-13
 * @author Alex Venger
 * */
public class MainFrame extends JFrame {
    FieldPanel fieldPanel;

    public MainFrame(String title) {
        super(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        fieldPanel = new FieldPanel(this);
        add(fieldPanel);

        createMenu();

        pack();
        setVisible(true);
    }

    private void createMenu() {
        ActionListener menuEventHandler = new MenuEventHandler(fieldPanel);

        JMenuBar jMenuBar = new JMenuBar();

        JMenu jMenuActions = new JMenu("Actions");
        jMenuActions.setMnemonic(KeyEvent.VK_A);
        jMenuActions.setToolTipText("Various actions (add, pause, clear ...)");

        JMenuItem jMenuItemAdd = new JMenuItem("Add");
        jMenuItemAdd.setMnemonic(KeyEvent.VK_D);
        jMenuItemAdd.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.ALT_DOWN_MASK | InputEvent.CTRL_DOWN_MASK));
        jMenuItemAdd.setToolTipText("Add a random flying ball");
        jMenuItemAdd.addActionListener(menuEventHandler);
        jMenuActions.add(jMenuItemAdd);

        JCheckBoxMenuItem jCheckBoxMenuItemPause = new JCheckBoxMenuItem("Pause");
        jCheckBoxMenuItemPause.setMnemonic(KeyEvent.VK_P);
        jCheckBoxMenuItemPause.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.ALT_DOWN_MASK | InputEvent.CTRL_DOWN_MASK));
        jCheckBoxMenuItemPause.setToolTipText("Pause the flight of balls");
        jCheckBoxMenuItemPause.addActionListener(menuEventHandler);
        jMenuActions.add(jCheckBoxMenuItemPause);

        JMenuItem jMenuItemClear = new JMenuItem("Clear");
        jMenuItemClear.setMnemonic(KeyEvent.VK_R);
        jMenuItemClear.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.ALT_DOWN_MASK | InputEvent.CTRL_DOWN_MASK));
        jMenuItemClear.setToolTipText("Remove all balls and clear the field");
        jMenuItemClear.addActionListener(menuEventHandler);
        jMenuActions.add(jMenuItemClear);

        jMenuActions.addSeparator();

        JMenuItem jMenuItemExit = new JMenuItem("Exit");
        jMenuItemExit.setMnemonic(KeyEvent.VK_X);
        jMenuItemExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.ALT_DOWN_MASK | InputEvent.CTRL_DOWN_MASK));
        jMenuItemExit.setToolTipText("Exit from the program");
        jMenuItemExit.addActionListener(menuEventHandler);
        jMenuActions.add(jMenuItemExit);
        jMenuBar.add(jMenuActions);

        setJMenuBar(jMenuBar);
    }
}
