package gr.epp.thesis;

import gr.epp.thesis.api.GenericBlock;
import java.awt.Color;
import javax.swing.ImageIcon;

/**
 *
 * @author vigos.ioannis
 */
public class ChildBlock extends GenericBlock {

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
        setSeaColor(Color.CYAN);
        setBackground(getSeaColor());
    }

    /*
     * Enemy's and My Ship List.
     */
    @Override
    public void initializeShipList() {
        if (this.ownership) {
            setBackground(Color.LIGHT_GRAY);
            setTotalBlocks(1);
            setOnShipsList(true);
        } else {
            setBackground(Color.DARK_GRAY);
        }
        setIcon(new ImageIcon("graphics/childListShip.gif"));
    }

    @Override
    public void stateChanged(Object obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
