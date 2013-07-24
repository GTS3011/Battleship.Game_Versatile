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

    public ChildBlock(int shipIndex, boolean ownership) {
        super(shipIndex, ownership);
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
    public void initializeShipBlocks() {
        if (this.ownership) {
            switch (this.shipIndex) {
                case (0):
                    setBackground(Color.YELLOW);
                    setText("MY FLEET");
                    break;
                case (1):
                    setBackground(Color.LIGHT_GRAY);
                    setIcon(new ImageIcon("graphics/fire.gif"));
                    break;
                case (2):
                    setBackground(Color.LIGHT_GRAY);
                    setIcon(new ImageIcon("graphics/fire.gif"));
                    break;
                case (3):
                    setBackground(Color.LIGHT_GRAY);
                    setIcon(new ImageIcon("graphics/fire.gif"));
                    break;
                case (4):
                    setBackground(Color.LIGHT_GRAY);
                    setIcon(new ImageIcon("graphics/fire.gif"));
                    break;
                case (5):
                    setBackground(Color.LIGHT_GRAY);
                    setIcon(new ImageIcon("graphics/fire.gif"));
                    break;
            }
        } else {
            switch (this.shipIndex) {
                case (0):
                    setBackground(Color.ORANGE);
                    setText("ENEMY FLEET");
                    break;
                case (1):
                    setBackground(Color.DARK_GRAY);
                    setIcon(new ImageIcon("graphics/fire.gif"));
                    break;
                case (2):
                    setBackground(Color.DARK_GRAY);
                    setIcon(new ImageIcon("graphics/fire.gif"));
                    break;
                case (3):
                    setBackground(Color.DARK_GRAY);
                    setIcon(new ImageIcon("graphics/fire.gif"));
                    break;
                case (4):
                    setBackground(Color.DARK_GRAY);
                    setIcon(new ImageIcon("graphics/fire.gif"));
                    break;
                case (5):
                    setBackground(Color.DARK_GRAY);
                    setIcon(new ImageIcon("graphics/fire.gif"));
                    break;
            }
        }
    }
}
