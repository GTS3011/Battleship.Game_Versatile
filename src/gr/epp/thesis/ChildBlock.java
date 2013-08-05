package gr.epp.thesis;

import gr.epp.thesis.api.GenericBlock;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;

/**
 *
 * @author vigos.ioannis
 */
public class ChildBlock extends GenericBlock {

    public ChildBlock() {
        super();
    }

    public ChildBlock(int index, boolean ownShipsList) {
        super(index, ownShipsList);
    }

    /**
     * Initializing current player's values.
     */
    @Override
    public void initializeValues() {
        this.values = new ChildValues();
    }

    /**
     * Initializing the Grid. Preparing seaBlocks. Background Color & sea water
     * Icon.
     */
    @Override
    public void initializeGridBlocks() {
        setBackground(values.getSeaColor());
        setIcon(values.getWater());
    }

    /**
     * Initializing the ships list. Preparing available warships.
     */
    @Override
    public void initializeShipsList() {

        if (this.player) {
            setBorder(values.getMyShipListBorder());
            setBackground(values.getMyShipsListBackColor());
            setIcon(new ImageIcon("graphics/childMyListShip.png"));
            setTotalBlocks(1);
            setOnShipsList(true);
        } else {
            setBorder(values.getEnemyShipListBorder());
            setBackground(values.getEnemyShipsListBackColor());
            setIcon(new ImageIcon("graphics/childEnemyListShip.png"));
        }
    }

    //To change body of generated methods, choose Tools | Templates.
    @Override
    public void stateChanged(Object obj) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
