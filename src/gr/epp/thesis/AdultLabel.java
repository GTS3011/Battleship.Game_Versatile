package gr.epp.thesis;

import gr.epp.thesis.api.GenericLabel;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;

/**
 *
 * @author vigos.ioannis
 */
public class AdultLabel extends GenericLabel {

    public AdultLabel() {
    }

    public AdultLabel(boolean ownShipList) {
        super(ownShipList);
    }

    @Override
    public void initializeValues() {
        this.values = new AdultValues();
    }

    @Override
    public void initializeDecorLabel() {
    }

    @Override
    public void initializeShipsListLabel() {
        if (ownShipList) {
            this.border = new LineBorder(Color.GREEN.darker(), 1, false);
            image = new ImageIcon("graphics/myFleetAdultIcon.png");
        } else {
            this.border = new LineBorder(Color.RED.darker(), 1, false);
            image = new ImageIcon("graphics/enemyFleetAdultIcon.png");
        }
    }

    //To change body of generated methods, choose Tools | Templates.
    @Override
    public void stateChanged(Object obj) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
