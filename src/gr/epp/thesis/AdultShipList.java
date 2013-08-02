package gr.epp.thesis;

import gr.epp.thesis.api.GenericPanel;
import java.awt.GridLayout;

/**
 *
 * @author vigos.ioannis
 */
public class AdultShipList extends GenericPanel {

    public AdultShipList() {
    }

    @Override
    public void initializeList() {
        setLayout(new GridLayout(6, 1));
    }

    @Override
    public int getTotalItems() {
        return 6;
    }

    //To change body of generated methods, choose Tools | Templates.
    @Override
    public void stateChanged(Object obj) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}