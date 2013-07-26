package gr.epp.thesis;

import gr.epp.thesis.api.GenericPanel;
import java.awt.GridLayout;

/**
 *
 * @author vigos.ioannis
 */
public class ChildShipList extends GenericPanel {

    public ChildShipList() {
    }

    @Override
    public void initializeList() {
        setLayout(new GridLayout(5, 2));
    }

    @Override
    public int totalItems() {
        return 10;
    }

    @Override
    public void stateChanged(Object obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}