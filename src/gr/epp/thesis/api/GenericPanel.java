package gr.epp.thesis.api;

import javax.swing.JPanel;

/**
 *
 * @author vigos.ioannis
 */
public abstract class GenericPanel extends JPanel implements ViewItem {

    protected int rows;
    protected int columns;
    protected String playerType;

    public GenericPanel() {       
        initializeList();
    }

    public abstract void initializeList();

    public abstract int totalItems();
}
