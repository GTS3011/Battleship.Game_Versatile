package gr.epp.thesis;

import gr.epp.thesis.api.GenericBlock;
import gr.epp.thesis.api.GenericValues;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author tsoutsas.yiorgos & vigkos.ioannis
 * @project Thesis_Battleship.Game
 * @author Vidakis.Nikolas & Vellis Giorgos
 * @since Spring Semester 2013
 * @inst. Applied Informatics and Multimedia - TEI of Crete
 */
public class GameControl implements MouseListener, Runnable {

    private GenericValues playerValues;
    private int rows = 0;
    private int columns = 0;
    private int coords[] = new int[3];
    private int orientation = 3;
    private int shipBlocks = 0;
    private JPanel enemyBoardPanel = null;
    private JPanel myBoardPanel = null;
    private boolean horizontal = true;
    private GenericBlock currentWarShip;
    private ArrayList<GenericBlock> shipsOnGrid = new ArrayList<>();
    private int maxShipsOnGrid = 0;
    private Color seaColor = null;
    private boolean readyToStart = false;
    private Point cursorHotSpot = new Point(10, 10);
    private DataOutputStream out = null;
    private DataInputStream in = null;
    private Socket clientSocket = null;

    public GameControl(GenericValues playerValues) {
        this.playerValues = playerValues;
        this.rows = playerValues.getRows();
        this.columns = playerValues.getColumns();
        this.seaColor = playerValues.getSeaColor();
    }

    /**
     * Current player values, needed for the setup of the game. Different
     * players equals to different values and behavior. Values needed every time
     * are the Current Player, it's pre-specified seaBlock's color, the enemy's
     * board component count (needed for the activateEnemyGrid() method), and an
     * integer about the maximum ships they can be put to the grid.
     */
    public void setLateValues(int maxShipsOnGrid, JPanel enemyBoardPanel, JPanel myBoardPanel) {
        this.maxShipsOnGrid = maxShipsOnGrid;
        this.enemyBoardPanel = enemyBoardPanel;
        this.myBoardPanel = myBoardPanel;
        activateEnemyGrid(false);
    }

    /**
     * A method that activates/deactivates the enemy's board components, so that
     * you can't fire if it's not your turn, or you haven't place your warships
     * already. If it's your turn to fire, enemy's components reactivating.
     */
    public void activateEnemyGrid(boolean activate) {
        for (int i = 0; i < playerValues.getTotalGridBlocks(); i++) {
            JButton temp = (JButton) enemyBoardPanel.getComponent(i);
            temp.setEnabled(activate);
            temp.removeMouseListener(this);
            temp.setDisabledIcon(playerValues.getWater());
        }
    }

    /**
     * A method that instantly, gives each seaBlock unique coordinates, on the
     * grid.
     */
    public void getBlockPosition(GenericBlock pressedBlock) {
        JPanel currentParent = (JPanel) pressedBlock.getParent();
        for (int i = 0; i < currentParent.getComponentCount(); i++) {
            if (currentParent.getComponent(i) == pressedBlock) {
                coords[0] = i / rows;
                coords[1] = i % columns;
                coords[2] = i;
                break;
            }
        }
    }

    /**
     * A method to create the shipBlocks on myBoard panel. This method adds
     * icons for every ship in every orientation. Later deactivates the
     * positioned warship on the list.
     */
    public void warshipOnGrid(GenericBlock warShipBlock, int currentBlock) {
        warShipBlock.setIcon(playerValues.getGridPieces(shipBlocks, currentBlock, orientation));
        warShipBlock.setBackground(seaColor);
        warShipBlock.setWarshipBlockOnGrid(true);
        currentWarShip.setEnabled(false);
        shipsOnGrid.add(currentWarShip);
    }

