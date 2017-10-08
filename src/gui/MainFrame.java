package gui;

import javax.swing.*;

/**
 * Main window of the program with the ball panel
 * @see gui.FieldPanel
 *
 * @version 1.1 2017-10-08
 * @author Alex Venger
 * */
public class MainFrame extends JFrame {
    private FieldPanel fieldPanel;

    public MainFrame(String title) {
        super(title);
        fieldPanel = new FieldPanel();
        add(fieldPanel);
        pack();
    }
}
