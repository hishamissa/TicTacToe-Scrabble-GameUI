package tictactoe;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.BoxLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * This class represents the "main menu" GUI to choose which game
 * you want to play and swap between games
 **/
public class GameUI extends JFrame {
    private JPanel gameContainer;
    private JLabel message;
    private JMenuBar menu;

    /**
     * @param title The title of the menu screen
     */
    public GameUI(String title) {
        super();
        JPanel subPanel = new JPanel();
        this.setSize(300, 100);
        this.setTitle(title);
        gameContainer = new JPanel();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        add(gameContainer, BorderLayout.EAST);
        subPanel.add(makeButtonPanelTTT(),BorderLayout.CENTER);
        subPanel.add(makeButtonPanelNumTTT(), BorderLayout.CENTER);
        add(subPanel, BorderLayout.CENTER);
        pack();
        //start();
    }

    /**
     * Method creates a JPanel with a button to play tic tac toe
     * @return JPanel  returns the JPanel that holds the play TTT button
     */
    private JPanel makeButtonPanelTTT() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.add(makeTTTButton());
        return buttonPanel;
    }

    /**
     * Method creates a JPanel with a button to play numerical tic tac toe
     * @return JPanel  returns the JPanel that holds the play numerical TTT button
     */
    private JPanel makeButtonPanelNumTTT() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.add(makeNumTTTButton());
        return buttonPanel;
    }

    /**
     * Method creates a JButton to play tic tac toe
     * @return JButton  returns the button to play TTT
     */
    private JButton makeTTTButton() {
        JButton button = new JButton("Play Tic Tac Toe");
        button.addActionListener(e->tictactoe());
        return button;
    }

    /**
     * Method creates a JButton to play numerical tic tac toe
     * @return JButton  returns the button to play numerical TTT
     */
    private JButton makeNumTTTButton() {
        JButton button = new JButton("Play Numerical Tic Tac Toe");
        button.addActionListener(e->numTicTacToe());
        return button;

    }

    /**
     * Protected method to empty the game container and pass control
     * to numerical tic tac toe. Is called if the user presses the play
     * numerical tic tac toe button
     * @return void
     */
    protected void numTicTacToe() {
        gameContainer.removeAll();
        gameContainer.add(new NumTTTView(3,3,this));
        getContentPane().repaint();
        getContentPane().revalidate();
        pack();
    }

    /**
     * Protected method to empty the game container and pass control
     * to tic tac toe. Is called if the user presses the play
     * tic tac toe button
     * @return void
     */
    protected void tictactoe() {
        gameContainer.removeAll();
        gameContainer.add(new TTTView(3,3,this));
        getContentPane().repaint();
        getContentPane().revalidate();
        pack();
        
    }

    /**
     * Method to empty game container and get to the menu
     * @return void
     */
    public void start() {
        gameContainer.removeAll();
        getContentPane().repaint();
        getContentPane().revalidate();
        pack();
    }

    /**
     * Main method simply creates the main menu and makes it visible
     * @param args  Command line string
     * @return void
     */
    public static void main(String[] args) {
        GameUI ttt = new GameUI("Menu");
        ttt.setVisible(true);
    }
}
