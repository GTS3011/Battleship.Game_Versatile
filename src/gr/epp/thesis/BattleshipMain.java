package gr.epp.thesis;

import gr.epp.thesis.api.GenericBlock;
import gr.epp.thesis.api.GenericLabel;
import gr.epp.thesis.api.GenericPanel;
import gr.epp.thesis.api.GenericValues;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author tsoutsas.yiorgos & vigkos.ioannis
 * @project Thesis_Battleship.Game
 * @author Vidakis.Nikolas & Vellis Giorgos
 * @since Spring Semester 2013
 * @inst. Applied Informatics and Multimedia - TEI of Crete
 *
 */
public class BattleshipMain implements ActionListener, Runnable {

    private GenericValues currentPlayerValues;
    private int rows = 0;
    private int columns = 0;
    private int frameWidth = 0;
    private int frameHeight = 0;
    private JFrame compoBoxFrame = new JFrame("Type of Player: ");
    private String[] playerType = {"Adult", "Child", "Admiral"};
    private JComboBox playerTypeBox = new JComboBox(playerType);
    private String currentPlayer = null;
    private static JFrame masterFrame = new JFrame("Battleship Game");
    private JPanel upPanel = new JPanel();
    private JPanel decorPanel = new JPanel();
    private JPanel downPanel = new JPanel();
    private JPanel myBoard = new JPanel();
    private JPanel enemyBoard = new JPanel();
    private static Class tempClass;
    private GameControl gameControl;
    private GenericPanel tempShipList1;
    private GenericPanel tempShipList2;
    private int maxShipsOnGrid = 0;
    private DataOutputStream out = null;
    private DataInputStream in = null;
    private static Socket clientSocket = null;

    /*
     * Player Selection
     */
    public BattleshipMain() {
        playerTypeBox.setSelectedIndex(0);
        playerTypeBox.addActionListener(this);
        compoBoxFrame.add(playerTypeBox);
        compoBoxFrame.setVisible(true);
        compoBoxFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        compoBoxFrame.setLocationRelativeTo(null);
        compoBoxFrame.setSize(250, 80);
        compoBoxFrame.setResizable(true);
    }

