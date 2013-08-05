package gr.epp.thesis;

import gr.epp.thesis.api.GenericBlock;
import gr.epp.thesis.api.GenericValues;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;

/**
 *
 * @author vigos.ioannis
 */
public class AdultBlock extends GenericBlock {

    public AdultBlock() {
        super();
        System.out.println("" + values.getRows());
    }

    public AdultBlock(int index, boolean ownShipsList) {
        super(index, ownShipsList);
    }

    /**
     * Initializing current player's values.
     */
    @Override
    public void initializeValues() {
        this.values = new AdultValues();
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
            switch (this.index) {
                case (0):
                    setIcon(values.getMyWarshipsIcons().get(4 - index));
                    setTotalBlocks(5);
                    setOnShipsList(true);
                    break;
                case (1):
                    setIcon(values.getMyWarshipsIcons().get(4 - index));
                    setTotalBlocks(4);
                    setOnShipsList(true);
                    break;
                case (2):
                    setIcon(values.getMyWarshipsIcons().get(4 - index));
                    setTotalBlocks(3);
                    setOnShipsList(true);
                    break;
                case (3):
                    setIcon(values.getMyWarshipsIcons().get(4 - index));
                    setTotalBlocks(2);
                    setOnShipsList(true);
                    break;
                case (4):
                    setIcon(values.getMyWarshipsIcons().get(4 - index));
                    setTotalBlocks(1);
                    setOnShipsList(true);
                    break;
            }
        } else {
            setBorder(values.getEnemyShipListBorder());
            setBackground(values.getEnemyShipsListBackColor());
            setIcon(values.getMyWarshipsIcons().get(4 - index));
        }
    }

    //To change body of generated methods, choose Tools | Templates.
    @Override
    public void stateChanged(Object obj) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
