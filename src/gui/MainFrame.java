package gui;

import javax.swing.*;

/**
 * Main window of the program with the ball panel
 * @see FieldPanel
 *
 * @aversion 1.0 2017-10-05
 * @author Alex Venger
 * */
public class MainFrame extends JFrame {
    private FieldPanel fieldPanel;

    public FieldPanel getFieldPanel() {
        return fieldPanel;
    }

    public MainFrame(String title) {
        super(title);
        fieldPanel = new FieldPanel();
        add(fieldPanel);
        pack();

        /*
         * It is necessary if you use only the AWT library
         */
        //addWindowListener(new WindowEventHandler());
    }
}
