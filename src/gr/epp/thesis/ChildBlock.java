package gr.epp.thesis;

import gr.epp.thesis.api.GenerickBlock;
import java.awt.Color;
import javax.swing.ImageIcon;

/**
 *
 * @author vigos.ioannis
 */
public class ChildBlock extends GenerickBlock {

    public ChildBlock() {
        super();
    }

    public ChildBlock(int index, boolean ownership) {
        super(index, ownership);
    }

    /*
     * Grid's seaBlocks:
     */
    @Override
    public void initializeGridBlocks() {
        setBackground(Color.CYAN);
    }

    /*
     * My Ship's List:
     */
    @Override
    public void initializeShipList() {
        if (this.ownership) {
            if (this.index > -1) {
                setBackground(Color.LIGHT_GRAY);
                setIcon(new ImageIcon("graphics/childGridShip.gif"));
            }
        } else {
            if (this.index > -1) {
                setBackground(Color.DARK_GRAY);
                setIcon(new ImageIcon("graphics/childGridShip.gif"));
            }
        }
    }
}
