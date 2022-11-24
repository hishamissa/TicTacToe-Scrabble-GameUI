package tictactoe;

import java.util.Scanner;

public class TextUINum {

    private Scanner userInput = new Scanner(System.in);

    private NumericalTTT game = new NumericalTTT(3,3); //calls tictactoe construcer, 
                                            
    private Board board = new Board(3,3);

    public int welcomeMessage() {
        System.out.println("Welcome to 3x3 Numerical Tic Tac Toe!");
        System.out.println("1. Start a new game");
        System.out.println("2. Load a previous game");
        while (true) {
            System.out.print("Enter an option (1 or 2) to continue: ");
            String menuOption = userInput.nextLine().trim();
            switch(menuOption) {
                case "1":
                    return 0; //new game starts on X by default 
                case "2":
                   String loadedGame = SaveToFileNumTTT.load(game, "numberTicTacToesave.csv", "assets");
                   String player = loadedGame.substring(0, 1);
                   int playerNumber = Integer.parseInt(player);
                   return playerNumber; 
                default:
                    System.out.println("Invalid option. Try again.");
                    continue;
            }
        }
    }

        /*
     * Method to determine which position on the board the user
     * wants to play their number. This method will call another method
     * to actually determine what number they will choose
     * 
     *  || METHOD IS TOO MANY LINES!!! FIX LATER ||
     */
    public int positionOnBoard(int player) {
        int positionOnBoard = 0;
        while(true) {
            String position; 
            position = userInput.nextLine().trim();
            while (game.checkBoard(position) != 88) {
                System.out.print("Position is occupied. Try again: ");
                position = userInput.nextLine().trim();
            }
            switch(position) {
                case "1": game.numberSelect(player, 1);
                          positionOnBoard = 1;
                          break;
                case "2": game.numberSelect(player, 2);
                          positionOnBoard = 2;
                          break;
                case "3": game.numberSelect(player, 3);
                          positionOnBoard = 3;
                          break;
                case "4": game.numberSelect(player, 4);
                          positionOnBoard = 4;
                          break;
                case "5": game.numberSelect(player, 5);
                          positionOnBoard = 5;
                          break;
                case "6": game.numberSelect(player, 6);
                          positionOnBoard = 6;
                          break;
                case "7": game.numberSelect(player, 7);
                          positionOnBoard = 7;
                          break;
                case "8": game.numberSelect(player, 8);
                          positionOnBoard = 8;
                          break;
                case "9": game.numberSelect(player, 9);
                          positionOnBoard = 9;
                          break;
                case "S": saveCase(player);
                    break;
                default:
                    System.out.print("Invalid input. Must be 1 to 9. Try again: ");
                    continue;                
            }
            break;
        }
        return positionOnBoard;
    }

    public void saveCase(int player) {
        game.parser(player);
        String filename = "";
        System.out.print("Enter the name of the file to save the game to: ");
        filename = userInput.next().trim();
        SaveToFile.save(game, filename, "assets");
        System.out.println("Game saved!");
        System.exit(0);
    }

    public void play() {
        game.resetGrid();
        int winner = -1;
        int position = 0;
        int num = 0;
        int player = welcomeMessage(); //odd player = 0 || even player = 1
        System.out.println("\nWelcome to 3x3 Numerical TicTacToe! (Even's vs Odd's!)");
        System.out.println("Odd's are Player 0  |  Even's are Player 1");
        System.out.println("Odd numbers go first!\n");
        while (winner == -1) {
            System.out.print("Player " + player + ", enter a position on the board from 1 to 9 (Or 'S' to save and quit): ");
            position = positionOnBoard(player);
            num = game.getNumber();
            setPosition(position, num, player);
            System.out.println(game);
            if (game.getWinner() == 1) {
                System.out.println("Winner is Player " + player + "!");
                winner = 0;
                //print the player to know who actually won
            } else if (game.getWinner() == 2) {
                System.out.println("Tie!");
                winner = 0;
            }  //NEXT UP: RESTARTING GAME
            game.setTurn(player);
            player = game.getNextTurn();
        }
    }

    public void setPosition(int position, int num, int player) { 
        if (Integer.parseInt(game.getCell(1,1)) == num || Integer.parseInt(game.getCell(2,1)) == num 
            || Integer.parseInt(game.getCell(3,1)) == num || Integer.parseInt(game.getCell(1,2)) == num 
            || Integer.parseInt(game.getCell(2,2)) == num || Integer.parseInt(game.getCell(3,2)) == num 
            || Integer.parseInt(game.getCell(1,3)) == num || Integer.parseInt(game.getCell(2,3)) == num 
            || Integer.parseInt(game.getCell(3,3)) == num) {
            if (player == 0) {
                System.out.println("Cannot use the same number twice!!");
                int number = game.numberSelectOdd(0);
                setPosition(position, number, 0);
            } else if (player == 1) {
                System.out.println("Cannot use the same number twice!!");
                int number = game.numberSelectEven(1);
                setPosition(position, number, 1);
            }
        } else {
            switch(position) {
                case 1: game.takeTurn(1, 1, num);
                    break;
                case 2: game.takeTurn(2, 1, num);
                    break;
                case 3: game.takeTurn(3, 1, num);
                    break;
                case 4: game.takeTurn(1, 2, num);
                    break;
                case 5: game.takeTurn(2, 2, num);
                    break;
                case 6: game.takeTurn(3, 2, num);
                    break;
                case 7: game.takeTurn(1, 3, num);
                    break;
                case 8: game.takeTurn(2, 3, num);
                    break;
                case 9: game.takeTurn(3, 3, num);
                    break;
                default:
                    break;
            }
        }
    }

    public void repeatNumberCheck() {
        
    }

    public static void main(String[] args) {
        TextUINum tester = new TextUINum();
        tester.play();
    }
}
