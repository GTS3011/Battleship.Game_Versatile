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
    public int totalItems() {
        return 6;
    }

    @Override
    public void stateChanged(Object obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}