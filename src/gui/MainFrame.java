package gui;

import javax.swing.*;

/**
 * Main window of the program with the ball panel
 * @see gui.FieldPanel
 *
 * @version 1.2 2017-10-09
 * @author Alex Venger
 * */
public class MainFrame extends JFrame {

    public MainFrame(String title) {
        super(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(new FieldPanel());
        pack();
        setVisible(true);
    }
}
