package gr.epp.thesis;

import gr.epp.thesis.api.GenericBlock;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
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

    private int rows = 0;
    private int columns = 0;
    private int coords[] = new int[3];
    private boolean onShipsList = false;
    private int orientation = 3;
    private int shipBlocks = 0;
    private JPanel enemyBoardPanel = null;
    private JPanel myBoardPanel = null;
    private boolean horizontal = true;
    private GenericBlock currentWarShip;
    private ArrayList<GenericBlock> shipsOnGrid = new ArrayList<>();
    private int maxShipsOnGrid = 0;
    private String currentPlayer = null;
    private Color seaColor = null;
    private int enemyComponentCount = 0;
    private boolean readyToStart = false;
    private Toolkit toolkit = Toolkit.getDefaultToolkit();
    private Image target = toolkit.getImage("graphics/target.gif");
    private Point cursorHotSpot = new Point(10, 10);

    public GameControl(JPanel enemyBoard, JPanel myBoard, int rows, int columns) {
        this.enemyBoardPanel = enemyBoard;
        this.myBoardPanel = myBoard;
        this.rows = rows;
        this.columns = columns;
    }

    public void setCurrentPlayerValues(String currentPlayer, Color seaColor, int enemyComponentCount, int maxShipsOnGrid) {
        this.currentPlayer = currentPlayer;
        this.seaColor = seaColor;
        this.enemyComponentCount = enemyComponentCount;
        this.maxShipsOnGrid = maxShipsOnGrid;
        activateEnemyGrid(false);
    }

    public void activateEnemyGrid(boolean activate) {
        for (int i = 0; i < enemyComponentCount; i++) {
            enemyBoardPanel.getComponent(i).setEnabled(activate);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        GenericBlock pressedBlock = (GenericBlock) e.getSource();
        onShipsList = pressedBlock.isOnShipsList();
        if (onShipsList) {
            currentWarShip = pressedBlock;
            shipBlocks = pressedBlock.getTotalBlocks();
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
                                initiateGame();
                            }
                        }
                        break;
                    case (6):
                        if (coords[0] < rows - (shipBlocks - 1) && !shipsOnGrid.contains(currentWarShip)) {
                            if (checkCollision()) {
                                battleFormations(false, false);
                                initiateGame();
                            }
                        }
                        break;
                }
                myBoardPanel.validate();
            }
        }

    }

    public void initiateGame() {
        maxShipsOnGrid--;
        if (maxShipsOnGrid == 0) {
            //Start the game session here...
            readyToStart = true;
        }
    }

    /*
     * Just for quicker ship selection.
     */
    @Override
    public void mousePressed(MouseEvent e) {
        GenericBlock pressedBlock = (GenericBlock) e.getSource();
        onShipsList = pressedBlock.isOnShipsList();
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
        GenericBlock pressedBlock = (GenericBlock) e.getSource();
        onShipsList = pressedBlock.isOnShipsList();
        if (!onShipsList) {
            getBlockPosition((GenericBlock) e.getSource());
            switch (orientation) {
                case (3):
                    if (coords[1] < (columns - (shipBlocks - 1)) && !shipsOnGrid.contains(currentWarShip)) {
                        if (checkCollision()) {
                            battleFormations(true, false);
                        }
                    }
                    break;
                case (6):
                    if (coords[0] < rows - (shipBlocks - 1) && !shipsOnGrid.contains(currentWarShip)) {
                        if (checkCollision()) {
                            battleFormations(true, false);
                        }
                    }
                    break;
            }
            myBoardPanel.validate();
        }
        if (pressedBlock.getParent().equals(enemyBoardPanel)) {
            Cursor targetCursor = toolkit.createCustomCursor(target, cursorHotSpot, "Cursor");
            enemyBoardPanel.setCursor(targetCursor);
        }
    }

    /* 
     * Code about the Exit Hover effect. On Mouse Exited, and with the proper orientation, the grid has to paint each SeaBlock again.
     */
    @Override
    public void mouseExited(MouseEvent e) {
        GenericBlock pressedBlock = (GenericBlock) e.getSource();
        onShipsList = pressedBlock.isOnShipsList();
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
        if (currentPlayer.equals("Adult") || currentPlayer.equals("Admiral")) {
            warShipBlock.setIcon(new ImageIcon("graphics/gridPieces/" + shipBlocks + "_" + currentBlock + "_" + orientation + ".gif"));
        } else {
            warShipBlock.setIcon(new ImageIcon("graphics/gridPieces/childGridShip.gif"));
        }
        warShipBlock.setBackground(seaColor);
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

    public boolean isReadyToStart() {
        return readyToStart;
    }
}
