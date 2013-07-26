package gr.epp.thesis;

import gr.epp.thesis.api.GenericLabel;
import javax.swing.ImageIcon;

/**
 *
 * @author vigos.ioannis
 */
public class ChildLabel extends GenericLabel {

    @Override
    public void setLabel() {
        decorImage = new ImageIcon("graphics/pirateShip.png");
        setHorizontalAlignment(CENTER);
        setIcon(decorImage);
    }

    @Override
    public void stateChanged(Object obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
