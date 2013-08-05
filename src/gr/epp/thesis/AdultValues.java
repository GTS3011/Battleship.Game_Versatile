package gr.epp.thesis;

import gr.epp.thesis.api.GenericValues;
import java.awt.Color;
import java.awt.Toolkit;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;

/**
 *
 * @author vigos.ioannis
 */
public class AdultValues extends GenericValues {

    public AdultValues() {
        this.rows = 10;
        this.columns = 10;
        this.frameWidth = 450;
        this.frameHeight = 900;
        this.totalShips = 5;
        this.seaColor = Color.CYAN;
        this.myShipsListBackColor = Color.GREEN.darker();
        this.enemyShipsListBackColor = Color.RED.darker();
        this.myShipListBorder = new LineBorder(Color.GREEN.darker(), 1, false);
        this.myShipListBorder = new LineBorder(Color.RED.darker(), 1, false);
        this.water = new ImageIcon("graphics/water.gif");
        this.decor = new ImageIcon("graphics/adultDecorLabel.png");
        this.playerBanner = new ImageIcon("graphics/myFleetAdultIcon.png");
        this.enemyPlayerBanner = new ImageIcon("graphics/enemyFleetAdultIcon.png");
        this.toolkit = Toolkit.getDefaultToolkit();
        this.target = toolkit.getImage("graphics/target.gif");
        this.hit = new ImageIcon("graphics/fire.gif");
        this.miss = new ImageIcon("graphics/miss.gif");
        this.myWarshipsIcons = new ImageIcon[totalShips];
        for (int i = 0; i < totalShips; i++) {
            this.myWarshipsIcons[i] = new ImageIcon("graphics/modernWarship" + i + ".gif");
        }
        //this.enemyWarshipsIcons = (ArrayList<ImageIcon>) myWarshipsIcons.clone();
    }
}
