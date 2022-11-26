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
 * Class that holds the Numerical TTT's GUI and allows for the user to play
 * Extends JPanel
 */
public class NumTTTView extends JPanel {
    
    private JLabel messageLabel;
        private JLabel clearMessageLabel;
    private NumericalTTT game;
    private JPanel buttonPanel;
    private GameUI menu;
    private PositionAwareButton[][] buttons;

    /**
     * @param wide  grids width
     * @param tall  grids height
     * @param menuApplication gameContainer to play in
     */
    public NumTTTView(int wide, int tall, GameUI menuApplication) {
        super();
        setLayout(new BorderLayout());
        JPanel subPanel = new JPanel();
        menu = menuApplication;
        setGameController(new NumericalTTT(wide, tall));
        messageLabel = new JLabel("");
        messageLabel("  Welcome to Numerical Tic Tac Toe! Odds go first.");
        game.resetGrid();
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
     * @param controller  of type NumericalTTT
     */
    public void setGameController(NumericalTTT controller) {
        this.game = controller;
    }

    /**
     * Method creates a JPanel with a grid of buttons which can be
     * pressed to set the players desired number
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
                                                enterNumber(e);
                                                checkGameOver();
                                                });
                    panel.add(buttons[y][x]);
                }
            }
            return panel;
    }

    /**
     * Method to get the value the player wished to enter and set it the
     * correct location on the grid and set the grid buttons text to match
     * Also updates the message label at the top of the grid to show the current turn
     * @param e  representing the action event caused by pressing the button
     */
    private void enterNumber(ActionEvent e) {
        String num = getPlayerValue();
        int number = 0;
            if (num != null) {
                if (!num.isEmpty()) {
                    int player = game.getNextTurn();
                    try {
                    number = Integer.parseInt(num);
                    } catch (NumberFormatException exception) {
                        JOptionPane.showMessageDialog(null, "Invalid entry! No characters.");
                        num = getPlayerValue();
                        number = Integer.parseInt(num);
                    }
                    game.setTurn(player);
                    player = game.getNextTurn();
                    PositionAwareButton clicked = ((PositionAwareButton)(e.getSource()));
                    if (Integer.parseInt(game.getCell(clicked.getAcross(), clicked.getDown())) == 88) {
                        if (game.takeTurn(clicked.getAcross(), clicked.getDown(), number)) {
                            clicked.setText(game.getCell(clicked.getAcross(), clicked.getDown()));
                            messageLabel("Player " + player + "'s Turn.");
                        }
                    } else {
                        game.setTurn(player);
                        player = game.getNextTurn();
                        JOptionPane.showMessageDialog(null, "Position is occupied!");
                    }
                }
            }
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
     * Method asks for the file to save and uses Savable method save()
     * to parse it and save it with the correct format
     * @param e  representing the action event of the user pressing the button
     */
    private void saveGame(ActionEvent e) {
        String filename = JOptionPane.showInputDialog("Please enter the name of the file you wish to save too. \n"
                                                           + "(Ensure to include the extension (.csv)");            
            if (filename != null) {
                if (!filename.isEmpty()) {
                    int player = game.getNextTurn();
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
     * Method asks for the file to load in and uses Savable method load()
     * to parse it and load it in. 
     * @param e  representing the action event of the user pressing the button
     */
    private void loadGame(ActionEvent e) {
        String filename = JOptionPane.showInputDialog("Please enter the name of the file you wish to load in. \n"
                                                       + "(Ensure to include the extension (.csv)");
        if (filename != null) {
            if (!filename.isEmpty()) {
                String loadedGame = SaveToFile.load(game, "savedBoard.csv", "assets");
                String player = loadedGame.substring(0, 1);
            }
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
        int player = game.getNextTurn();
        if (player == 0) {
            player = 1;
        } else if (player == 1) {
            player = 0;
        }
        if (game.getWinner() == 1) { //X wins
            choice = gameOver.showConfirmDialog(null, "Player " + player + " is the winner!", 
                                                "Play Again?", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.NO_OPTION) {
                menu.start();
            } else {
                game.resetGrid();
                updateBoard();
            }
        } else if (game.getWinner() == 2) {
            choice = gameOver.showConfirmDialog(null, "Tie!", "Play Again?", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.NO_OPTION) {
                menu.start();
            } else {
                game.resetGrid();
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

    /**
     * Method to get the value from the player depending on whos turn it is. 
     * Ensures the user can only enter an odd or even number (depending on whos turn)
     * @return String  the number they enter
     */
    private String getPlayerValue() {
        int playerTurn = game.getNextTurn();
        String num = "";
        if (playerTurn == 0) {
            try {
                num = JOptionPane.showInputDialog("Please input an odd value (1,3,5,7,9)");
                int number = Integer.parseInt(num);
                while (number % 2 == 0 || number > 9 || checkNumber(number)) {
                    num = JOptionPane.showInputDialog("Input must be 1,3,5,7 or 9 and" 
                                                      + "the position must be empty. Try again.");
                    number = Integer.parseInt(num);
                }
            } catch (NumberFormatException exception) {
            }
        } else if (playerTurn == 1) {
            try {
                num = JOptionPane.showInputDialog("Please input an even value (2,4,6,8)");
                int number = Integer.parseInt(num);
                while (number % 2 == 1 || number > 9 || checkNumber(number)) {
                    num = JOptionPane.showInputDialog("Input must be 2,4,6 or 8 and" 
                                                      + "the position must be empty. Try again.");
                    number = Integer.parseInt(num);
                }
            } catch (NumberFormatException exception) {
            }
        }
        return num;
    }

    /**
     * Method to check if a number has already been used elsewhere in the grid
     * by comparing the number to the contents of each cell in the grid
     * @param number  the number the user entered
     * @return boolean  returns true is the number has been used, false otherwise
     */
    private boolean checkNumber(int number) {
        if (Integer.parseInt(game.getCell(1,1)) == number || Integer.parseInt(game.getCell(2,1)) == number 
            || Integer.parseInt(game.getCell(3,1)) == number || Integer.parseInt(game.getCell(1,2)) == number 
            || Integer.parseInt(game.getCell(2,2)) == number || Integer.parseInt(game.getCell(3,2)) == number 
            || Integer.parseInt(game.getCell(1,3)) == number || Integer.parseInt(game.getCell(2,3)) == number 
            || Integer.parseInt(game.getCell(3,3)) == number) {
                return true;
        }
        return false;
    }



}

