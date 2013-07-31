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
public class GameControl implements MouseListener {

    private int rows = 10;
    private int columns = 10;
    private int coords[] = new int[3];
    private boolean onShipsList = false;
    private int orientation = 3;
    private int shipBlocks = 0;
    private JPanel parentPanel = null;
    private int tempHold = 0;
    private boolean horizontal = true;
    private boolean shipOnGrid = false;
    private GenerickBlock currentShip;

    public GameControl(JPanel myBoard) {
        this.parentPanel = myBoard;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        GenerickBlock pressedButton = (GenerickBlock) e.getSource();
        onShipsList = pressedButton.isOnShipsList();
        if (onShipsList) {
            currentShip = pressedButton;
            setShipBlocks(pressedButton.getTotalBlocks());
            System.out.println("" + pressedButton.getTotalBlocks());
        } else {
            if (e.getButton() == MouseEvent.BUTTON3) {
                mouseExited(e);
                horizontal ^= true;
                if (horizontal) {
                    orientation = 3;
                    mouseEntered(e);
                } else {
                    orientation = 6;
                    mouseEntered(e);
                }
                parentPanel.validate();
            } else {
                getBlockPosition((GenerickBlock) e.getSource());
                switch (orientation) {
                    case (3):
                        if (coords[1] < (columns - (shipBlocks - 1)) && tempHold != shipBlocks) {
                            if (checkCollision()) {
                                battleFormations(false, false);
                                tempHold = shipBlocks;
                                shipOnGrid = true;
                            }
                        }
                        break;
                    case (6):
                        if (coords[0] < rows - (shipBlocks - 1) && tempHold != shipBlocks) {
                            if (checkCollision()) {
                                battleFormations(false, false);
                                tempHold = shipBlocks;
                                shipOnGrid = true;
                            }
                        }
                        break;
                }
                parentPanel.validate();
            }
        }

    }

    /*
     * Just for quicker ship selection.
     */
    @Override
    public void mousePressed(MouseEvent e) {
        GenerickBlock pressedButton = (GenerickBlock) e.getSource();
        onShipsList = pressedButton.isOnShipsList();
        if (onShipsList) {
            mouseClicked(e);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    /* 
     * Code about the Hover effect. On Mouse Enter, and with the proper orientation, 
     * the grid paints so many SeaBlocks as many as the number of ShipsBlocks.
     */
    @Override
    public void mouseEntered(MouseEvent e) {
        GenerickBlock pressedButton = (GenerickBlock) e.getSource();
        onShipsList = pressedButton.isOnShipsList();
        if (!onShipsList) {
            getBlockPosition((GenerickBlock) e.getSource());
            switch (orientation) {
                case (3):
                    if (coords[1] < (columns - (shipBlocks - 1)) && tempHold != shipBlocks) {
                        if (checkCollision()) {
                            battleFormations(true, false);
                        }
                    }
                    break;
                case (6):
                    if (coords[0] < rows - (shipBlocks - 1) && tempHold != shipBlocks) {
                        if (checkCollision()) {
                            battleFormations(true, false);
                        }
                    }
                    break;
            }
            parentPanel.validate();
        }
    }

    /* 
     * Code about the Exit Hover effect. On Mouse Exited, and with the proper orientation, the grid has to paint each SeaBlock again.
     */
    @Override
    public void mouseExited(MouseEvent e) {
        GenerickBlock pressedButton = (GenerickBlock) e.getSource();
        onShipsList = pressedButton.isOnShipsList();
        if (!onShipsList) {
            getBlockPosition((GenerickBlock) e.getSource());
            switch (orientation) {
                case (3):
                    if (coords[1] < (columns - (shipBlocks - 1))) {
                        if (checkCollision()) {
                            battleFormations(false, true);
                        }
                    }
                    break;
                case (6):
                    if (coords[0] < rows - (shipBlocks - 1)) {
                        if (checkCollision()) {
                            battleFormations(false, true);
                        }
                    }
                    break;
            }
            parentPanel.validate();
        }
    }

    /**
     * A method that instantly, gives each SeaBlock unique coordinates, on the
     * grid.
     */
    public void getBlockPosition(GenerickBlock pressedBlock) {
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
    public void warshipOnGrid(GenerickBlock warShipBlock) {
        warShipBlock.setBackground(Color.DARK_GRAY);
        warShipBlock.setWarshipOn(true);
        currentShip.setEnabled(false);
    }

    /**
     * A method for collision detection. This method prevents the installation
     * of another WarShip, that collides on the first one. Values of 3 & 6,
     * about orientation means orientation of the WarShip clockwise.
     */
    public boolean checkCollision() {
        boolean freeArea = true;
        switch (orientation) {
            case (3):
                for (int i = coords[2]; i < (coords[2] + shipBlocks); i++) {
                    GenerickBlock tempBlock = (GenerickBlock) parentPanel.getComponent(i);
                    if (tempBlock.isWarshipOn()) {
                        freeArea = false;
                        break;
                    }
                }
                return freeArea;
            case (6):
                for (int i = coords[2]; i < (coords[2] + (rows * shipBlocks)); i = i + rows) {
                    GenerickBlock tempBlock = (GenerickBlock) parentPanel.getComponent(i);
                    if (tempBlock.isWarshipOn()) {
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
     * Hovering on seaBlocks, Exiting from seaBlocks hover, and Capturing. This
     * method needs the orientation of the ship placement, and also two
     * arguments for the behavior of the WarShip before the capture. If Hovering
     * is true, then a ship is above buttons. If Exiting is true, then the
     * SeaBlock, has to repaint itself because the WarShip is elsewhere. Values
     * of 3 & 6, about orientation means orientation of the WarShip clockwise.
     */
    public void battleFormations(boolean hovering, boolean exiting) {
        switch (orientation) {
            case (3):
                for (int i = 0; i < shipBlocks; i++) {
                    if (hovering) {
                        System.out.println("ok edo!");
                        parentPanel.getComponent(coords[2] + i).setBackground(Color.GREEN);
                    } else if (exiting) {
                        parentPanel.getComponent(coords[2] + i).setBackground(Color.CYAN);
                    } else {
                        warshipOnGrid((GenerickBlock) parentPanel.getComponent(coords[2] + i));
                    }
                }
                break;
            case (6):
                for (int i = 0; i < shipBlocks; i++) {
                    if (hovering) {
                        parentPanel.getComponent(coords[2] + (i * rows)).setBackground(Color.GREEN);
                    } else if (exiting) {
                        parentPanel.getComponent(coords[2] + (i * rows)).setBackground(Color.CYAN);
                    } else {
                        warshipOnGrid((GenerickBlock) parentPanel.getComponent(coords[2] + (i * rows)));
                    }
                }
                break;
        }
    }

    public void setShipBlocks(int shipBlocks) {
        this.shipBlocks = shipBlocks;
    }

    public int getShipBlocks() {
        return shipBlocks;
    }
}
