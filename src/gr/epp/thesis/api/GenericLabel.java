package gr.epp.thesis.api;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public abstract class GenericLabel extends JLabel implements ViewItem {

    protected ImageIcon decorImage;

    public GenericLabel() {
        setLabel();
    }

    public abstract void setLabel();
}
