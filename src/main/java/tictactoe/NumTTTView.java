/*
 * To Do:  
 *         Loading / Saving
 *         Readability / Design
 *         Error for characters entered
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

public class NumTTTView extends JPanel {
    
    private JLabel messageLabel;
        private JLabel clearMessageLabel;
    private NumericalTTT game;
    private JPanel buttonPanel;
    private GameUI menu;
    private PositionAwareButton[][] buttons;

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
    }

    public void messageLabel(String arguement) {
        //messageLabel = new JLabel(arguement);
        messageLabel.setText(arguement);
        add(messageLabel, BorderLayout.NORTH);
    }

    public void setGameController(NumericalTTT controller) {
        this.game = controller;
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
                    buttons[y][x].addActionListener(e->{
                                                enterNumber(e);
                                                checkGameOver();
                                                });
                    panel.add(buttons[y][x]);
                }
            }
            return panel;
    }

    private void enterNumber(ActionEvent e) {
        String num = getPlayerValue();
            if (num != null) {
                if (!num.isEmpty()) {
                    int player = game.getNextTurn();
                    int number = Integer.parseInt(num);
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
            choice = gameOver.showConfirmDialog(null, "Player " + player + " is the winner!", "Play Again?", JOptionPane.YES_NO_OPTION);
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

    protected void updateBoard() {
        for (int y=0; y<game.getHeight(); y++){
            for (int x=0; x<game.getWidth(); x++){  
                buttons[y][x].setText(" "); 
            }
        }
    }

    private String getPlayerValue() {
        int playerTurn = game.getNextTurn();
        String num = "";
        if (playerTurn == 0) {
            try {
                num = JOptionPane.showInputDialog("Please input an odd value (1,3,5,7,9)");
                int number = Integer.parseInt(num);
                while (number % 2 == 0 || number > 9 || checkNumber(number)) {
                    num = JOptionPane.showInputDialog("Input must be 1,3,5,7 or 9 and the position must be empty. Try again.");
                    number = Integer.parseInt(num);
                }
            } catch (NumberFormatException exception) {
            }
        } else if (playerTurn == 1) {
            try {
                num = JOptionPane.showInputDialog("Please input an even value (2,4,6,8)");
                int number = Integer.parseInt(num);
                while (number % 2 == 1 || number > 9 || checkNumber(number)) {
                    num = JOptionPane.showInputDialog("Input must be 2,4,6 or 8 and the position must be empty. Try again.");
                    number = Integer.parseInt(num);
                }
            } catch (NumberFormatException exception) {
            }
        }
        return num;
    }

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

