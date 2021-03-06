package gr.epp.thesis;

import gr.epp.thesis.api.GenericBlock;
import gr.epp.thesis.api.GenericValues;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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
    private int warshipBlocks = 0;
    private JPanel enemyBoard = null;
    private JPanel myBoard = null;
    private boolean horizontal = true;
    private GenericBlock currentWarship;
    private ArrayList<GenericBlock> warshipBlocksList;
    private ArrayList<GenericBlock> hittenBlocks;
    private int maxWarshipsBlocks = 0;
    private int warshipBlocksHold[];
    private Color seaColor = null;
    private ImageIcon water = null;
    private ImageIcon hit = null;
    private ImageIcon miss = null;
    private Point cursorHotSpot = new Point(10, 10);
    private Socket clientSocket = null;
    private boolean gameStarted = false;
    private PrintWriter out = null;
    private BufferedReader in = null;
    private boolean locked = false;
    private static int successfulHits = 0;
    private static int totalHits = 0;
    private int l = 0;

    public GameControl(GenericValues playerValues) {
        this.playerValues = playerValues;               //Current player values.
        this.rows = playerValues.getRows();
        this.columns = playerValues.getColumns();
        this.maxWarshipsBlocks = playerValues.getMaxWarshipsBlocks();
        this.seaColor = playerValues.getSeaColor();
        this.water = playerValues.getWater();
        this.hit = playerValues.getHit();
        this.miss = playerValues.getMiss();
        this.warshipBlocksList = new ArrayList<>();
        this.hittenBlocks = new ArrayList<>();
    }

    /**
     * Values needed to continue the game.
     */
    public void setLateValues(JPanel enemyBoard, JPanel myBoard) {
        this.enemyBoard = enemyBoard;
        this.myBoard = myBoard;
        activateBoard(enemyBoard, false);
    }

    /**
     * A method that activates/deactivates enemy's and player's board
     * components, so that you can't fire if it's not your turn, or you haven't
     * place your warships already. If it's your turn to fire, enemy's
     * components reactivating. This method also, sets a disable icon on the
     * blocks, and removes the gameConstrol Listener.
     */
    public void activateBoard(JPanel board, boolean activate) {
        for (int i = 0; i < playerValues.getTotalGridBlocks(); i++) {
            GenericBlock tempBlock = (GenericBlock) board.getComponent(i);
            tempBlock.setEnabled(activate);
            if (activate) {
                tempBlock.addMouseListener(this);
                tempBlock.setIcon(water);
            } else {
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
    public void warshipBlockOnGrid(GenericBlock warshipBlock, int currentBlock) {
        warshipBlock.setIcon(playerValues.getGridPieces(warshipBlocks, currentBlock, orientation, false));
        warshipBlock.setBackground(seaColor);
        warshipBlock.setWarshipBlockOnGrid(true);
        currentWarship.setEnabled(false);
        warshipBlocksList.add(currentWarship);
        for (int i = 0; i < myBoard.getComponentCount(); i++) {
            if (myBoard.getComponent(i) == warshipBlock) {
                this.warshipBlocksHold[l] = i;
                l++;
            }
        }
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
                for (int i = coords[2]; i < (coords[2] + warshipBlocks); i++) {
                    GenericBlock tempBlock = (GenericBlock) myBoard.getComponent(i);
                    if (tempBlock.isWarshipBlockOnGrid()) {
                        freeArea = false;
                        break;
                    }
                }
                return freeArea;
            case (6):
                for (int i = coords[2]; i < (coords[2] + (rows * warshipBlocks)); i = i + rows) {
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
                for (int i = 0; i < warshipBlocks; i++) {
                    GenericBlock tempSeaBlock = (GenericBlock) myBoard.getComponent(coords[2] + i);
                    if (hovering) {
                        tempSeaBlock.setIcon(null);
                        tempSeaBlock.setBackground(Color.GREEN);
                    } else if (exiting) {
                        tempSeaBlock.setIcon(water);
                        tempSeaBlock.setBackground(seaColor);
                    } else {
                        warshipBlockOnGrid(tempSeaBlock, currentBlock);
                    }
                    currentBlock++;
                }
                break;
            case (6):
                for (int i = 0; i < warshipBlocks; i++) {
                    GenericBlock tempSeaBlock = (GenericBlock) myBoard.getComponent(coords[2] + (i * rows));
                    if (hovering) {
                        tempSeaBlock.setIcon(null);
                        tempSeaBlock.setBackground(Color.GREEN);
                    } else if (exiting) {
                        tempSeaBlock.setIcon(water);
                        tempSeaBlock.setBackground(seaColor);
                    } else {
                        warshipBlockOnGrid(tempSeaBlock, currentBlock);
                    }
                    currentBlock++;
                }
                break;
        }
    }

    public void initiateGame() {
        if (warshipBlocksList.size() == playerValues.getMaxWarshipsBlocks()) {
            //Start the game session here...
            activateBoard(enemyBoard, true);
            gameStarted = true;
            System.out.println("GAMESTARTED");
        }
    }

    public void setSocket(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    /**
     * On run method, gameControl class receives enemy's hits, and response if
     * they were successful or missed.
     */
    @Override
    public void run() {
        try {
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            while (true) {
                String incomingMessage = in.readLine();
                String[] splitMessage = incomingMessage.split(":");
                int hitBlock = Integer.parseInt(splitMessage[1]);
                GenericBlock playerHitBlock = (GenericBlock) myBoard.getComponent(hitBlock);
                GenericBlock enemyHitBlock = (GenericBlock) enemyBoard.getComponent(hitBlock);

                if (splitMessage[0].equals("hit")) {
                    if (playerHitBlock.isWarshipBlockOnGrid()) {
                        out.println("success:" + hitBlock);
                        playerHitBlock.setIcon(hit);
                    } else {
                        out.println("missed:" + hitBlock);
                        playerHitBlock.setIcon(miss);
                    }
                    locked = false;
                } else {
                    if (splitMessage[0].equals("missed")) {
                        enemyHitBlock.setIcon(miss);
                    } else {
                        enemyHitBlock.setIcon(hit);
                        successfulHits++;
                    }
                    System.out.println("Accuracy: " + successfulHits + "/" + totalHits);
                }

                System.out.println(incomingMessage);
            }
        } catch (IOException ex) {
            Logger.getLogger(GameControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean isGameStarted() {
        return gameStarted;
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
        if (gameStarted && !locked) {
            getBlockPosition(clickedBlock);
            out.println("hit:" + coords[2]);
            totalHits++;
            locked = true;
        }
        if (clickedBlock.isOnShipsList()) {
            currentWarship = clickedBlock;
            warshipBlocks = clickedBlock.getTotalBlocks();
            this.warshipBlocksHold = new int[warshipBlocks];
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
                        if (coords[1] < (columns - (warshipBlocks - 1)) && !warshipBlocksList.contains(currentWarship)) {
                            if (checkCollision()) {
                                battleFormations(false, false);
                                initiateGame();
                            }
                        }
                        break;
                    case (6):
                        if (coords[0] < rows - (warshipBlocks - 1) && !warshipBlocksList.contains(currentWarship)) {
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
        if (!gameStarted) {
            if (!enteredBlock.isOnShipsList()) {
                getBlockPosition((GenericBlock) e.getSource());
                switch (orientation) {
                    case (3):
                        if (coords[1] < (columns - (warshipBlocks - 1)) && !warshipBlocksList.contains(currentWarship)) {
                            if (checkCollision()) {
                                battleFormations(true, false);
                            }
                        }
                        break;
                    case (6):
                        if (coords[0] < rows - (warshipBlocks - 1) && !warshipBlocksList.contains(currentWarship)) {
                            if (checkCollision()) {
                                battleFormations(true, false);
                            }
                        }
                        break;
                }
                myBoard.validate();
            }
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
        if (!gameStarted) {
            GenericBlock exitedBlock = (GenericBlock) e.getSource();
            if (!exitedBlock.isOnShipsList()) {
                getBlockPosition((GenericBlock) e.getSource());
                switch (orientation) {
                    case (3):
                        if (coords[1] < (columns - (warshipBlocks - 1))) {
                            if (checkCollision()) {
                                battleFormations(false, true);
                            }
                        }
                        break;
                    case (6):
                        if (coords[0] < rows - (warshipBlocks - 1)) {
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
}
