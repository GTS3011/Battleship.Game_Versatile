package gr.epp.thesis;

import gr.epp.thesis.api.GenericBlock;
import java.awt.Color;
import java.awt.List;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
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
    private JPanel myBoardPanel = null;
    private int tempHold = 0;
    private boolean horizontal = true;
    private GenericBlock currentWarShip;
    private ArrayList<GenericBlock> shipsOnGrid = new ArrayList<>();
    private String currentPlayer = null;
    private Color seaColor = null;

    public GameControl(JPanel myBoard) {
        this.myBoardPanel = myBoard;
    }

    public void setCurrentPlayerValues(String currentPlayer, Color seaColor) {
        this.currentPlayer = currentPlayer;
        this.seaColor = seaColor;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        GenericBlock pressedWarship = (GenericBlock) e.getSource();
        onShipsList = pressedWarship.isOnShipsList();
        if (onShipsList) {
            currentWarShip = pressedWarship;
            shipBlocks = pressedWarship.getTotalBlocks();
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
                myBoardPanel.validate();
            } else {
                switch (orientation) {
                    case (3):
                        if (coords[1] < (columns - (shipBlocks - 1)) && !shipsOnGrid.contains(currentWarShip)) {
                            if (checkCollision()) {
                                battleFormations(false, false);
                                tempHold = shipBlocks;
                            }
                        }
                        break;
                    case (6):
                        if (coords[0] < rows - (shipBlocks - 1) && !shipsOnGrid.contains(currentWarShip)) {
                            if (checkCollision()) {
                                battleFormations(false, false);
                                tempHold = shipBlocks;
                            }
                        }
                        break;
                }
                myBoardPanel.validate();
            }
        }

    }

    /*
     * Just for quicker ship selection.
     */
    @Override
    public void mousePressed(MouseEvent e) {
        GenericBlock pressedButton = (GenericBlock) e.getSource();
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
        GenericBlock pressedButton = (GenericBlock) e.getSource();
        onShipsList = pressedButton.isOnShipsList();
        if (!onShipsList) {
            getBlockPosition((GenericBlock) e.getSource());
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
            myBoardPanel.validate();
        }
    }

    /* 
     * Code about the Exit Hover effect. On Mouse Exited, and with the proper orientation, the grid has to paint each SeaBlock again.
     */
    @Override
    public void mouseExited(MouseEvent e) {
        GenericBlock pressedButton = (GenericBlock) e.getSource();
        onShipsList = pressedButton.isOnShipsList();
        if (!onShipsList) {
            getBlockPosition((GenericBlock) e.getSource());
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
            myBoardPanel.validate();
        }
    }

    /**
     * A method that instantly, gives each SeaBlock unique coordinates, on the
     * grid.
     */
    public void getBlockPosition(GenericBlock pressedBlock) {
        for (int i = 0; i < myBoardPanel.getComponentCount(); i++) {
            if (myBoardPanel.getComponent(i) == pressedBlock) {
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
    public void warshipOnGrid(GenericBlock warShipBlock, int currentBlock) {
        if (currentPlayer.equals("Adult")) {
            warShipBlock.setIcon(new ImageIcon("graphics/gridPieces/" + shipBlocks + "_" + currentBlock + "_" + orientation + ".gif"));
        }
        //warShipBlock.setBackground(Color.DARK_GRAY);
        warShipBlock.setWarshipOn(true);
        currentWarShip.setEnabled(false);
        shipsOnGrid.add(currentWarShip);
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
                    GenericBlock tempBlock = (GenericBlock) myBoardPanel.getComponent(i);
                    if (tempBlock.isWarshipOn()) {
                        freeArea = false;
                        break;
                    }
                }
                return freeArea;
            case (6):
                for (int i = coords[2]; i < (coords[2] + (rows * shipBlocks)); i = i + rows) {
                    GenericBlock tempBlock = (GenericBlock) myBoardPanel.getComponent(i);
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
        int currentBlock = 0;
        switch (orientation) {
            case (3):
                for (int i = 0; i < shipBlocks; i++) {
                    if (hovering) {
                        myBoardPanel.getComponent(coords[2] + i).setBackground(Color.GREEN);
                    } else if (exiting) {
                        myBoardPanel.getComponent(coords[2] + i).setBackground(seaColor);
                    } else {
                        warshipOnGrid((GenericBlock) myBoardPanel.getComponent(coords[2] + i), currentBlock);
                    }
                    currentBlock++;
                }
                break;
            case (6):
                for (int i = 0; i < shipBlocks; i++) {
                    if (hovering) {
                        myBoardPanel.getComponent(coords[2] + (i * rows)).setBackground(Color.GREEN);
                    } else if (exiting) {
                        myBoardPanel.getComponent(coords[2] + (i * rows)).setBackground(seaColor);
                    } else {
                        warshipOnGrid((GenericBlock) myBoardPanel.getComponent(coords[2] + (i * rows)), currentBlock);
                    }
                    currentBlock++;
                }
                break;
        }
    }
}
