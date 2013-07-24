package gr.epp.thesis;

import gr.epp.thesis.api.GenericLabel;
import javax.swing.ImageIcon;


/**
 *
 * @author vigos.ioannis
 */
public class AdultLabel extends GenericLabel {

    @Override
    public void setLabel() {
        decorImage = new ImageIcon("graphics/bismarck.png");
        setHorizontalAlignment(CENTER);
        setIcon(decorImage);
    }
}
