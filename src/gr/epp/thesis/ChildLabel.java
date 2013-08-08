package gr.epp.thesis;

import gr.epp.thesis.api.GenericLabel;

/**
 * @author tsoutsas.yiorgos & vigkos.ioannis
 * @project Thesis_Battleship.Game
 * @author Vidakis.Nikolas & Vellis Giorgos
 * @since Spring Semester 2013
 * @institute Applied Informatics and Multimedia - TEI of Crete
 */
public class ChildLabel extends GenericLabel {

    public ChildLabel() {
        super();
        setIcon(values.getDecor());
        setHorizontalAlignment(CENTER);
    }

    public ChildLabel(boolean player) {
        super(player);
    }

    /**
     * Initializing Child player's values.
     */
    @Override
    public void initializeValues() {
        this.values = new ChildValues();
    }

    /**
     * Initializing Child player's label.
     */
    @Override
    public void initializePlayerLabel() {
        setHorizontalAlignment(CENTER);
        if (player) {
            setBorder(values.getMyShipsListBorder());
            setIcon(values.getPlayerBanner());
        } else {
            setBorder(values.getEnemyShipsListBorder());
            setIcon(values.getEnemyPlayerBanner());
        }
    }

    //To change body of generated methods, choose Tools | Templates.
    @Override
    public void stateChanged(Object obj) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
