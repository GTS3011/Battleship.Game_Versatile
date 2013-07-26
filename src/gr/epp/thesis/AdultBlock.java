package gr.epp.thesis;

import gr.epp.thesis.api.GenerickBlock;
import java.awt.Color;
import javax.swing.ImageIcon;

/**
 *
 * @author vigos.ioannis
 */
public class AdultBlock extends GenerickBlock {

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
        setBackground(Color.BLUE);
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
                    break;
                case (2):
                    setBackground(Color.LIGHT_GRAY);
                    setIcon(new ImageIcon("graphics/battleship.gif"));
                    setTotalBlocks(4);
                    break;
                case (3):
                    setBackground(Color.LIGHT_GRAY);
                    setIcon(new ImageIcon("graphics/submarine.gif"));
                    setTotalBlocks(3);
                    break;
                case (4):
                    setBackground(Color.LIGHT_GRAY);
                    setIcon(new ImageIcon("graphics/destroyer.gif"));
                    setTotalBlocks(2);
                    break;
                case (5):
                    setBackground(Color.LIGHT_GRAY);
                    setIcon(new ImageIcon("graphics/patrolShip.gif"));
                    setTotalBlocks(1);
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
        System.out.println("DONE!");
    }    
}
