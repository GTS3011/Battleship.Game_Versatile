package gr.epp.thesis;

import gr.epp.thesis.api.GenericLabel;
import javax.swing.ImageIcon;

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
            image = new ImageIcon("graphics/myFleetAdultIcon.gif");
            setHorizontalAlignment(CENTER);
            setIcon(image);
        } else {
            image = new ImageIcon("graphics/enemyFleetAdultIcon.gif");
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
