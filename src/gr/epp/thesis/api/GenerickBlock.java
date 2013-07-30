package gr.epp.thesis.api;

import gr.epp.thesis.mvc.View;
import gr.epp.thesis.GameControl;
import javax.swing.JButton;

public abstract class GenerickBlock extends JButton implements View {

    protected int index;
    protected boolean ownership;
    protected int temp;
    protected int totalBlocks;
    protected boolean warship = false;
    protected boolean listShips = false;
    protected GameControl boardListener = new GameControl();

    public GenerickBlock() {
        this.boardListener = boardListener;
        addMouseListener(boardListener);
        initializeGridBlocks();
    }

    public GenerickBlock(int index, boolean ownership) {
        this.index = index;
        this.ownership = ownership;
        initializeShipList();
        this.boardListener = boardListener;
        addMouseListener(boardListener);
    }

    public abstract void initializeGridBlocks();

    public abstract void initializeShipList();

    public void setListShips(boolean listShips) {
        this.listShips = listShips;
    }

    public boolean isLisShips() {
        return listShips;
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
