package gr.epp.thesis.api;

import gr.epp.thesis.mvc.View;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.Border;

public abstract class GenericBlock extends JButton implements View {

    protected int index;
    protected boolean ownShipsList;
    protected int temp;
    protected int totalBlocks;
    protected boolean warship = false;
    protected boolean onShipsList = false;
    protected Color seaColor = null;
    protected Border border;
    protected ImageIcon water = new ImageIcon("graphics/water.gif");

    public GenericBlock() {
        initializeGridBlocks();
        setBackground(getSeaColor());
        setIcon(water);
    }

    public GenericBlock(int index, boolean ownShipsList) {
        this.index = index;
        this.ownShipsList = ownShipsList;
        this.border = border;
        initializeShipList();
        setBorder(border);
    }

    public abstract void initializeGridBlocks();

    public abstract void initializeShipList();

    public ImageIcon getWater() {
        return water;
    }

    public void setOnShipsList(boolean onShipsList) {
        this.onShipsList = onShipsList;
    }

    public Color getSeaColor() {
        return seaColor;
    }

    public void setSeaColor(Color seaColor) {
        this.seaColor = seaColor;
    }

    public boolean isOnShipsList() {
        return onShipsList;
    }

    public void setWarshipOn(boolean warship) {
        this.warship = warship;
    }

    public boolean isWarshipOn() {
        return warship;
    }

    public void setTotalBlocks(int totalBlocks) {
        this.totalBlocks = totalBlocks;
    }

    public int getTotalBlocks() {
        return totalBlocks;
    }
}
