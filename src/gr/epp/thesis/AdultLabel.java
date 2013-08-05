package gr.epp.thesis;

import gr.epp.thesis.api.GenericLabel;
import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;

/**
 *
 * @author vigos.ioannis
 */
public class AdultLabel extends GenericLabel {

    public AdultLabel() {
    }

    public AdultLabel(boolean player) {
        super(player);
    }

    @Override
    public void initializeValues() {
        this.values = new AdultValues();
    }

    @Override
    public void initializeShipsListLabel() {
        if (player) {
            this.border = values.getMyShipListBorder();
            playerLabel = values.getPlayerBanner();
        } else {
            this.border = values.getEnemyShipListBorder();
            playerLabel = values.getEnemyPlayerBanner();
        }
    }

    //To change body of generated methods, choose Tools | Templates.
    @Override
    public void stateChanged(Object obj) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
