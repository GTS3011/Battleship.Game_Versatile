package gr.epp.thesis;

import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author vigos.ioannis
 */
public class AdultBlock extends JButton implements ViewItem {

    public AdultBlock() {
        setBackground(Color.BLUE);
    }

    @Override
    public void itemIcon() {
        setIcon(new ImageIcon("graphics/miss.gif"));
    }

    @Override
    public boolean isWarship() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String checker() {
        return "Megistooos!";
    }
}
