package gr.epp.thesis.api;

import javax.swing.JButton;

public abstract class GenerickBlock extends JButton implements ViewItem {

    protected int index;
    protected boolean ownership;

    public GenerickBlock() {
        initializeGridBlocks();
    }

    public GenerickBlock(int index, boolean ownership) {
        this.index = index;
        this.ownership = ownership;
        initializeShipList();
    }

    public abstract void initializeGridBlocks();

    public abstract void initializeShipList();

    public int getIndex() {
        return index;
    }

    public void setIndex(int shipIndex) {
        this.index = index;
    }

    public boolean isOwnership() {
        return ownership;
    }

    public void setOwnership(boolean ownership) {
        this.ownership = ownership;
    }
}
