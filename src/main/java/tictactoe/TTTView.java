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

/**
 * Class that holds the regular TTT's GUI and allows for the user to play
 * Extends JPanel
 */
public class TTTView extends JPanel {
    
    private JLabel messageLabel;
        private JLabel clearMessageLabel;
    private TicTacToe game;
    private JPanel buttonPanel;
    private GameUI menu;
    private PositionAwareButton[][] buttons;

    /**
     * @param wide  grids width
     * @param tall  grids height
     * @param menuApplication gameContainer to play in
     */
    public TTTView(int wide, int tall, GameUI menuApplication) {
        super();
        setLayout(new BorderLayout());
        JPanel subPanel = new JPanel();
        menu = menuApplication;
        setGameController(new TicTacToe(wide, tall));
        messageLabel = new JLabel("");
        // add(messageLabel, BorderLayout.NORTH);
        messageLabel("  Welcome to 3x3 Tic Tac Toe! Player X is first.");
        add(buttonGrid(tall,wide), BorderLayout.CENTER);
        subPanel.add(makeButtonPanelSave(),BorderLayout.SOUTH);
        subPanel.add(makeButtonPanelLoad(), BorderLayout.SOUTH);
        add(subPanel, BorderLayout.SOUTH);
    }

    /**
     * Method to update the JLabel at the top of the game grid
     * @param arguement  String with the updated text
     */
    public void messageLabel(String arguement) {
        //messageLabel = new JLabel(arguement);
        messageLabel.setText(arguement);
        add(messageLabel, BorderLayout.NORTH);
    }

    /**
     * Method to pass control to the game
     * @param controller  of type TicTacToe
     */
    public void setGameController(TicTacToe controller) {
        this.game = controller;
    }

    /**
     * Method which creates a JPanel and adds the button to save the game
     * @return JPanel
     */
    private JPanel makeButtonPanelSave() {
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.add(makeSaveButton());
        return buttonPanel;
    }

    /**
     * Method which creates a JPanel and adds the button to load the game
     * @return JPanel
     */
    private JPanel makeButtonPanelLoad() {
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.add(makeLoadButton());
        return buttonPanel;
    }

    /**
     * Method which creates a JButton to load the game
     * Calls loadGame() when the button is pressed
     * @return JButton
     */
    private JButton makeLoadButton() {
        JButton button = new JButton("Load a Previous Game");
        button.addActionListener(e->loadGame(e));
        return button;
    }

    /**
     * Method which creates a JButton to save the game
     * Calls saveGame() when the button is pressed
     * @return JButton
     */
    private JButton makeSaveButton() {
        JButton button = new JButton("Save and Exit");
        button.addActionListener(e->saveGame(e));
        return button;
    }

    /**
     * Method asks for the file to load in and uses Savable method load()
     * to parse it and load it in. 
     * @param e  representing the action event of the user pressing the button
     */
    private void loadGame(ActionEvent e) {
        String filename = JOptionPane.showInputDialog("Please enter the name of the file you wish to load in. \n"
                                                       + "(Ensure to include the extension (.csv)");
        if (filename != null) {
            if (!filename.isEmpty()) {
                String loadedGame = SaveToFile.load(game, filename, "assets");
                String player = loadedGame.substring(0, 1);
            }
        }
    } 

    /**
     * Method asks for the file to save and uses Savable method save()
     * to parse it and save it with the correct format
     * @param e  representing the action event of the user pressing the button
     */
    private void saveGame(ActionEvent e) {
        String filename = JOptionPane.showInputDialog("Please enter the name of the file you wish to save too. \n"
                                                       + "(Ensure to include the extension (.csv)");
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

    /**
     * Method creates a JPanel with a grid of buttons which can be
     * pressed to set an X or an O.
     * @param tall  grid height
     * @param wide  grid width
     * @return JPanel
     */
    private JPanel buttonGrid(int tall, int wide) {
        JPanel panel = new JPanel();
        buttons = new PositionAwareButton[tall][wide];
        panel.setLayout(new GridLayout(wide,tall));
            for (int y = 0; y < wide; y++) {
                for (int x = 0; x < tall; x++) {
                    buttons[y][x] = new PositionAwareButton();
                    buttons[y][x].setAcross(x+1);
                    buttons[y][x].setDown(y+1);
                    buttons[y][x].addActionListener(e->{
                                                setSymbol(e); 
                                                checkGameOver();
                                                });
                    panel.add(buttons[y][x]);
                }
            }
            return panel;
    }

    /**
     * Method to set the correct players symbol into the button grid when
     * one of the buttons is pressed
     * @param e  representing the action event of pressing the button
     */
    private void setSymbol(ActionEvent e) {
        /* Swaps the turns after each button press */
        String symbol = game.getNextTurn();
        game.setTurn(symbol);
        symbol = game.getNextTurn();
        
        PositionAwareButton clicked = ((PositionAwareButton)(e.getSource()));

        if (game.getCell(clicked.getAcross(), clicked.getDown()) == " ") {
            if (game.takeTurn(clicked.getAcross(), clicked.getDown(), symbol)) {
                clicked.setText(game.getCell(clicked.getAcross(), clicked.getDown()));
                messageLabel("Player " + symbol + "'s Turn.");
            }
        } else {
            game.setTurn(symbol);
            symbol = game.getNextTurn();
        }
    }

    /**
     * Method to check for win conditions by working with getWinner() method and
     * outputs a pop up to indicate who won and ask if the user wants to play again
     * If they play again, it resets the grid, if not it sends them back to the GameUI menu
     */
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
        } else if (game.isDone()) {
            choice = gameOver.showConfirmDialog(null, "Tie!", "Play Again?", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.NO_OPTION) {
                menu.start();
            } else {
                game.restartGrid();
                updateBoard();
            }
        } 
    }

    /**
     * Method to update the actual text in the button grid with blank spaces
     */
    protected void updateBoard() {
        for (int y=0; y<game.getHeight(); y++){
            for (int x=0; x<game.getWidth(); x++){  
                buttons[y][x].setText(" "); 
            }
        }
    }


}
    

