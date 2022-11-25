/*
 * To Do: Loading and Saving on the GUI
 *        More text pop ups for readability (which turn it is, etc)
 *        GUI for Numerical TTT
 *        Player Statistics
 */

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
import java.awt.GridLayout;
import java.util.Scanner;

public class TTTView extends JPanel {
    
    private JLabel messageLabel;
        private JLabel clearMessageLabel;
    private TicTacToe game;
    private JPanel buttonPanel;
    private GameUI menu;
    private PositionAwareButton[][] buttons;

    public TTTView(int wide, int tall, GameUI menuApplication) {
        super();
        setLayout(new BorderLayout());
        JPanel subPanel = new JPanel();
        menu = menuApplication;
        setGameController(new TicTacToe(wide, tall));
        // messageLabel = new JLabel("    Welcome to 3x3 Tic Tac Toe!");
        // add(messageLabel, BorderLayout.NORTH);
        messageLabel("  Welcome to 3x3 Tic Tac Toe! Player X is first.");
        add(buttonGrid(tall,wide), BorderLayout.CENTER);
        subPanel.add(makeButtonPanelSave(),BorderLayout.SOUTH);
        subPanel.add(makeButtonPanelLoad(), BorderLayout.SOUTH);
        add(subPanel, BorderLayout.SOUTH);
    }

    /*
     * METHOD IS CURRENTLY PRINTING OVERLAPPED PANELS! 
     */
    public void messageLabel(String arguement) {
        messageLabel = new JLabel(arguement);
        add(messageLabel, BorderLayout.NORTH);
    }

    public void setGameController(TicTacToe controller) {
        this.game = controller;
    }

    private JPanel makeButtonPanelSave() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.add(makeSaveButton());
        return buttonPanel;
    }

    private JPanel makeButtonPanelLoad() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.add(makeLoadButton());
        return buttonPanel;
    }

    private JButton makeLoadButton() {
        JButton button = new JButton("Load a Previous Game");
        button.addActionListener(e->loadGame(e));
        return button;
    }

    private JButton makeSaveButton() {
        JButton button = new JButton("Save and Exit");
        button.addActionListener(e->saveGame(e));
        return button;
    }

    private void loadGame(ActionEvent e) {
        String filename = JOptionPane.showInputDialog("Please enter the name of the file you wish to load in. \n"
                                                       + "(Ensure to include the extension (.csv)" );
        if (filename != null) {
            if (!filename.isEmpty()) {
                String loadedGame = SaveToFile.load(game, "savedBoard.csv", "assets");
                String player = loadedGame.substring(0, 1);
            }
        }
    } 

    private void saveGame(ActionEvent e) {
        String filename = JOptionPane.showInputDialog("Please enter the name of the file you wish to save too. \n"
                                                       + "(Ensure to include the extension (.csv)" );
        if (filename != null) {
            if (!filename.isEmpty()) {
                String player = game.getNextTurn();
                /* Swapping player before saving so it knows which turn is next */
                game.setTurn(player);
                player = game.getNextTurn();
                game.parser(player);
                SaveToFile.save(game, filename, "assets");
                menu.start(); //sends user back to the menu
            }
        }

    }

    private JPanel buttonGrid(int tall, int wide) {
        JPanel panel = new JPanel();
        buttons = new PositionAwareButton[tall][wide];
        panel.setLayout(new GridLayout(wide,tall));
            for (int y = 0; y < wide; y++) {
                for (int x = 0; x < tall; x++) {
                    buttons[y][x] = new PositionAwareButton();
                    buttons[y][x].setAcross(x+1);
                    buttons[y][x].setDown(y+1);
                    buttons[y][x].addActionListener(e->{setSymbol(e); checkGameOver();});
                    panel.add(buttons[y][x]);
                }
            }
            return panel;
    }

    private void setSymbol(ActionEvent e) {
        /* Swaps the turns after each button press */
        String symbol = game.getNextTurn();
        game.setTurn(symbol);
        symbol = game.getNextTurn();
        
        messageLabel("Player " + symbol + "'s Turn.");

        PositionAwareButton clicked = ((PositionAwareButton)(e.getSource()));
        if (game.takeTurn(clicked.getAcross(), clicked.getDown(), symbol)) {
            clicked.setText(game.getCell(clicked.getAcross(), clicked.getDown()));
        }
    }

    private void checkGameOver() {
        int choice = 0;
        JOptionPane gameOver = new JOptionPane();
        if (game.getWinner() == 1) { //X wins
            choice = gameOver.showConfirmDialog(null, "X Wins!", "Play Again?", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.NO_OPTION) {
                menu.start();
            } else {
                game.restartGrid();
                updateBoard();
            }
        } else if (game.getWinner() == 2) {
            choice = gameOver.showConfirmDialog(null, "O Wins!", "Play Again?", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.NO_OPTION) {
                menu.start();
            } else {
                game.restartGrid();
                updateBoard();
            }
        } else if (game.isDone() == true) {
            choice = gameOver.showConfirmDialog(null, "Tie!", "Play Again?", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.NO_OPTION) {
                menu.start();
            } else {
                game.restartGrid();
                updateBoard();
            }
        } 
    }

    protected void updateBoard() {
        for (int y=0; y<game.getHeight(); y++){
            for (int x=0; x<game.getWidth(); x++){  
                buttons[y][x].setText(" "); 
            }
        }
    }


}
    

