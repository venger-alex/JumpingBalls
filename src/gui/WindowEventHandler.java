package gui;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Window event handler
 *
 * @aversion 1.0 2017-10-05
 * @author Alex Venger
 */
public class WindowEventHandler extends WindowAdapter {
    /**
     * It is necessary if you use only the AWT library,
     * to close main window
     * @param e Window event
     */
    @Override
    public void windowClosing(WindowEvent e) {
        System.exit(0);
    }
}