    /*
     * Main Game
     */
    public BattleshipMain(String currentPlayer, GenericValues currentPlayerValues) {
        this.currentPlayerValues = currentPlayerValues;
        masterFrame.setSize(this.currentPlayerValues.getFrameWidth(), this.currentPlayerValues.getFrameHeight());
        masterFrame.setResizable(false);
        masterFrame.setVisible(true);
        masterFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        masterFrame.setLocationRelativeTo(null);
        masterFrame.setLayout(new GridLayout(3, 1, 0, 5));
        masterFrame.setBackground(Color.WHITE);
        masterFrame.add(upPanel);
        upPanel.setBackground(Color.WHITE);
        masterFrame.add(decorPanel);
        decorPanel.setBackground(Color.WHITE);
        masterFrame.add(downPanel);
        downPanel.setBackground(Color.WHITE);
        upPanel.setLayout(new BorderLayout(10, 0));
        upPanel.add(enemyBoard, BorderLayout.CENTER);
        enemyBoard.setLayout(new GridLayout(this.currentPlayerValues.getRows(), this.currentPlayerValues.getColumns()));
        downPanel.setLayout(new BorderLayout(10, 0));
        downPanel.add(myBoard, BorderLayout.CENTER);
        myBoard.setLayout(new GridLayout(this.currentPlayerValues.getRows(), this.currentPlayerValues.getColumns()));
        masterFrame.validate();

        gameControl = new GameControl(enemyBoard, myBoard, this.currentPlayerValues.getRows(), this.currentPlayerValues.getColumns());

        try {
            tempClass = Class.forName("gr.epp.thesis." + currentPlayer + "Block");
            GenericBlock tempSeaColor = (GenericBlock) tempClass.newInstance();
            for (int i = 0; i < this.currentPlayerValues.getRows() * this.currentPlayerValues.getColumns(); i++) {
                GenericBlock enemySeaBlock = (GenericBlock) tempClass.newInstance();
                enemySeaBlock.addMouseListener(gameControl);
                enemyBoard.add(enemySeaBlock);
                GenericBlock mySeaBlock = (GenericBlock) tempClass.newInstance();
                mySeaBlock.addMouseListener(gameControl);
                myBoard.add(mySeaBlock);
            }

            tempClass = Class.forName("gr.epp.thesis." + currentPlayer + "Label");
            GenericLabel decorLabel = (GenericLabel) tempClass.newInstance();
            decorPanel.add(decorLabel);

            Constructor tempLabelConstructor = tempClass.getConstructor(boolean.class);
            GenericLabel tempMyLabell = (GenericLabel) tempLabelConstructor.newInstance(false);
            GenericLabel tempMyLabel2 = (GenericLabel) tempLabelConstructor.newInstance(true);

            tempClass = Class.forName("gr.epp.thesis." + currentPlayer + "ShipList");
            tempShipList1 = (GenericPanel) tempClass.newInstance();
            tempShipList2 = (GenericPanel) tempClass.newInstance();
            upPanel.add(tempShipList1, BorderLayout.WEST);
            downPanel.add(tempShipList2, BorderLayout.EAST);

            tempClass = Class.forName("gr.epp.thesis." + currentPlayer + "Block");
            Constructor tempShipConstructor = tempClass.getConstructor(int.class, boolean.class);
            tempShipList1.add(tempMyLabell);
            tempShipList2.add(tempMyLabel2);
            for (int i = 0; i < tempShipList1.getTotalItems() - 1; i++) {
                GenericBlock tempShip1 = (GenericBlock) tempShipConstructor.newInstance(i, false);
                tempShip1.addMouseListener(gameControl);
                tempShipList1.add(tempShip1);
                GenericBlock tempShip2 = (GenericBlock) tempShipConstructor.newInstance(i, true);
                tempShip2.addMouseListener(gameControl);
                tempShipList2.add(tempShip2);
                maxShipsOnGrid++;
            }

            gameControl.setCurrentPlayerValues(currentPlayer, tempSeaColor.getSeaColor(), enemyBoard.getComponentCount(), maxShipsOnGrid);

            int portNumber = 1501;
            String host = "localhost";
            System.out.print("Connect with " + host + " in port " + portNumber + ": ");
            try {
                clientSocket = new Socket(host, portNumber);
                System.out.println("Connected");
                gameControl.setSocket(clientSocket);
                (new Thread(gameControl)).start();
            } catch (UnknownHostException ex) {
                Logger.getLogger(BattleshipMain.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(BattleshipMain.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (NoSuchMethodException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(BattleshipMain.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ChildBlock.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(ChildBlock.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ChildBlock.class.getName()).log(Level.SEVERE, null, ex);
        }

        masterFrame.validate();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JComboBox playerTypeBox = (JComboBox) e.getSource();
        currentPlayer = (String) playerTypeBox.getSelectedItem();
        compoBoxFrame.dispose();
        try {
            tempClass = Class.forName("gr.epp.thesis." + currentPlayer + "Values");
            currentPlayerValues = (GenericValues) tempClass.newInstance();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ChildBlock.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(BattleshipMain.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(BattleshipMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        Thread thread = new Thread(new BattleshipMain(currentPlayer, currentPlayerValues));
        thread.start();
    }

    @Override
    public void run() {
        while (!this.gameControl.isReadyToStart()) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(BattleshipMain.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //Temporary
//        for (int i = 0; i < myBoard.getComponentCount(); i++) {
//            myBoard.getComponent(i).removeMouseListener(gameControl);
//        }
        for (int i = 0; i < tempShipList2.getComponentCount(); i++) {
            tempShipList2.getComponent(i).removeMouseListener(gameControl);
        }
        System.out.println("Starting Game...");

        try {
            in = new DataInputStream(clientSocket.getInputStream());
            out = new DataOutputStream(clientSocket.getOutputStream());

            while (true) {
                int value = in.readInt();
                System.out.println("Enemy has pressed the block " + value + " on his board");
                //notifies all views with the incoming value from the server.
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        BattleshipMain entryPoint = new BattleshipMain();
    }
}
