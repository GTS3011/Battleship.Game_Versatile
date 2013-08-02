package gr.epp.thesis;

import gr.epp.thesis.api.GenericLabel;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;

/**
 *
 * @author vigos.ioannis
 */
public class ChildLabel extends GenericLabel {

    public ChildLabel() {
    }

    public ChildLabel(boolean ownShipList) {
        super(ownShipList);
    }

    @Override
    public void initializeDecorLabel() {
        image = new ImageIcon("graphics/childDecorLabel.png");
    }

    @Override
    public void initializeShipsListLabel() {
        if (ownShipList) {
            this.border = new LineBorder(Color.GREEN, 1, false);
            image = new ImageIcon("graphics/childMyListMotherShip.png");
        } else {
            this.border = new LineBorder(Color.RED, 1, false);
            image = new ImageIcon("graphics/childEnemyListMotherShip.png");
        }
    }

    //To change body of generated methods, choose Tools | Templates.
    @Override
    public void stateChanged(Object obj) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
