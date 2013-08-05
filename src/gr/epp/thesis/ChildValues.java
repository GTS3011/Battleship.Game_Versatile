package gr.epp.thesis;

import gr.epp.thesis.api.GenericValues;
import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

/**
 *
 * @author vigos.ioannis
 */
public class ChildValues extends GenericValues {

    public ChildValues() {
        this.rows = 10;
        this.columns = 10;
        this.frameWidth = 450;
        this.frameHeight = 900;
        this.totalShips = 9;
        this.seaColor = Color.CYAN;
        this.myShipsListBackColor = Color.GREEN;
        this.enemyShipsListBackColor = Color.RED;
        this.water = new ImageIcon("graphics/water.gif");
        this.decor = new ImageIcon("graphics/childDecorLabel.png");
        this.playerBanner = new ImageIcon("graphics/childMyListMotherShip.png");
        this.enemyPlayerBanner = new ImageIcon("graphics/childEnemyListMotherShip.png");
        this.toolkit = Toolkit.getDefaultToolkit();
        this.target = toolkit.getImage("graphics/target.gif");
        this.hit = new ImageIcon("graphics/fire.gif");
        this.miss = new ImageIcon("graphics/miss.gif");
        //this.myWarshipsIcons.add(new ImageIcon("graphics/childMyListShip.png"));
        //this.enemyWarshipsIcons.add(new ImageIcon("graphics/childEnemyListShip.png"));
    }
}
