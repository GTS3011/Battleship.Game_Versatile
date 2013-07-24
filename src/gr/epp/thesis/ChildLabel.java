package gr.epp.thesis;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author vigos.ioannis
 */
public class ChildLabel extends JLabel implements ViewItem {

    ImageIcon decorImage = new ImageIcon("graphics/pirateShip.png");

    public ChildLabel() {
        setHorizontalAlignment(CENTER);
        setIcon(decorImage);
    }

    @Override
    public void itemIcon() {
    }

    @Override
    public boolean isWarship() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String checker() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
