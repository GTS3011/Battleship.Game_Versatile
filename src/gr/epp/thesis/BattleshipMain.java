package gr.epp.thesis;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
    private JPanel myShips = new JPanel(new GridLayout(6, 1));
    private JPanel enemyShips = new JPanel(new GridLayout(6, 1));
    private ViewItem view = null;
    private static Object tempObject = null;
    private static Class tempClass;
    private AttackListener customListener;
    private Object x;

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
        upPanel.add(enemyShips, BorderLayout.WEST);
        downPanel.add(myBoard, BorderLayout.CENTER);
        downPanel.add(myShips, BorderLayout.EAST);

        try {
            for (int i = 0; i < rows * columns; i++) {
                tempClass = Class.forName("gr.epp.thesis." + currentPlayer + "Block");
                JButton temp1 = (JButton) createObjects(tempClass);
                enemyBoard.add(temp1);
                JButton temp2 = (JButton) createObjects(tempClass);
                myBoard.add(temp2);
            }
            for (int i = 0; i < 6; i++) {
                tempClass = Class.forName("gr.epp.thesis." + currentPlayer + "Block");
                JButton temp1 = (JButton) createObjects(tempClass);
                enemyShips.add(temp1);
                JButton temp2 = (JButton) createObjects(tempClass);
                myShips.add(temp2);
            }
            tempClass = Class.forName("gr.epp.thesis." + currentPlayer + "Label");
            JLabel tempLabel = (JLabel) createObjects(tempClass);
            decorPanel.add(tempLabel);

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ChildBlock.class.getName()).log(Level.SEVERE, null, ex);
        }

        masterFrame.validate();

        //for (int i = 0; i < panel.getComponentCount(); i++) {
        //  Method m[] = c[i].getDeclaredMethods();
        //for (int j = 0; j < m.length; j++) {
        //  System.out.print("Method: " + m[j].getName());
    }
    //System.out.print(",  Result: " + algo.doIt(10, 5));
    //}
    // } 

    public Object createObjects(Class tempClass) {
        try {
            //methodInvoke(tempClass);
            view = (ViewItem) tempClass.newInstance();
            tempObject = (Object) view;
            return tempObject;
        } catch (InstantiationException ex) {
            Logger.getLogger(ChildBlock.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ChildBlock.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public void methodInvoke(Class tempClass1) {
        Object f = null;
        try {
            Method[] d = tempClass1.getDeclaredMethods();
            for (int i = 0; i < d.length; i++) {
                if (d[i].getName() == "checker") {
                    System.out.println("oke!");
                    f = d[i].invoke(x, (Object[]) null);
                }
            }
            System.out.println("" + f);
        } catch (IllegalAccessException ex) {
            System.err.println("Not able to access method ");
            ex.printStackTrace();
        } catch (SecurityException ex) {
            System.err.println("Security Exception raised");
            ex.printStackTrace();
        } catch (IllegalArgumentException ex) {
            System.err.println("Incorrect supplied arguments");
            ex.printStackTrace();
        } catch (InvocationTargetException ex) {
            System.err.println("Not able to invoke method by String in Java");
            ex.printStackTrace();
        }
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
