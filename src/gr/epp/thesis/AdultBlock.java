package gr.epp.thesis;

import gr.epp.thesis.api.GenericBlock;
import java.awt.Color;
import javax.swing.ImageIcon;

/**
 *
 * @author vigos.ioannis
 */
public class AdultBlock extends GenericBlock {

    public AdultBlock() {
        super();
    }

    public AdultBlock(int index, boolean ownership) {
        super(index, ownership);
    }

    /*
     * Grid's seaBlocks:
     */
    @Override
    public void initializeGridBlocks() {
        setSeaColor(Color.CYAN);
        setBackground(getSeaColor());
    }

    /*
     * Enemy's and My Ship List.
     */
    @Override
    public void initializeShipList() {
        if (this.ownership) {
            switch (this.index) {
                case (0):
                    setBackground(Color.GREEN);
                    setText("MY FLEET");
                    break;
                case (1):
                    setBackground(Color.LIGHT_GRAY);
                    setIcon(new ImageIcon("graphics/aircraftCarrier.gif"));
                    setTotalBlocks(5);
                    setOnShipsList(true);
                    break;
                case (2):
                    setBackground(Color.LIGHT_GRAY);
                    setIcon(new ImageIcon("graphics/battleship.gif"));
                    setTotalBlocks(4);
                    setOnShipsList(true);
                    break;
                case (3):
                    setBackground(Color.LIGHT_GRAY);
                    setIcon(new ImageIcon("graphics/submarine.gif"));
                    setTotalBlocks(3);
                    setOnShipsList(true);
                    break;
                case (4):
                    setBackground(Color.LIGHT_GRAY);
                    setIcon(new ImageIcon("graphics/destroyer.gif"));
                    setTotalBlocks(2);
                    setOnShipsList(true);
                    break;
                case (5):
                    setBackground(Color.LIGHT_GRAY);
                    setIcon(new ImageIcon("graphics/patrolShip.gif"));
                    setTotalBlocks(1);
                    setOnShipsList(true);
                    break;
            }
        } else {
            switch (index) {
                case (0):
                    setBackground(Color.red);
                    setText("ENEMY FLEET");
                    break;
                case (1):
                    setBackground(Color.DARK_GRAY);
                    setIcon(new ImageIcon("graphics/aircraftCarrier.gif"));
                    break;
                case (2):
                    setBackground(Color.DARK_GRAY);
                    setIcon(new ImageIcon("graphics/battleship.gif"));
                    break;
                case (3):
                    setBackground(Color.DARK_GRAY);
                    setIcon(new ImageIcon("graphics/submarine.gif"));
                    break;
                case (4):
                    setBackground(Color.DARK_GRAY);
                    setIcon(new ImageIcon("graphics/destroyer.gif"));
                    break;
                case (5):
                    setBackground(Color.DARK_GRAY);
                    setIcon(new ImageIcon("graphics/patrolShip.gif"));
                    break;
            }
        }
    }

    @Override
    public void stateChanged(Object obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
