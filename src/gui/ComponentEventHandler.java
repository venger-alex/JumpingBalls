package gui;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

/**
 * Component event handler
 *
 * @aversion 1.0 2017-10-05
 * @author Alex Venger
 */
public class ComponentEventHandler extends ComponentAdapter {
    /**
     *  When changing the size of a component (panel), resize the field
     * @param e Event object
     */
    @Override
    public void componentResized(ComponentEvent e) {
        FieldPanel fieldPanel = (FieldPanel) e.getComponent();
        Rectangle newFieldShape = new Rectangle(fieldPanel.getX() + 3,
                fieldPanel.getY() + 3,
                fieldPanel.getWidth() - 6,
                fieldPanel.getHeight() - 6);
        fieldPanel.getField().setFieldShape(newFieldShape);
    }
}
