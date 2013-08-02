package gr.epp.thesis;

import gr.epp.thesis.api.GenericBlock;
import java.awt.BorderLayout;
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

    /*
     * Grid's seaBlocks:
     */
    @Override
    public void initializeGridBlocks() {
        setSeaColor(Color.CYAN);
    }

    /*
     * Enemy's and My Ship List.
     */
    @Override
    public void initializeShipList() {

        if (this.ownShipsList) {
            setIcon(new ImageIcon("graphics/childMyListShip.png"));
            this.border = new LineBorder(Color.GREEN, 1, false);
            setBackground(Color.LIGHT_GRAY);
            setTotalBlocks(1);
            setOnShipsList(true);
        } else {
            setIcon(new ImageIcon("graphics/childEnemyListShip.png"));
            this.border = new LineBorder(Color.RED, 1, false);
            setBackground(Color.DARK_GRAY);
        }
    }

    //To change body of generated methods, choose Tools | Templates.
    @Override
    public void stateChanged(Object obj) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
