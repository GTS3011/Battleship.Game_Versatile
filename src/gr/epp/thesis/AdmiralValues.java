package gr.epp.thesis;

import gr.epp.thesis.api.GenericValues;
import java.awt.Color;
import java.awt.Toolkit;
import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 *
 * @author vigos.ioannis
 */
public class AdmiralValues extends GenericValues {

    public AdmiralValues() {
        this.rows = 15;
        this.columns = 15;
        this.frameWidth = 525;
        this.frameHeight = 1050;
        this.totalShips = 10;
        this.seaColor = Color.CYAN.darker();
        this.myShipsListBackColor = Color.GREEN.darker();
        this.enemyShipsListBackColor = Color.RED.darker();
        this.water = new ImageIcon("graphics/water.gif");
        this.decor = new ImageIcon("graphics/admiralDecorLabel.png");
        this.playerBanner = new ImageIcon("graphics/myFleetAdmiralIcon.png");
        this.enemyPlayerBanner = new ImageIcon("graphics/enemyFleetAdmiralIcon.png");
        this.toolkit = Toolkit.getDefaultToolkit();
        this.target = toolkit.getImage("graphics/target.gif");
        this.hit = new ImageIcon("graphics/fire.gif");
        this.miss = new ImageIcon("graphics/miss.gif");
        for (int i = 0; i < totalShips; i++) {
            this.myWarshipsIcons.add(new ImageIcon("graphics/modernWarship" + i + ".gif"));
        }
        this.enemyWarshipsIcons = (ArrayList<ImageIcon>) myWarshipsIcons.clone();
    }
}
