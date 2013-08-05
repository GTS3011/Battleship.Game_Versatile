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

    public AdmiralLabel(boolean player) {
        super(player);
    }

    @Override
    public void initializeValues() {
        this.values = new AdmiralValues();
    }

    @Override
    public void initializeShipsListLabel() {
        if (player) {
            this.border = values.getEnemyShipListBorder();
            this.playerLabel = values.getPlayerBanner();
        } else {
            this.border = values.getEnemyShipListBorder();
            this.playerLabel = values.getEnemyPlayerBanner();
        }
    }

    //To change body of generated methods, choose Tools | Templates.
    @Override
    public void stateChanged(Object obj) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
