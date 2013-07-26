package gr.epp.thesis;

import java.awt.event.MouseEvent;
import gr.epp.thesis.api.GenerickBlock;

/**
 *
 * @author vigos.ioannis
 */
public class GameControl implements BoardListener {

    private GenerickBlock generickBlock;

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("Clicked");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int temp = generickBlock.getTotalBlocks();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println("Released");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        System.out.println("Entered");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        System.out.println("Exited");
    }
}
