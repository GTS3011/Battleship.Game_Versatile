package gr.epp.thesis.api;

import gr.epp.thesis.GameControl;
import javax.swing.JButton;

public abstract class GenerickBlock extends JButton implements ViewItem {

    protected int index;
    protected boolean ownership;
    protected int temp;

    public GenerickBlock() {
        addMouseListener(new GameControl());
        initializeGridBlocks();
    }

    public GenerickBlock(int index, boolean ownership) {
        addMouseListener(new GameControl());
        this.index = index;
        this.ownership = ownership;
        initializeShipList();
    }

    public abstract void initializeGridBlocks();

    public abstract void initializeShipList();

    public void setTotalBlocks(int temp) {
        this.temp = temp;
    }

    public int getTotalBlocks() {
        return temp;
    }

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
