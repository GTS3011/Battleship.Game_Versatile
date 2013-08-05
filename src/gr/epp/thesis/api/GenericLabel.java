package gr.epp.thesis.api;

import gr.epp.thesis.mvc.View;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.border.Border;

public abstract class GenericLabel extends JLabel implements View {

    protected GenericValues values;
    protected ImageIcon playerLabel;
    protected boolean player;
    protected Border border;

    /**
     * Constructor for the decor panel of a specific player.
     */
    public GenericLabel() {
        initializeValues();             // All values needed for a specific player.
        setIcon(values.getDecor());
        setHorizontalAlignment(CENTER);
    }

    /**
     * Constructor for the player labels above ships list of a specific player.
     */
    public GenericLabel(boolean player) {
        initializeValues();             // All values needed for a specific player.
        this.player = player;
        initializeShipsListLabel();
        setBorder(border);
        setHorizontalAlignment(CENTER);
        setIcon(playerLabel);
    }

    public abstract void initializeValues();

    public abstract void initializeShipsListLabel();
}
