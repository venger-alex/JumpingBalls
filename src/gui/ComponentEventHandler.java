package gui;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

/**
 * Component event handler
 *
 * @version 1.1 2017-10-08
 * @author Alex Venger
 */
public class ComponentEventHandler extends ComponentAdapter {
    /**
     *  When changing the size of a component (panel), resize the field
     * @param e Event object
     */
    @Override
    public void componentResized(ComponentEvent e) {
        if(e.getComponent() instanceof FieldPanel) {
            ((FieldPanel) e.getComponent()).resizeField();
            e.getComponent().repaint();
        }
    }
}
