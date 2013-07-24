package gr.epp.thesis;

import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author vigos.ioannis
 */
public class ChildBlock extends JButton implements ViewItem {

    public ChildBlock() {
        setBackground(Color.CYAN);
    }

    @Override
    public void itemIcon() {
        setIcon(new ImageIcon("graphics/airCraftCarrierChild.gif"));
    }

    @Override
    public void setWarship(boolean warship) {
        if (warship) {
            setBackground(Color.red);
        }
    }

    @Override
    public String checker() {
        return "paidaros!";
    }
}
