package tictactoe;

import java.util.Scanner;

public class TextUI {

    private Scanner userInput = new Scanner(System.in);

    private TicTacToe game = new TicTacToe(3,3); //calls tictactoe construcer, 
                                         //which calls super to make a grid
    private Board board = new Board(3,3);

    public String welcomeMessage() {
        System.out.println("Welcome to 3x3 Tic Tac Toe!");
        System.out.println("1. Start a new game");
        System.out.println("2. Load a previous game");
        while (true) {
            System.out.print("Enter an option (1 or 2) to continue: ");
            String menuOption = userInput.nextLine().trim();
            switch(menuOption) {
                case "1":
                    return "X"; //new game starts on X by default 
                case "2":
                   String loadedGame = SaveToFile.load(game, "savedBoard.csv", "assets");
                   String player = loadedGame.substring(0, 1);
                   return player; 
                default:
                    System.out.println("Invalid option. Try again.");
                    continue;
            }
        }
    }

    public void play() {
        String player = welcomeMessage();
        while(game.getWinner() == -1) {
            System.out.print("Player " + player); 
            System.out.print(" enter a position between 1 and 9 ");
            System.out.print("(Or enter 'S' to save the current board and quit): ");
            setBoard(player);
            System.out.println(game);
            game.setTurn(player);
            player = game.getNextTurn();
        }
        if (game.getWinner() == 1) {
            System.out.println("Winner is Player 'X'!\n");
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
            String position = userInput.nextLine().trim();
            System.out.println("Position before checkBoard = " + position);
            while (game.checkBoard(position) == "X" || game.checkBoard(position) == "O") {
                System.out.print("Position is occupied. Try again: ");
                position = userInput.nextLine().trim();
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
            case "S": saveCase(player);
            default: System.out.println("Invalid input. Try again: ");
                continue; } break; } }
    /*
     * Method is user enters 'S' to save
     * during the game
     */
    public void saveCase(String player) {
        game.parser(player);
        String filename = "";
        System.out.print("Enter the name of the file to save the game to: ");
        filename = userInput.next().trim();
        SaveToFile.save(game, filename, "assets");
        System.out.println("Game saved!");
        System.exit(0);
    }

    public static void main(String[] args) {
        TextUI tester = new TextUI();
        tester.play();
    }
}