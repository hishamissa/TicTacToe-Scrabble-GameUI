package tictactoe;

import java.util.Scanner;

/**
 * Class to run the command line version of tic tac toe
 */
public class TextUI {

    private Scanner userInput = new Scanner(System.in);

    private TicTacToe game = new TicTacToe(3,3); //calls tictactoe construcer, 
                                         //which calls super to make a grid
    private Board board = new Board(3,3);

    /**
     * Method to print the opening message and ask for a new game
     * or a loaded game
     * @return String  representing the first players turn
     */
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

    /**
     * Method to play the game
     */
    public void play() {
        int playAgain = 1;
        String player = welcomeMessage();
        while(playAgain == 1) {
            int winner = -1;
            while(winner == -1) {
                System.out.print("Player " + player); 
                System.out.print(" enter a position between 1 and 9 ");
                System.out.print("(Or enter 'S' to save the current board and quit): ");
                setBoard(player);
                System.out.println(game);
                game.setTurn(player);
                player = game.getNextTurn();
                    if (game.getWinner() == 1) {
                        System.out.println("Winner is Player 'X'!\n");
                        winner = 1;
                    } else if (game.getWinner() == 2) {
                        System.out.println("Winner is Player 'O'!\n");
                        winner = 2;
                    } else if (game.getWinner() == 0) {
                        System.out.println("Game is a Tie.\n");
                        winner = 0;
                    }
            }
            playAgain = restartGame();
        }
    }

    /**
     * Uses users input to set value in grid by using the takeTurn() method
     * Ensures the userInput is valid
     * @param player 
     */
    public void setBoard(String player) {
        while(true) {
            String position; 
            position = userInput.nextLine().trim();
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
    /**
     * Method if user enters 'S' to save
     * during the game. Calls save() method to save the game
     * @param player
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

    /**
     * Method to set each value the grid to a blank space
     */
    public void resetGrid() {
        game.takeTurn(1,1," ");
        game.takeTurn(2,1," ");
        game.takeTurn(3,1," ");
        game.takeTurn(1,2," ");
        game.takeTurn(2,2," ");
        game.takeTurn(3,2," ");
        game.takeTurn(1,3," ");
        game.takeTurn(2,3," ");
        game.takeTurn(3,3," ");
    }

    /**
     * Method to give the user the option to play again after the game is over
     * @return int  1 if they play again, 2 otherwise
     */
    public int restartGame() {
        int restart = 0;
        while (true) {
            System.out.print("Would you like to play again? If so press 1.");
            System.out.print(" Otherwise press 2: ");
            int playAgain;
            playAgain = userInput.nextInt();
            switch(playAgain) {
                case 1:
                    restart = 1;
                    break;
                case 2:
                    restart = 2;
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
                    continue;
            }
            resetGrid();
            return restart;
        }
    }

    /**
     * Main method just runs play()
     * @param args
     */
    public static void main(String[] args) {
        TextUI tester = new TextUI();
        tester.play();
    }
}