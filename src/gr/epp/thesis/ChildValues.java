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
        this.totalShips = 9;
        this.seaColor = Color.CYAN;
        this.myShipsListBackColor = Color.LIGHT_GRAY;
        this.enemyShipsListBackColor = Color.DARK_GRAY;
        this.myShipListBorder = new LineBorder(Color.GREEN, 1, false);
        this.enemyShipListBorder = new LineBorder(Color.RED, 1, false);
        this.water = new ImageIcon("graphics/water.gif");
        this.decor = new ImageIcon("graphics/childDecorLabel.png");
        this.playerBanner = new ImageIcon("graphics/childMyListMotherShip.png");
        this.enemyPlayerBanner = new ImageIcon("graphics/childEnemyListMotherShip.png");
        this.toolkit = Toolkit.getDefaultToolkit();
        this.target = toolkit.getImage("graphics/target.gif");
        this.hit = new ImageIcon("graphics/fire.gif");
        this.miss = new ImageIcon("graphics/miss.gif");
        this.myWarshipsIcons = new ArrayList<>();
        this.myWarshipsIcons.add(new ImageIcon("graphics/childMyListShip.png"));
        this.enemyWarshipsIcons = new ArrayList<>();
        this.enemyWarshipsIcons.add(new ImageIcon("graphics/childEnemyListShip.png"));
        this.totalShipsListItems = 10;
        this.shipsListRows = 5;
        this.shipsListColumns = 2;
    }
}