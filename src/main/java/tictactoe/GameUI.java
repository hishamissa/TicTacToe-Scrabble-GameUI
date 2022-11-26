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

public class GameUI extends JFrame {
    private JPanel gameContainer;
    private JLabel message;
    private JMenuBar menu;

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

    private JPanel makeButtonPanelTTT() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.add(makeTTTButton());
        return buttonPanel;
    }

    private JPanel makeButtonPanelNumTTT() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.add(makeNumTTTButton());
        return buttonPanel;
    }

    private JButton makeTTTButton() {
        JButton button = new JButton("Play Tic Tac Toe");
        button.addActionListener(e->tictactoe());
        return button;
    }

    private JButton makeNumTTTButton() {
        JButton button = new JButton("Play Numerical Tic Tac Toe");
        button.addActionListener(e->numTicTacToe());
        return button;

    }

    protected void numTicTacToe() {
        gameContainer.removeAll();
        gameContainer.add(new NumTTTView(3,3,this));
        getContentPane().repaint();
        getContentPane().revalidate();
        pack();
    }

    protected void tictactoe() {
        gameContainer.removeAll();
        gameContainer.add(new TTTView(3,3,this));
        getContentPane().repaint();
        getContentPane().revalidate();
        pack();
        
    }

    public void start() {
        gameContainer.removeAll();
        getContentPane().repaint();
        getContentPane().revalidate();
        pack();
    }


    public static void main(String[] args) {
        GameUI ttt = new GameUI("Menu");
        ttt.setVisible(true);
    }
}
