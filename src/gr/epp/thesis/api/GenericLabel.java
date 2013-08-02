package gr.epp.thesis.api;

import gr.epp.thesis.mvc.View;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.border.Border;

public abstract class GenericLabel extends JLabel implements View {

    protected ImageIcon image;
    protected boolean ownShipList;
    protected Border border;

    public GenericLabel() {
        initializeDecorLabel();
    }

    public GenericLabel(boolean ownShipList) {
        this.ownShipList = ownShipList;
        this.border = border;
        initializeShipsListLabel();
        setBorder(border);
    }

    public abstract void initializeDecorLabel();

    public abstract void initializeShipsListLabel();
}
