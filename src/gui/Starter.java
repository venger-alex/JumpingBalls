package gui;

import java.awt.*;
import javax.swing.*;

/**
 * Example of animation of jumping balls (multithreaded, new thread for each new ball)
 * Start the main program window
 *
 * @aversion 1.0 2017-10-05
 * @author Alex Venger
 */
public class Starter {
    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            JFrame mainFrame = new MainFrame("Jumping balls v1.0 - click the mouse to add a balls");
            mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            mainFrame.setVisible(true);
        });

    }
}
