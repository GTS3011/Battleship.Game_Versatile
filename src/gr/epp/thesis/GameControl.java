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
import javax.swing.ImageIcon;
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
    private JPanel enemyBoard = null;
    private JPanel myBoard = null;
    private boolean horizontal = true;
    private GenericBlock currentWarShip;
    private ArrayList<GenericBlock> shipsOnGrid = new ArrayList<>();
    private ArrayList<GenericBlock> hitteBlocks = new ArrayList<>();
    private int maxShipsOnGrid = 0;
    private Color seaColor = null;
    private ImageIcon water = null;
    private boolean readyToStart = false;
    private Point cursorHotSpot = new Point(10, 10);
    private DataOutputStream out = null;
    private DataInputStream in = null;
    private Socket clientSocket = null;

    public GameControl(GenericValues playerValues) {
        this.playerValues = playerValues;               //Current player values.
        this.rows = playerValues.getRows();
        this.columns = playerValues.getColumns();
        this.seaColor = playerValues.getSeaColor();
        this.water = playerValues.getWater();
    }

    /**
     * Values needed to continue the game.
     */
    public void setLateValues(int maxShipsOnGrid, JPanel enemyBoard, JPanel myBoard) {
        this.maxShipsOnGrid = maxShipsOnGrid;
        this.enemyBoard = enemyBoard;
        this.myBoard = myBoard;
        activateEnemyGrid(true);
    }

    /**
     * A method that activates/deactivates the enemy's board components, so that
     * you can't fire if it's not your turn, or you haven't place your warships
     * already. If it's your turn to fire, enemy's components reactivating. This
     * method also, sets a disable icon on the blocks, and removes the
     * gameConstrol Listener.
     */
    public void activateEnemyGrid(boolean activate) {
        if (!activate) {
            for (int i = 0; i < playerValues.getTotalGridBlocks(); i++) {
                GenericBlock tempBlock = (GenericBlock) enemyBoard.getComponent(i);
                tempBlock.setEnabled(false);
                tempBlock.removeMouseListener(this);
                tempBlock.setDisabledIcon(water);
            }
        }
    }

    /**
     * A method that instantly, gives each seaBlock unique coordinates, on the
     * grid.
     */
    public void getBlockPosition(GenericBlock block) {
        JPanel currentParent = (JPanel) block.getParent();
        for (int i = 0; i < currentParent.getComponentCount(); i++) {
            if (currentParent.getComponent(i) == block) {
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
                    GenericBlock tempBlock = (GenericBlock) myBoard.getComponent(i);
                    if (tempBlock.isWarshipBlockOnGrid()) {
                        freeArea = false;
                        break;
                    }
                }
                return freeArea;
            case (6):
                for (int i = coords[2]; i < (coords[2] + (rows * shipBlocks)); i = i + rows) {
                    GenericBlock tempBlock = (GenericBlock) myBoard.getComponent(i);
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
                    GenericBlock tempSeaBlock = (GenericBlock) myBoard.getComponent(coords[2] + i);
                    if (hovering) {
                        tempSeaBlock.setIcon(null);
                        tempSeaBlock.setBackground(Color.GREEN);
                    } else if (exiting) {
                        tempSeaBlock.setIcon(water);
                        tempSeaBlock.setBackground(seaColor);
                    } else {
                        warshipOnGrid(tempSeaBlock, currentBlock);
                    }
                    currentBlock++;
                }
                break;
            case (6):
                for (int i = 0; i < shipBlocks; i++) {
                    GenericBlock tempSeaBlock = (GenericBlock) myBoard.getComponent(coords[2] + (i * rows));
                    if (hovering) {
                        tempSeaBlock.setIcon(null);
                        tempSeaBlock.setBackground(Color.GREEN);
                    } else if (exiting) {
                        tempSeaBlock.setIcon(water);
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

    public void setHits(int hit) {
    }

    public void battle(int hit) {
        GenericBlock tempHit = (GenericBlock) myBoard.getComponent(hit);
        tempHit.setIcon(null);
        if (tempHit.isWarshipBlockOnGrid()) {
            tempHit.setIcon(playerValues.getHit());
        } else {
            tempHit.setIcon(playerValues.getMiss());
        }
    }

    /**
     * Handles all click interactions. Also changes orientation if right button
     * is clicked.
     *
     * @param e
     */
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
                myBoard.validate();
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
                myBoard.validate();
            }
        }

    }

    /**
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

    /**
     * Code about the Hover effect. On Mouse Enter, and with the proper
     * orientation, the grid paints so many SeaBlocks as many as the number of
     * ShipsBlocks.
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
            myBoard.validate();
        }
        if (enteredBlock.getParent().equals(enemyBoard)) {
            Cursor targetCursor = playerValues.getToolkit().createCustomCursor(playerValues.getTarget(), cursorHotSpot, "Cursor");
            enemyBoard.setCursor(targetCursor);
        }
    }

    /**
     * Code about the Exit Hover effect. On Mouse Exited, and with the proper
     * orientation, the grid has to paint each SeaBlock again.
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
            myBoard.validate();
        }
    }
}
