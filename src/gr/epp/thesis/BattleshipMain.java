package gr.epp.thesis;

import gr.epp.thesis.api.GenericLabel;
import gr.epp.thesis.api.GenericBlock;
import gr.epp.thesis.api.GenericPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author vigos.ioannis
 */
public class BattleshipMain implements ActionListener {

    private int rows = 0;
    private int columns = 0;
    private int frameWidth = 0;
    private int frameHeight = 0;
    private JFrame compoFrame = new JFrame("Type of Player: ");
    private String[] playerType = {"Adult", "Child", "Admiral"};
    private JComboBox playerTypeList = new JComboBox(playerType);
    private String currentPlayer = null;
    private static JFrame masterFrame = new JFrame("Battleship Game");
    private JPanel upPanel = new JPanel(new BorderLayout(10, 0));
    private JPanel decorPanel = new JPanel();
    private JPanel downPanel = new JPanel(new BorderLayout(10, 0));
    private JPanel myBoard = new JPanel();
    private JPanel enemyBoard = new JPanel();
    private static Class tempClass;
    private GameControl gameControl;

    /*
     * Player Selection
     */
    public BattleshipMain() {
        playerTypeList.setSelectedIndex(0);
        playerTypeList.addActionListener(this);
        compoFrame.add(playerTypeList);
        compoFrame.setVisible(true);
        compoFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        compoFrame.setLocationRelativeTo(null);
        compoFrame.setSize(250, 80);
    }

    /*
     * Main Game
     */
    public BattleshipMain(String currentPlayer, int rows, int columns, int frameWidth, int frameHeight) {

        /* All graphic contents of the game.
         * 
         */
        masterFrame.setSize(frameWidth, frameHeight);
        masterFrame.setResizable(true);
        masterFrame.setVisible(true);
        masterFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        masterFrame.setLocationRelativeTo(null);
        masterFrame.setVisible(true);
        masterFrame.setLayout(new GridLayout(3, 1, 0, 5));
        masterFrame.setBackground(Color.WHITE);
        masterFrame.add(upPanel);
        upPanel.setBackground(Color.WHITE);
        masterFrame.add(decorPanel);
        decorPanel.setBackground(Color.WHITE);
        masterFrame.add(downPanel);
        downPanel.setBackground(Color.WHITE);
        upPanel.add(enemyBoard, BorderLayout.CENTER);
        enemyBoard.setLayout(new GridLayout(rows, columns));
        downPanel.add(myBoard, BorderLayout.CENTER);
        myBoard.setLayout(new GridLayout(rows, columns));
        upPanel.setSize(600, 500);

        gameControl = new GameControl(myBoard, rows, columns);

        try {
            tempClass = Class.forName("gr.epp.thesis." + currentPlayer + "Block");
            GenericBlock tempSeaColor = (GenericBlock) tempClass.newInstance();
            for (int i = 0; i < rows * columns; i++) {
                GenericBlock tempSeaBlock1 = (GenericBlock) tempClass.newInstance();
                tempSeaBlock1.addMouseListener(gameControl);
                enemyBoard.add(tempSeaBlock1);
                GenericBlock tempSeaBlock2 = (GenericBlock) tempClass.newInstance();
                tempSeaBlock2.addMouseListener(gameControl);
                myBoard.add(tempSeaBlock2);
            }

            tempClass = Class.forName("gr.epp.thesis." + currentPlayer + "Label");
            GenericLabel tempDecorLabel = (GenericLabel) tempClass.newInstance();
            decorPanel.add(tempDecorLabel);

            Constructor tempLabelConstructor = tempClass.getConstructor(boolean.class);
            GenericLabel tempMyLabell = (GenericLabel) tempLabelConstructor.newInstance(false);
            GenericLabel tempMyLabel2 = (GenericLabel) tempLabelConstructor.newInstance(true);

            tempClass = Class.forName("gr.epp.thesis." + currentPlayer + "ShipList");
            GenericPanel tempShipList1 = (GenericPanel) tempClass.newInstance();
            GenericPanel tempShipList2 = (GenericPanel) tempClass.newInstance();
            upPanel.add(tempShipList1, BorderLayout.WEST);
            downPanel.add(tempShipList2, BorderLayout.EAST);
            
            tempClass = Class.forName("gr.epp.thesis." + currentPlayer + "Block");
            Constructor tempShipConstructor = tempClass.getConstructor(int.class, boolean.class);
            tempShipList1.add(tempMyLabell);
            tempShipList2.add(tempMyLabel2);
            for (int i = 0; i < tempShipList1.totalItems() - 1; i++) {
                GenericBlock tempShip1 = (GenericBlock) tempShipConstructor.newInstance(i, false);
                tempShip1.addMouseListener(gameControl);
                tempShipList1.add(tempShip1);
                GenericBlock tempShip2 = (GenericBlock) tempShipConstructor.newInstance(i, true);
                tempShip2.addMouseListener(gameControl);
                tempShipList2.add(tempShip2);
            }

            gameControl.setCurrentPlayerValues(currentPlayer, tempSeaColor.getSeaColor());

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
        JComboBox playerTypeList = (JComboBox) e.getSource();
        String playerType = (String) playerTypeList.getSelectedItem();
        currentPlayer = playerType;
        compoFrame.setEnabled(false);
        compoFrame.dispose();
        if (currentPlayer.equals("Child") || currentPlayer.equals("Adult")) {
            rows = 10;
            columns = 10;
            frameWidth = 450;
            frameHeight = 900;
        } else {
            rows = 15;
            columns = 15;
            frameWidth = 525;
            frameHeight = 1050;
        }
        new BattleshipMain(currentPlayer, rows, columns, frameWidth, frameHeight);
    }

    public static void main(String[] args) {
        BattleshipMain entryPoint = new BattleshipMain();
    }
}
