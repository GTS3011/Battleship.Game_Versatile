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
        setLayout(new GridLayout(12, 1));
    }

    @Override
    public int totalItems() {
        return 12;
    }

    @Override
    public void stateChanged(Object obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
