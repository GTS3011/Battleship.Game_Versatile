package gr.epp.thesis;

import gr.epp.thesis.api.GenericBlock;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

/**
 *
 * @author vigos.ioannis
 */
public class AdultBlock extends GenericBlock {

    public AdultBlock() {
        super();
    }

    public AdultBlock(int index, boolean ownShipsList) {
        super(index, ownShipsList);

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
        if (this.ownShipsList) {
            this.border = new LineBorder(Color.GREEN.darker(), 1, false);
            setBorder(border);
            switch (this.index) {
                case (0):
                    setBackground(Color.LIGHT_GRAY);
                    setIcon(new ImageIcon("graphics/aircraftCarrier.gif"));
                    setTotalBlocks(5);
                    setOnShipsList(true);
                    break;
                case (1):
                    setBackground(Color.LIGHT_GRAY);
                    setIcon(new ImageIcon("graphics/battleship.gif"));
                    setTotalBlocks(4);
                    setOnShipsList(true);
                    break;
                case (2):
                    setBackground(Color.LIGHT_GRAY);
                    setIcon(new ImageIcon("graphics/submarine.gif"));
                    setTotalBlocks(3);
                    setOnShipsList(true);
                    break;
                case (3):
                    setBackground(Color.LIGHT_GRAY);
                    setIcon(new ImageIcon("graphics/destroyer.gif"));
                    setTotalBlocks(2);
                    setOnShipsList(true);
                    break;
                case (4):
                    setBackground(Color.LIGHT_GRAY);
                    setIcon(new ImageIcon("graphics/patrolShip.gif"));
                    setTotalBlocks(1);
                    setOnShipsList(true);
                    break;
            }
        } else {
            this.border = new LineBorder(Color.RED.darker(), 1, false);
            setBorder(border);
            switch (index) {
                case (0):
                    setBackground(Color.DARK_GRAY);
                    setIcon(new ImageIcon("graphics/aircraftCarrier.gif"));
                    break;
                case (1):
                    setBackground(Color.DARK_GRAY);
                    setIcon(new ImageIcon("graphics/battleship.gif"));
                    break;
                case (2):
                    setBackground(Color.DARK_GRAY);
                    setIcon(new ImageIcon("graphics/submarine.gif"));
                    break;
                case (3):
                    setBackground(Color.DARK_GRAY);
                    setIcon(new ImageIcon("graphics/destroyer.gif"));
                    break;
                case (4):
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
