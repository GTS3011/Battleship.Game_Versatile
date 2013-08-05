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

    @Override
    public void initializeValues() {
        this.values = new AdultValues();
    }

    /*
     * Grid's seaBlocks:
     */
    @Override
    public void initializeGridBlocks() {
    }

    /*
     * Enemy's and My Ship List.
     */
    @Override
    public void initializeShipsList() {
        if (this.ownShipsList) {
            this.border = new LineBorder(Color.GREEN.darker(), 1, false);
            setBackground(values.getMyShipsListBackColor());
            switch (this.index) {
                case (0):
                    ImageIcon img = values.getMyWarshipsIcons(index);
                    setIcon(new ImageIcon("graphics/modernWarship0.gif"));
                    //System.out.println("" + values.getMyWarshipsIcons().length);
                    setTotalBlocks(5);
                    setOnShipsList(true);
                    break;
                case (1):
                    setIcon(new ImageIcon("graphics/battleship.gif"));
                    setTotalBlocks(4);
                    setOnShipsList(true);
                    break;
                case (2):
                    setIcon(new ImageIcon("graphics/submarine.gif"));
                    setTotalBlocks(3);
                    setOnShipsList(true);
                    break;
                case (3):
                    setIcon(new ImageIcon("graphics/destroyer.gif"));
                    setTotalBlocks(2);
                    setOnShipsList(true);
                    break;
                case (4):
                    setIcon(new ImageIcon("graphics/patrolShip.gif"));
                    setTotalBlocks(1);
                    setOnShipsList(true);
                    break;
            }
        } else {
            this.border = new LineBorder(Color.RED.darker(), 1, false);
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

    //To change body of generated methods, choose Tools | Templates.
    @Override
    public void stateChanged(Object obj) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
