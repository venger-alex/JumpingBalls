package gui;

import javax.swing.*;

/**
 * Example of animation of jumping balls (multithreaded, new thread for each new ball)
 * Start the main program window
 *
 * @version 1.2 2017-10-09
 * @author Alex Venger
 */
public class Starter {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame("Jumping balls v1.1 - click the mouse to add a balls"));
    }
}
