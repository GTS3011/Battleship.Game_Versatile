package gr.epp.thesis;

import com.sun.xml.internal.ws.api.FeatureConstructor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.lang.reflect.Method;
import gr.epp.thesis.api.GenerickBlock;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author vigos.ioannis
 */
public class BoardListener implements MouseListener {

    private boolean listShip = false;
    private int rows = 10;
    private int columns = 10;
    private int coords[] = new int[3];
    private int orientation = 3;
    private int shipBlocks = 0;
    private JPanel parentPanel = null;

    public BoardListener(boolean listShip) {
        this.listShip = listShip;
    }

    public BoardListener(boolean listShip, int shipBlocks) {
        this.listShip = listShip;
        this.shipBlocks = shipBlocks;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        JButton pressedButton = (JButton) e.getSource();
        if (listShip) {
            System.out.println("" + shipBlocks);
        } else {
            parentPanel = (JPanel) pressedButton.getParent();
            pressedButton.setBackground(Color.YELLOW);
            getBlockPosition(pressedButton);
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    /* 
     * Code about the Hover effect. On Mouse Enter, and with the proper orientation, the grid paints so many SeaBlocks as many as the number of ShipsBlocks.
     */
    @Override
    public void mouseEntered(MouseEvent e) {
        getBlockPosition((JButton) e.getSource());
        switch (orientation) {
            case (3):
                if (coords[1] < (columns - (shipBlocks - 1)) && tempHold != shipBlocks) {
                    if (checkCollision(3)) {
                        battleFormations(3, true, false);
                    }
                }
                break;
            case (6):
                if (coords[0] < rows - (shipBlocks - 1) && tempHold != shipBlocks) {
                    if (checkCollision(6)) {
                        battleFormations(6, true, false);
                    }
                }
                break;
        }
        validate();
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    /**
     * A method that instantly, gives each SeaBlock unique coordinates, on the
     * grid.
     */
    public void getBlockPosition(JButton pressedBlock) {
        for (int i = 0; i < parentPanel.getComponentCount(); i++) {
            if (parentPanel.getComponent(i) == pressedBlock) {
                coords[0] = i / rows;
                coords[1] = i % columns;
                coords[2] = i;
                break;
            }
        }
    }

    /**
     * A method to create shipBlocks of MyBoard Panel. This method also adds
     * MouseListener to each ShipBlock.
     */
    private ShipBlock createShipBlock(int shipBlocks, int orientation, int currBlock) {
        ShipBlock shipBlock = new ShipBlock(shipBlocks, orientation, currBlock, true);
        shipBlock.addMouseListener(this);
        return shipBlock;
    }

    /**
     * A method for collision detection. This method prevents the installation
     * of another WarShip, that collides on the first one. Values of 3 & 6,
     * about orientation means orientation of the WarShip clockwise.
     */
    public boolean checkCollision(int orientation) {
        boolean freeArea = true;
        switch (orientation) {
            case (3):
                for (int i = coords[2]; i < (coords[2] + shipBlocks); i++) {
                    if (parentPanel.getComponent(i) instanceof ShipBlock) {
                        freeArea = false;
                        break;
                    }
                }
                return freeArea;
            case (6):
                for (int i = coords[2]; i < (coords[2] + (rows * shipBlocks)); i = i + rows) {
                    if (parentPanel.getComponent(i) instanceof ShipBlock) {
                        freeArea = false;
                        break;
                    }
                }
                return freeArea;
        }
        return false;
    }

    /**
     * A method that contains all interactions with the grid. Those are,
     * Hovering on seaBlocks, Exiting from seaBlocks seablock, and Capturing.
     * This method needs the orientation of the ship placement, and also two
     * arguments for the behavior of the WarShip before the capture. If Hovering
     * is true, then a ship is above buttons. If Exiting is true, then the
     * SeaBlock, has to repaint itself because the WarShip is elsewhere. Values
     * of 3 & 6, about orientation means orientation of the WarShip clockwise.
     */
    public void battleFormations(int orientation, boolean hovering, boolean exiting) {
        int currBlock = 0;
        switch (orientation) {
            case (3):
                for (int i = 0; i < shipBlocks; i++) {
                    if (hovering) {
                        parentPanel.getComponent(coords[2] + i).setBackground(Color.GREEN);
                    } else if (exiting) {
                        parentPanel.getComponent(coords[2] + i).setBackground(Color.CYAN);
                    } else {
                        parentPanel.remove(parentPanel.getComponent(coords[2] + i));
                        add(createShipBlock(shipBlocks, orientation, currBlock), coords[2] + i);
                    }
                    currBlock++;
                }
                break;
            case (6):
                for (int i = 0; i < shipBlocks; i++) {
                    if (hovering) {
                        parentPanel.getComponent(coords[2] + (i * rows)).setBackground(Color.GREEN);
                    } else if (exiting) {
                        parentPanel.getComponent(coords[2] + (i * rows)).setBackground(Color.CYAN);
                    } else {
                        parentPanel.remove(parentPanel.getComponent(coords[2] + (i * rows)));
                        add(createShipBlock(shipBlocks, orientation, currBlock), coords[2] + (i * rows));
                    }
                    currBlock++;
                }
                break;
        }
    }
}
