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
public class AdultValues extends GenericValues {

    /**
     * Values defined for the Adult Player.
     */
    public AdultValues() {
        this.rows = 10;
        this.columns = 10;
        this.frameWidth = 450;
        this.frameHeight = 900;
        this.totalShips = 5;
        this.seaColor = Color.CYAN;
        this.myShipsListBackColor = Color.LIGHT_GRAY;
        this.enemyShipsListBackColor = Color.DARK_GRAY;
        this.myShipListBorder = new LineBorder(Color.GREEN.darker(), 1, false);
        this.enemyShipListBorder = new LineBorder(Color.RED.darker(), 1, false);
        this.water = new ImageIcon("graphics/water.gif");
        this.decor = new ImageIcon("graphics/adultDecorLabel.png");
        this.playerBanner = new ImageIcon("graphics/myFleetAdultIcon.png");
        this.enemyPlayerBanner = new ImageIcon("graphics/enemyFleetAdultIcon.png");
        this.toolkit = Toolkit.getDefaultToolkit();
        this.target = toolkit.getImage("graphics/target.gif");
        this.hit = new ImageIcon("graphics/fire.gif");
        this.miss = new ImageIcon("graphics/miss.gif");
        this.myWarshipsIcons = new ArrayList<>();
        for (int i = 0; i < totalShips; i++) {
            this.myWarshipsIcons.add(new ImageIcon("graphics/modernWarship" + i + ".gif"));
        }
        this.enemyWarshipsIcons = new ArrayList<>();
        this.enemyWarshipsIcons = myWarshipsIcons;
        this.totalShipsListItems = 6;
        this.shipsListRows = 6;
        this.shipsListColumns = 1;
    }
}