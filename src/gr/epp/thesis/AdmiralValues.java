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
public class AdmiralValues extends GenericValues {

    /**
     * Values defined for the Adult Player.
     */
    public AdmiralValues() {
        this.rows = 15;
        this.columns = 15;
        this.frameWidth = 525;
        this.frameHeight = 1050;
        this.totalShips = 10;
        this.seaColor = Color.CYAN.darker();
        this.myShipsListBackColor = Color.LIGHT_GRAY.darker();
        this.enemyShipsListBackColor = Color.DARK_GRAY.darker();
        this.myShipListBorder = new LineBorder(Color.GREEN.darker(), 1, false);
        this.enemyShipListBorder = new LineBorder(Color.RED.darker(), 1, false);
        this.water = new ImageIcon("graphics/water.gif");
        this.decor = new ImageIcon("graphics/admiralDecorLabel.png");
        this.playerBanner = new ImageIcon("graphics/myFleetAdmiralIcon.png");
        this.enemyPlayerBanner = new ImageIcon("graphics/enemyFleetAdmiralIcon.png");
        this.toolkit = Toolkit.getDefaultToolkit();
        this.target = toolkit.getImage("graphics/sword.gif");
        this.hit = new ImageIcon("graphics/fire.gif");
        this.miss = new ImageIcon("graphics/miss.gif");
        this.myWarships = new ArrayList<>();
        for (int i = 0; i < totalShips; i++) {
            this.myWarships.add(new ImageIcon("graphics/modernWarship" + i + ".gif"));
        }
        this.enemyWarships = new ArrayList<>();
        this.enemyWarships = myWarships;
        this.listItems = 11;
        this.listRows = 11;
        this.listColumns = 1;
    }

    @Override
    public int getTotalGridBlocks() {
        return this.rows * this.columns;
    }
}