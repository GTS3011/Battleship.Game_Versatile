package gr.epp.thesis;

import com.sun.xml.internal.ws.api.FeatureConstructor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.lang.reflect.Method;
import gr.epp.thesis.api.GenerickBlock;
import java.awt.Color;
import javax.swing.JButton;

/**
 *
 * @author vigos.ioannis
 */
public class BoardListener implements MouseListener {

    private boolean listShip = false;
    private int totalBlocks;

    public BoardListener(boolean listShip) {
        this.listShip = listShip;
    }

    public BoardListener(boolean listShip, int totalBlocks) {
        this.listShip = listShip;
        this.totalBlocks = totalBlocks;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        JButton f = (JButton) e.getSource();
        if (listShip) {
            f.setBackground(Color.red);
            System.out.println("" + totalBlocks);
        } else {
            f.setBackground(Color.YELLOW);
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
