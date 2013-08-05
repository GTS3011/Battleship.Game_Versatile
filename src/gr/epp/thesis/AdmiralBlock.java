package gr.epp.thesis;

import gr.epp.thesis.api.GenericBlock;
import javax.swing.ImageIcon;

/**
 *
 * @author USER1
 */
public class AdmiralBlock extends GenericBlock {

    public AdmiralBlock() {
    }

    public AdmiralBlock(int index, boolean ownShipsList) {
        super(index, ownShipsList);
    }

    /**
     * Initializing current player's values.
     */
    @Override
    public void initializeValues() {
        this.values = new AdmiralValues();
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
                    setIcon(values.getMyWarshipsIcons()[index]);
                    //setIcon(new ImageIcon("graphics/aircraftCarrier.gif"));
                    setTotalBlocks(5);
                    setOnShipsList(true);
                    break;
                case (1):
                    //setIcon(values.getMyWarshipsIcons(index));
                    //setIcon(new ImageIcon("graphics/battleship.gif"));
                    setTotalBlocks(4);
                    setOnShipsList(true);
                    break;
                case (2):
                    setIcon(new ImageIcon("graphics/battleship.gif"));
                    setTotalBlocks(4);
                    setOnShipsList(true);
                    break;
                case (3):
                    setIcon(new ImageIcon("graphics/submarine.gif"));
                    setTotalBlocks(3);
                    setOnShipsList(true);
                    break;
                case (4):
                    setIcon(new ImageIcon("graphics/submarine.gif"));
                    setTotalBlocks(3);
                    setOnShipsList(true);
                    break;
                case (5):
                    setIcon(new ImageIcon("graphics/submarine.gif"));
                    setTotalBlocks(3);
                    setOnShipsList(true);
                    break;
                case (6):
                    setIcon(new ImageIcon("graphics/destroyer.gif"));
                    setTotalBlocks(2);
                    setOnShipsList(true);
                    break;
                case (7):
                    setIcon(new ImageIcon("graphics/destroyer.gif"));
                    setTotalBlocks(2);
                    setOnShipsList(true);
                    break;
                case (8):
                    setIcon(new ImageIcon("graphics/patrolShip.gif"));
                    setTotalBlocks(1);
                    setOnShipsList(true);
                    break;
                case (9):
                    setIcon(new ImageIcon("graphics/patrolShip.gif"));
                    setTotalBlocks(1);
                    setOnShipsList(true);
                    break;
                case (10):
                    setIcon(new ImageIcon("graphics/patrolShip.gif"));
                    setTotalBlocks(1);
                    setOnShipsList(true);
                    break;
            }
        } else {
            setBorder(values.getEnemyShipListBorder());
            setBackground(values.getEnemyShipsListBackColor());
            switch (index) {
                case (0):
                    setIcon(new ImageIcon("graphics/aircraftCarrier.gif"));
                    break;
                case (1):
                    setIcon(new ImageIcon("graphics/battleship.gif"));
                    break;
                case (2):
                    setIcon(new ImageIcon("graphics/battleship.gif"));
                    break;
                case (3):
                    setIcon(new ImageIcon("graphics/submarine.gif"));
                    break;
                case (4):
                    setIcon(new ImageIcon("graphics/submarine.gif"));
                    break;
                case (5):
                    setIcon(new ImageIcon("graphics/submarine.gif"));
                    break;
                case (6):
                    setIcon(new ImageIcon("graphics/destroyer.gif"));
                    break;
                case (7):
                    setIcon(new ImageIcon("graphics/destroyer.gif"));
                    break;
                case (8):
                    setIcon(new ImageIcon("graphics/patrolShip.gif"));
                    break;
                case (9):
                    setIcon(new ImageIcon("graphics/patrolShip.gif"));
                    break;
                case (10):
                    setIcon(new ImageIcon("graphics/patrolShip.gif"));
                    break;
            }
        }
    }

    //To change body of generated methods, choose Tools | Templates.
    @Override
    public void stateChanged(Object obj) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
