package gr.epp.thesis.api;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.border.Border;

/**
 *
 * @author vigos.ioannis
 */
public abstract class GenericValues {

    protected int rows;
    protected int columns;
    protected int frameWidth;
    protected int frameHeight;
    protected int totalGridItems;
    protected int totalShips;
    protected Color seaColor;
    protected Color myShipsListBackColor;
    protected Color enemyShipsListBackColor;
    protected Border border;
    protected ImageIcon water;
    protected ImageIcon decor;
    protected ImageIcon playerBanner;
    protected ImageIcon enemyPlayerBanner;
    protected Toolkit toolkit;
    protected Image target;
    protected ImageIcon hit;
    protected ImageIcon miss;
    protected ArrayList<ImageIcon> myWarshipsIcons = new ArrayList<>();
    protected ArrayList<ImageIcon> enemyWarshipsIcons = new ArrayList<>();

    public ImageIcon getGridPieces(int shipBlocks, int currentBlock, int orientation) {
        return (new ImageIcon("graphics/gridPieces/" + shipBlocks + "_" + currentBlock + "_" + orientation + ".gif"));
    }

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

    public int getTotalGridItems() {
        return this.rows * this.columns;
    }

    public void setTotalGridItems(int totalGridItems) {
        this.totalGridItems = totalGridItems;
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

    public Border getBorder() {
        return border;
    }

    public void setBorder(Border border) {
        this.border = border;
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

    public ArrayList<ImageIcon> getMyWarshipsIcons() {
        return myWarshipsIcons;
    }

    public void setMyWarshipsIcons(ArrayList<ImageIcon> myWarshipsIcons) {
        this.myWarshipsIcons = myWarshipsIcons;
    }

    public ArrayList<ImageIcon> getEnemyWarshipsIcons() {
        return enemyWarshipsIcons;
    }

    public void setEnemyWarshipsIcons(ArrayList<ImageIcon> enemyWarshipsIcons) {
        this.enemyWarshipsIcons = enemyWarshipsIcons;
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
}
