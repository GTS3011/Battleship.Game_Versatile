package gr.epp.thesis;

import gr.epp.thesis.api.GenericPanel;
import java.awt.GridLayout;

/**
 *
 * @author USER1
 */
public class AdmiralShipList extends GenericPanel {

    public AdmiralShipList() {
    }

    @Override
    public void initializeList() {
        setLayout(new GridLayout(11, 1));
    }

    @Override
    public int getTotalItems() {
        return 11;
    }

    //To change body of generated methods, choose Tools | Templates.
    @Override
    public void stateChanged(Object obj) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
