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

import tictactoe.TTTView;

public class GameUI extends JFrame {
    private JPanel gameContainer;
    private JLabel message;
    private JMenuBar menu;

    public GameUI(String title) {
        super();
        this.setSize(200, 200);
        this.setTitle(title);
        gameContainer = new JPanel();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        add(gameContainer, BorderLayout.EAST);
        add(makeButtonPanel(),BorderLayout.CENTER);
        //start();
    }

    private JPanel makeButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.add(makeTTTButton());
        return buttonPanel;
    }

    private JButton makeTTTButton() {
        JButton button = new JButton("Play Tic Tac Toe");
        button.addActionListener(e->tictactoe());
        return button;
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
