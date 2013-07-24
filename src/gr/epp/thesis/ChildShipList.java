/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
}
