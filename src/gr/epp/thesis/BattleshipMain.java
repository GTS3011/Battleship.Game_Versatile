package gr.epp.thesis;

import gr.epp.thesis.mvc.View;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author vigos.ioannis
 */
public class BattleshipMain implements MouseListener, ActionListener {

    private int rows = 10;
    private int columns = 10;
    private JFrame compoFrame = new JFrame("Type of Player: ");
    private String[] playerType = {"Adult", "Child", "Admiral"};
    private JComboBox playerTypeList = new JComboBox(playerType);
    private String currentPlayer = null;
    private static JFrame masterFrame = new JFrame("Battleship Game");
    private JPanel upPanel = new JPanel(new BorderLayout(10, 0));
    private JPanel decorPanel = new JPanel();
    private JPanel downPanel = new JPanel(new BorderLayout(10, 0));
    private JPanel myBoard = new JPanel(new GridLayout(rows, columns));
    private JPanel enemyBoard = new JPanel(new GridLayout(rows, columns));
    private static Class tempClass;

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
    public BattleshipMain(String currentPlayer) {

        /* All graphic contents of the game.
         * 
         */
        masterFrame.setSize(450, 900);
        masterFrame.setResizable(false);
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
        downPanel.add(myBoard, BorderLayout.CENTER);

        try {

            tempClass = Class.forName("gr.epp.thesis." + currentPlayer + "Block");
            for (int i = 0; i < rows * columns; i++) {
                JButton temp1 = (JButton) tempClass.newInstance();
                enemyBoard.add(temp1);
                JButton temp2 = (JButton) tempClass.newInstance();
                myBoard.add(temp2);
            }

            tempClass = Class.forName("gr.epp.thesis." + currentPlayer + "ShipList");
            JPanel shipPanel1 = (JPanel) tempClass.newInstance();
            JPanel shipPanel2 = (JPanel) tempClass.newInstance();
            Method tempMethod = tempClass.getMethod("totalItems", (Class[]) null);
            Object tempObj = tempMethod.invoke(shipPanel1, (Object[]) null);
            upPanel.add(shipPanel1, BorderLayout.WEST);
            downPanel.add(shipPanel2, BorderLayout.EAST);
            tempClass = Class.forName("gr.epp.thesis." + currentPlayer + "Block");
            Constructor tempConstr = tempClass.getConstructor(int.class, boolean.class);
            for (int i = 0; i < (int) tempObj; i++) {
                JButton temp1 = (JButton) tempConstr.newInstance(i, false);
                shipPanel1.add(temp1);
                JButton temp2 = (JButton) tempConstr.newInstance(i, true);
                shipPanel2.add(temp2);
            }

            tempClass = Class.forName("gr.epp.thesis." + currentPlayer + "Label");
            JLabel tempLabel = (JLabel) tempClass.newInstance();
            decorPanel.add(tempLabel);

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
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JComboBox playerTypeList = (JComboBox) e.getSource();
        String playerType = (String) playerTypeList.getSelectedItem();
        currentPlayer = playerType;
        compoFrame.setEnabled(false);
        compoFrame.dispose();
        new BattleshipMain(currentPlayer);
    }

    public static void main(String[] args) {
        BattleshipMain entryPoint = new BattleshipMain();
    }
}
