package gr.epp.thesis.api;

import gr.epp.thesis.mvc.View;
import java.awt.Color;
import javax.swing.JButton;

public abstract class GenericBlock extends JButton implements View {

    protected int index;
    protected boolean ownership;
    protected int temp;
    protected int totalBlocks;
    protected boolean warship = false;
    protected boolean onShipsList = false;
    protected Color seaColor = null;

    public GenericBlock() {
        initializeGridBlocks();
    }

    public GenericBlock(int index, boolean ownership) {
        this.index = index;
        this.ownership = ownership;
        initializeShipList();
    }

    public abstract void initializeGridBlocks();

    public abstract void initializeShipList();

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

    public boolean isOwnership() {
        return ownership;
    }

    public void setOwnership(boolean ownership) {
        this.ownership = ownership;
    }
}
