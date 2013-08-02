package gr.epp.thesis.api;

import gr.epp.thesis.mvc.View;
import javax.swing.JPanel;

/**
 *
 * @author vigos.ioannis
 */
public abstract class GenericPanel extends JPanel implements View {

    public GenericPanel() {
        initializeList();
    }

    public abstract void initializeList();

    public abstract int getTotalItems();
}
