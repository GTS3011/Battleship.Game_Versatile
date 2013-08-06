package gr.epp.thesis;

import gr.epp.thesis.api.GenericLabel;

/**
 * @author tsoutsas.yiorgos & vigkos.ioannis
 * @project Thesis_Battleship.Game
 * @author Vidakis.Nikolas & Vellis Giorgos
 * @since Spring Semester 2013
 * @institute Applied Informatics and Multimedia - TEI of Crete
 */
public class AdmiralLabel extends GenericLabel {

    public AdmiralLabel() {
        super();
        setIcon(values.getDecor());
        setHorizontalAlignment(CENTER);
    }

    public AdmiralLabel(boolean player) {
        super(player);
    }

    /**
     * Initializing Admiral player's values.
     */
    @Override
    public void initializeValues() {
        this.values = new AdmiralValues();
    }

    /**
     * Initializing Admiral player's label.
     */
    @Override
    public void initializePlayerLabel() {
        setHorizontalAlignment(CENTER);
        if (player) {
            setBorder(values.getMyShipListBorder());
            setIcon(values.getPlayerBanner());
        } else {
            setBorder(values.getEnemyShipListBorder());
            setIcon(values.getEnemyPlayerBanner());
        }
    }

    //To change body of generated methods, choose Tools | Templates.
    @Override
    public void stateChanged(Object obj) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
