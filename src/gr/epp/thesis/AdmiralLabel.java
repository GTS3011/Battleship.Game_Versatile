package gr.epp.thesis;

import gr.epp.thesis.api.GenericLabel;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;

/**
 *
 * @author USER1
 */
public class AdmiralLabel extends GenericLabel {

    public AdmiralLabel() {
    }

    public AdmiralLabel(boolean ownShipList) {
        super(ownShipList);
    }

    @Override
    public void initializeDecorLabel() {
        image = new ImageIcon("graphics/admiralDecorLabel.png");
        setHorizontalAlignment(CENTER);
        setIcon(image);
    }

    @Override
    public void initializeShipsListLabel() {
        if (ownShipList) {
            this.border = new LineBorder(Color.GREEN.darker(), 1, false);
            image = new ImageIcon("graphics/myFleetAdmiralIcon.gif");
            setHorizontalAlignment(CENTER);
            setIcon(image);
        } else {
            this.border = new LineBorder(Color.RED.darker(), 1, false);
            image = new ImageIcon("graphics/enemyFleetAdmiralIcon.gif");
            setHorizontalAlignment(CENTER);
            setIcon(image);
        }
    }

    //To change body of generated methods, choose Tools | Templates.
    @Override
    public void stateChanged(Object obj) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
