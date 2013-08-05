package gr.epp.thesis.api;

import gr.epp.thesis.mvc.View;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.border.Border;

public abstract class GenericLabel extends JLabel implements View {

    protected GenericValues values;
    protected ImageIcon image;
    protected boolean ownShipList;
    protected Border border;

    public GenericLabel() {
        initializeValues();
        setIcon(values.getDecor());
        setHorizontalAlignment(CENTER);
    }

    public GenericLabel(boolean ownShipList) {
        initializeValues();
        this.ownShipList = ownShipList;
        this.border = border;
        initializeShipsListLabel();
        setHorizontalAlignment(CENTER);
        setBorder(border);
        setIcon(image);
    }

    public abstract void initializeValues();

    public abstract void initializeDecorLabel();

    public abstract void initializeShipsListLabel();
}
