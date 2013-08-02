package gr.epp.thesis;

import gr.epp.thesis.api.GenericLabel;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.border.Border;
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
    public void initializeDecorLabel() {
        image = new ImageIcon("graphics/adultDecorLabel.png");
        setHorizontalAlignment(CENTER);
        setIcon(image);
    }

    @Override
    public void initializeShipsListLabel() {
        if (ownShipList) {
            this.border = new LineBorder(Color.GREEN.darker(), 1, false);
            image = new ImageIcon("graphics/myFleetAdultIcon.gif");
            setHorizontalAlignment(CENTER);
            setBorder(border);
            setIcon(image);
        } else {
            this.border = new LineBorder(Color.RED.darker(), 1, false);
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
