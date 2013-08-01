package gr.epp.thesis;

import gr.epp.thesis.api.GenerickBlock;
import java.awt.Color;
import javax.swing.ImageIcon;

/**
 *
 * @author USER1
 */
public class AdmiralBlock extends GenerickBlock {

    public AdmiralBlock() {
    }

    public AdmiralBlock(int index, boolean ownership) {
        super(index, ownership);
    }

    @Override
    public void initializeGridBlocks() {
        setBackground(Color.CYAN.darker());
    }

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
                    setIcon(new ImageIcon("graphics/battleship.gif"));
                    setTotalBlocks(4);
                    setOnShipsList(true);
                    break;
                case (4):
                    setBackground(Color.LIGHT_GRAY);
                    setIcon(new ImageIcon("graphics/submarine.gif"));
                    setTotalBlocks(3);
                    setOnShipsList(true);
                    break;
                case (5):
                    setBackground(Color.LIGHT_GRAY);
                    setIcon(new ImageIcon("graphics/submarine.gif"));
                    setTotalBlocks(3);
                    setOnShipsList(true);
                    break;
                case (6):
                    setBackground(Color.LIGHT_GRAY);
                    setIcon(new ImageIcon("graphics/submarine.gif"));
                    setTotalBlocks(3);
                    setOnShipsList(true);
                    break;
                case (7):
                    setBackground(Color.LIGHT_GRAY);
                    setIcon(new ImageIcon("graphics/destroyer.gif"));
                    setTotalBlocks(2);
                    setOnShipsList(true);
                    break;
                case (8):
                    setBackground(Color.LIGHT_GRAY);
                    setIcon(new ImageIcon("graphics/destroyer.gif"));
                    setTotalBlocks(2);
                    setOnShipsList(true);
                    break;
                case (9):
                    setBackground(Color.LIGHT_GRAY);
                    setIcon(new ImageIcon("graphics/patrolShip.gif"));
                    setTotalBlocks(1);
                    setOnShipsList(true);
                    break;
                case (10):
                    setBackground(Color.LIGHT_GRAY);
                    setIcon(new ImageIcon("graphics/patrolShip.gif"));
                    setTotalBlocks(1);
                    setOnShipsList(true);
                    break;
                case (11):
                    setBackground(Color.LIGHT_GRAY);
                    setIcon(new ImageIcon("graphics/patrolShip.gif"));
                    setTotalBlocks(1);
                    setOnShipsList(true);
                    break;
                case (12):
                    setBackground(Color.LIGHT_GRAY);
                    setIcon(new ImageIcon("graphics/patrolShip.gif"));
                    setTotalBlocks(1);
                    setOnShipsList(true);
                    break;
            }
        } else {
            switch (index) {
                case (0):
                    setBackground(Color.GREEN);
                    setText("MY FLEET");
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
                    setIcon(new ImageIcon("graphics/battleship.gif"));
                    break;
                case (4):
                    setBackground(Color.DARK_GRAY);
                    setIcon(new ImageIcon("graphics/submarine.gif"));
                    break;
                case (5):
                    setBackground(Color.DARK_GRAY);
                    setIcon(new ImageIcon("graphics/submarine.gif"));
                    break;
                case (6):
                    setBackground(Color.DARK_GRAY);
                    setIcon(new ImageIcon("graphics/submarine.gif"));
                    break;
                case (7):
                    setBackground(Color.DARK_GRAY);
                    setIcon(new ImageIcon("graphics/destroyer.gif"));
                    break;
                case (8):
                    setBackground(Color.DARK_GRAY);
                    setIcon(new ImageIcon("graphics/destroyer.gif"));
                    break;
                case (9):
                    setBackground(Color.DARK_GRAY);
                    setIcon(new ImageIcon("graphics/patrolShip.gif"));
                    break;
                case (10):
                    setBackground(Color.DARK_GRAY);
                    setIcon(new ImageIcon("graphics/patrolShip.gif"));
                    break;
                case (11):
                    setBackground(Color.DARK_GRAY);
                    setIcon(new ImageIcon("graphics/patrolShip.gif"));
                    break;
                case (12):
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
