package gr.epp.thesis.api;

import gr.epp.thesis.mvc.View;
import javax.swing.JPanel;

/**
 * @author tsoutsas.yiorgos & vigkos.ioannis
 * @project Thesis_Battleship.Game
 * @author Vidakis.Nikolas & Vellis Giorgos
 * @since Spring Semester 2013
 * @institute Applied Informatics and Multimedia - TEI of Crete
 */
public abstract class GenericPanel extends JPanel implements View {

    public GenericPanel() {
        initializeList();
    }

    public abstract void initializeList();

    public abstract int getTotalItems();
}
