package gr.epp.thesis.api;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;

/**
 * @author tsoutsas.yiorgos & vigkos.ioannis
 * @project Thesis_Battleship.Game
 * @author Vidakis.Nikolas & Vellis Giorgos
 * @since Spring Semester 2013
 * @institute Applied Informatics and Multimedia - TEI of Crete *
 */
public abstract class GenericValues {

    /**
     * Values defined for a Player.
     */
    protected int rows;
    protected int columns;
    protected int frameWidth;
    protected int frameHeight;
    protected int totalGridBlocks;
    protected int totalShips;
    protected Color seaColor;
    protected Color myShipsListBackColor;
    protected Color enemyShipsListBackColor;
    protected LineBorder myShipListBorder;
    protected LineBorder enemyShipListBorder;
    protected ImageIcon water;
    protected ImageIcon decor;
    protected ImageIcon playerBanner;
    protected ImageIcon enemyPlayerBanner;
    protected Toolkit toolkit;
    protected Image target;
    protected ImageIcon hit;
    protected ImageIcon miss;
    protected ArrayList<ImageIcon> myWarships;
    protected ArrayList<ImageIcon> enemyWarships;
    protected int maxWarshipsBlocks;
    protected int listItems;
    protected int listRows;
    protected int listColumns;

    /**
     * Method needed for the placement of warships on the grid. Every
     * orientation.
     *
     * @param shipBlocks
     * @param currentBlock
     * @param orientation
     * @return
     */
    public ImageIcon getGridPieces(int shipBlocks, int currentBlock, int orientation) {
        return (new ImageIcon("graphics/gridPieces/" + shipBlocks + "_" + currentBlock + "_" + orientation + ".gif"));
    }

    //Getters & Setters: 
    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public int getFrameWidth() {
        return frameWidth;
    }

    public void setFrameWidth(int frameWidth) {
        this.frameWidth = frameWidth;
    }

    public int getFrameHeight() {
        return frameHeight;
    }

    public void setFrameHeight(int frameHeight) {
        this.frameHeight = frameHeight;
    }

    public int getTotalGridBlocks() {
        return totalGridBlocks;
    }

    public void setTotalGridBlocks(int totalGridBlocks) {
        this.totalGridBlocks = totalGridBlocks;
    }

    public int getTotalShips() {
        return totalShips;
    }

    public void setTotalShips(int totalShips) {
        this.totalShips = totalShips;
    }

    public Color getSeaColor() {
        return seaColor;
    }

    public void setSeaColor(Color seaColor) {
        this.seaColor = seaColor;
    }

    public Color getMyShipsListBackColor() {
        return myShipsListBackColor;
    }

    public void setMyShipsListBackColor(Color myShipsListBackColor) {
        this.myShipsListBackColor = myShipsListBackColor;
    }

    public Color getEnemyShipsListBackColor() {
        return enemyShipsListBackColor;
    }

    public void setEnemyShipsListBackColor(Color enemyShipsListBackColor) {
        this.enemyShipsListBackColor = enemyShipsListBackColor;
    }

    public LineBorder getMyShipListBorder() {
        return myShipListBorder;
    }

    public void setMyShipListBorder(LineBorder myShipListBorder) {
        this.myShipListBorder = myShipListBorder;
    }

    public LineBorder getEnemyShipListBorder() {
        return enemyShipListBorder;
    }

    public void setEnemyShipListBorder(LineBorder enemyShipListBorder) {
        this.enemyShipListBorder = enemyShipListBorder;
    }

    public ImageIcon getWater() {
        return water;
    }

    public void setWater(ImageIcon water) {
        this.water = water;
    }

    public ImageIcon getDecor() {
        return decor;
    }

    public void setDecor(ImageIcon decor) {
        this.decor = decor;
    }

    public ImageIcon getPlayerBanner() {
        return playerBanner;
    }

    public void setPlayerBanner(ImageIcon playerBanner) {
        this.playerBanner = playerBanner;
    }

    public ImageIcon getEnemyPlayerBanner() {
        return enemyPlayerBanner;
    }

    public void setEnemyPlayerBanner(ImageIcon enemyPlayerBanner) {
        this.enemyPlayerBanner = enemyPlayerBanner;
    }

    public Toolkit getToolkit() {
        return toolkit;
    }

    public void setToolkit(Toolkit toolkit) {
        this.toolkit = toolkit;
    }

    public Image getTarget() {
        return target;
    }

    public void setTarget(Image target) {
        this.target = target;
    }

    public ImageIcon getHit() {
        return hit;
    }

    public void setHit(ImageIcon hit) {
        this.hit = hit;
    }

    public ImageIcon getMiss() {
        return miss;
    }

    public void setMiss(ImageIcon miss) {
        this.miss = miss;
    }

    public ArrayList<ImageIcon> getMyWarships() {
        return myWarships;
    }

    public void setMyWarships(ArrayList<ImageIcon> myWarships) {
        this.myWarships = myWarships;
    }

    public ArrayList<ImageIcon> getEnemyWarships() {
        return enemyWarships;
    }

    public void setEnemyWarships(ArrayList<ImageIcon> enemyWarships) {
        this.enemyWarships = enemyWarships;
    }

    public int getMaxWarshipsBlocks() {
        return maxWarshipsBlocks;
    }

    public void setMaxWarshipsBlocks(int maxWarshipsBlocks) {
        this.maxWarshipsBlocks = maxWarshipsBlocks;
    }

    public int getListItems() {
        return listItems;
    }

    public void setListItems(int listItems) {
        this.listItems = listItems;
    }

    public int getListRows() {
        return listRows;
    }

    public void setListRows(int listRows) {
        this.listRows = listRows;
    }

    public int getListColumns() {
        return listColumns;
    }

    public void setListColumns(int listColumns) {
        this.listColumns = listColumns;
    }
}
