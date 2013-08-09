package gr.epp.thesis;

import gr.epp.thesis.api.GenericValues;
import java.awt.Color;
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
public class ChildValues extends GenericValues {

    /**
     * Values defined for the Adult Player.
     */
    public ChildValues() {
        this.rows = 10;
        this.columns = 10;
        this.frameWidth = 450;
        this.frameHeight = 900;
        this.totalWarships = 9;
        this.seaColor = Color.CYAN;
        this.myShipsListBackColor = Color.LIGHT_GRAY;
        this.enemyShipsListBackColor = Color.DARK_GRAY;
        this.myShipsListBorder = new LineBorder(Color.GREEN, 1, false);
        this.enemyShipsListBorder = new LineBorder(Color.RED, 1, false);
        this.water = new ImageIcon("graphics/water.gif");
        this.decor = new ImageIcon("graphics/childDecorLabel.png");
        this.playerBanner = new ImageIcon("graphics/childMyListMotherShip.png");
        this.enemyPlayerBanner = new ImageIcon("graphics/childEnemyListMotherShip.png");
        this.toolkit = Toolkit.getDefaultToolkit();
        this.target = toolkit.getImage("graphics/target.gif");
        this.hit = new ImageIcon("graphics/fire.gif");
        this.miss = new ImageIcon("graphics/miss.gif");
        this.myWarships = new ArrayList<>();
        this.myWarships.add(new ImageIcon("graphics/childMyListShip.png"));
        this.enemyWarships = new ArrayList<>();
        this.enemyWarships.add(new ImageIcon("graphics/childEnemyListShip.png"));
        this.maxWarshipsBlocks = 9;
        this.listItems = 10;
        this.listRows = 5;
        this.listColumns = 2;
    }

    @Override
    public ImageIcon getGridPieces(int shipBlocks, int currentBlock, int orientation, boolean sinked) {
        if (sinked) {
            return (new ImageIcon("graphics/gridPieces/childMySinked.png"));
        } else {
            return (new ImageIcon("graphics/gridPieces/childGridShip.png"));
        }
    }

    @Override
    public int getTotalGridBlocks() {
        return this.rows * this.columns;
    }
}