    /**
     * A method for collision detection. This method prevents the installation
     * of another warship, that collides on other ones. Values of 3 & 6, about
     * orientation means orientation of the warship clockwise.
     */
    public boolean checkCollision() {
        boolean freeArea = true;
        switch (orientation) {
            case (3):
                for (int i = coords[2]; i < (coords[2] + shipBlocks); i++) {
                    GenericBlock tempBlock = (GenericBlock) myBoardPanel.getComponent(i);
                    if (tempBlock.isWarshipBlockOnGrid()) {
                        freeArea = false;
                        break;
                    }
                }
                return freeArea;
            case (6):
                for (int i = coords[2]; i < (coords[2] + (rows * shipBlocks)); i = i + rows) {
                    GenericBlock tempBlock = (GenericBlock) myBoardPanel.getComponent(i);
                    if (tempBlock.isWarshipBlockOnGrid()) {
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
     * arguments for the behavior of the warship before the capture. If Hovering
     * is true, then a ship is above buttons. If Exiting is true, then the
     * SeaBlock, has to repaint itself because the warship is elsewhere. Values
     * of 3 & 6, about orientation means orientation of the warship clockwise.
     */
    public void battleFormations(boolean hovering, boolean exiting) {
        int currentBlock = 0;
        switch (orientation) {
            case (3):
                for (int i = 0; i < shipBlocks; i++) {
                    GenericBlock tempSeaBlock = (GenericBlock) myBoardPanel.getComponent(coords[2] + i);
                    if (hovering) {
                        tempSeaBlock.setIcon(null);
                        tempSeaBlock.setBackground(Color.GREEN);
                    } else if (exiting) {
                        tempSeaBlock.setIcon(playerValues.getWater());
                        tempSeaBlock.setBackground(seaColor);
                    } else {
                        warshipOnGrid(tempSeaBlock, currentBlock);
                    }
                    currentBlock++;
                }
                break;
            case (6):
                for (int i = 0; i < shipBlocks; i++) {
                    GenericBlock tempSeaBlock = (GenericBlock) myBoardPanel.getComponent(coords[2] + (i * rows));
                    if (hovering) {
                        tempSeaBlock.setIcon(null);
                        tempSeaBlock.setBackground(Color.GREEN);
                    } else if (exiting) {
                        tempSeaBlock.setIcon(playerValues.getWater());
                        tempSeaBlock.setBackground(seaColor);
                    } else {
                        warshipOnGrid(tempSeaBlock, currentBlock);
                    }
                    currentBlock++;
                }
                break;
        }
    }

    public boolean isReadyToStart() {
        return readyToStart;
    }

    public void initiateGame() {
        maxShipsOnGrid--;
        if (maxShipsOnGrid == 0) {
            //Start the game session here...
            readyToStart = true;
        }
    }

    public void setSocket(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            out = new DataOutputStream(clientSocket.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(GameControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        GenericBlock clickedBlock = (GenericBlock) e.getSource();
        if (readyToStart) {
            try {
                getBlockPosition(clickedBlock);
                out.writeInt(coords[2]);
            } catch (IOException ex) {
                Logger.getLogger(GameControl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (clickedBlock.isOnShipsList()) {
            currentWarShip = clickedBlock;
            shipBlocks = clickedBlock.getTotalBlocks();
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

    /*
     * Just for quicker ship selection.
     */
    @Override
    public void mousePressed(MouseEvent e) {
        GenericBlock pressedBlock = (GenericBlock) e.getSource();
        if (pressedBlock.isOnShipsList()) {
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
        GenericBlock enteredBlock = (GenericBlock) e.getSource();
        if (!enteredBlock.isOnShipsList()) {
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
        if (enteredBlock.getParent().equals(enemyBoardPanel)) {
            Cursor targetCursor = playerValues.getToolkit().createCustomCursor(playerValues.getTarget(), cursorHotSpot, "Cursor");
            enemyBoardPanel.setCursor(targetCursor);
        }
    }

    /* 
     * Code about the Exit Hover effect. On Mouse Exited, and with the proper orientation, the grid has to paint each SeaBlock again.
     */
    @Override
    public void mouseExited(MouseEvent e) {
        GenericBlock exitedBlock = (GenericBlock) e.getSource();
        if (!exitedBlock.isOnShipsList()) {
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
}
