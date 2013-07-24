package gr.epp.thesis;

import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author vigos.ioannis
 */
public class ChildBlock extends JButton implements ViewItem {

    /*
     * Grid's seaBlocks:
     */
    public ChildBlock() {
        setBackground(Color.CYAN);
    }

    /*
     * My Ship's List:
     */
    public ChildBlock(int temp, boolean own) {
        if (own) {
            switch (temp) {
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
            switch (temp) {
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
