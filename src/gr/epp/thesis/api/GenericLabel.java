package gr.epp.thesis.api;

import gr.epp.thesis.mvc.View;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public abstract class GenericLabel extends JLabel implements View {

    protected ImageIcon decorImage;

    public GenericLabel() {
        setLabel();
    }

    public abstract void setLabel();
}
