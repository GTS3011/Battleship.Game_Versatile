package gr.epp.thesis.api;

import gr.epp.thesis.mvc.View;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public abstract class GenericLabel extends JLabel implements View {

    protected ImageIcon image;
    protected boolean ownShipList;

    public GenericLabel() {
        initializeDecorLabel();
    }

    public GenericLabel(boolean ownShipList) {
        System.out.println("okk");
        this.ownShipList = ownShipList;
        initializeShipsListLabel();
    }

    public abstract void initializeDecorLabel();

    public abstract void initializeShipsListLabel();
}
