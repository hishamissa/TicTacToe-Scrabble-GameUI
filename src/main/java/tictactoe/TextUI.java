package tictactoe;

import java.util.Scanner;

public class TextUI {

    private Scanner userInput = new Scanner(System.in);

    private TicTacToe game = new TicTacToe(3,3); //calls tictactoe construcer, 
                                         //which calls super to make a grid
    private Board board = new Board(3,3);

    public void play() {
        String player = "X";
        while(game.getWinner() == -1) {
            System.out.print("Player " + player); 
            System.out.print(" enter a position between 1 and 9: ");
            setBoard(player);
            System.out.println(game);
            game.setTurn(player);
            player = game.getNextTurn();
        }
        if (game.getWinner() == 1) {
            System.out.println("Winner is Player 'X'!\n");
            
            game.parser(player);
            SaveToFile.save(game, "savedBoard.csv", "assets");
        } else if (game.getWinner() == 2) {
            System.out.println("Winner is Player 'O'!\n");
        } else if (game.getWinner() == 0) {
            System.out.println("Game is a Tie.\n");
        }
    }

    /*
     * Uses users input to set value in grid
     */
    public void setBoard(String player) {
        while(true) {
            String position;
            position = userInput.next();
            while (game.checkBoard(position) == "X" || game.checkBoard(position) == "O") {
                System.out.println("Position is occupied. Try again: ");
                position = userInput.next();
            }
        switch(position) { 
            case "1": game.takeTurn(1,1,player);
                break;
            case "2": game.takeTurn(2,1,player);
                break;
            case "3": game.takeTurn(3,1,player);
                break;
            case "4": game.takeTurn(1,2,player);
                break;
            case "5": game.takeTurn(2,2,player);
                break;
            case "6": game.takeTurn(3,2,player);
                break;
            case "7": game.takeTurn(1,3,player);
                break;
            case "8": game.takeTurn(2,3,player);
                break;
            case "9": game.takeTurn(3,3,player);
                break;
            default: System.out.println("Invalid input. Try again: ");
                continue; } break; } }

    public static void main(String[] args) {
        TextUI tester = new TextUI();
        tester.play();
    }
}