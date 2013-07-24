package gr.epp.thesis.api;

import javax.swing.JButton;


public abstract class GenerickBlock extends JButton implements ViewItem {

    protected int shipIndex;
    protected boolean ownership;

    public GenerickBlock() {
        initializeGridBlocks();

    }

    public GenerickBlock(int shipIndex, boolean ownership) {
        this.shipIndex = shipIndex;
        this.ownership = ownership;
        initializeShipBlocks();
    }

    public abstract void initializeGridBlocks();

    public abstract void initializeShipBlocks();

    public int getShipIndex() {
        return shipIndex;
    }

    public void setShipIndex(int shipIndex) {
        this.shipIndex = shipIndex;
    }

    public boolean isOwnership() {
        return ownership;
    }

    public void setOwnership(boolean ownership) {
        this.ownership = ownership;
    }
}
