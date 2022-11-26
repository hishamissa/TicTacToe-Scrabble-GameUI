package tictactoe;

import java.util.Scanner;
import java.util.InputMismatchException;

/**
 * Class handles playing numerical tic tac toe
 * Extends BoardGame
 * Implements Saveable
 */
public class NumericalTTT extends boardgame.BoardGame implements Saveable {

    private Scanner userInput = new Scanner(System.in);
    private String parsedBoard;
    private int nextTurn;
    private int number;
    private int counter = 0;

    /**
     * Numerical TicTacToe constructor to start a game
     * @param wide  boards width (3)
     * @param tall  boards height (3)
     */
    public NumericalTTT(int wide, int tall) {
        super(wide, tall);
    }

    /** 
     * Method that facilitates the placement of an input on the board 
     * with String input. Method should be overriden 
     * to validate input prior to placing the input value.
     * @param across across index, 1 based
     * @param down  down index, 1 based
     * @param input  String input from game
     * @return boolean  returns true if input was placed false otherwise
     */
    @Override
    public boolean takeTurn(int across, int down, String input) {
        setValue(across, down, input);
        return true;
    }

    /** 
     * Method that facilitates the placement of an input on the board 
     * with integer input. Method should be overriden 
     * to validate input prior to placing the input value.
     * @param across across index, 1 based
     * @param down  down index, 1 based
     * @param input  String input from game
     * @return boolean  returns true if input was placed false otherwise
     */
    @Override
    public boolean takeTurn(int across, int down, int input) {
        setValue(across, down, input);
        return true;
    }

    /**
     * Method to check if the game is done (board is full) by checking
     * if each cell on the board is no longer storing an 88 (meaning
     * there is now a player symbol there)
     * @return boolean  true is board is full, false otherwise
     */
    @Override
    public boolean isDone() {
        if (getCell(1,1) != "88" && getCell(2,1) != "88" && getCell(3,1) != "88"
            && getCell(1,2) != "88" && getCell(2,2) != "88" && getCell(3,2) != "88"
            && getCell(1,3) != "88" && getCell(2,3) != "88" && getCell(3,3) != "88") {
               return true;
        // } else {
        //     return false;
        }
        return false;
    }

    @Override
    public String getGameStateMessage() {
        return "T";
    }

    /**
     * Method to retrieve the winner.
     * Return 1 if there is a winner
     * Return 2 if a Tie
     * Return 0 otherwise
     * @return int 
     */
    @Override
    public int getWinner() {
        int integer = 0;
        if (checkBoard("1") + checkBoard("2") + checkBoard("3") == 15) {
            integer = 1;
        } else if (checkBoard("4") + checkBoard("5") + checkBoard("6") == 15) {
            integer = 1;
        } else if (checkBoard("7") + checkBoard("8") + checkBoard("9") == 15) {
            integer = 1;
        } else if (checkBoard("1") + checkBoard("4") + checkBoard("7") == 15) {
            integer = 1;
        } else if (checkBoard("2") + checkBoard("5") + checkBoard("8") == 15) {
            integer = 1;
        } else if (checkBoard("3") + checkBoard("6") + checkBoard("9") == 15) {
            integer = 1;
        } else if (checkBoard("1") + checkBoard("5") + checkBoard("9") == 15) {
            integer = 1;
        } else if (checkBoard("3") + checkBoard("5") + checkBoard("7") == 15) {
            integer = 1;
        }
        counter++;
        if (integer == 1) {
            return 1; //win
        } else if (counter == 18) {
            return 2; //tie
        }
        return 0;
    }

    /**
     * Method to get the parsed version of the board
     * @return String 
     */
    @Override
    public String getStringToSave() {
        return parsedBoard;
    }

    /**
     * Method that takes the loaded game board and parses it
     * and creates the new game board
     * @param toLoad represnting the string that needs to be parsed into the game grid
     */
    @Override
    public void loadSavedString(String toLoad) {
        Scanner parse = new Scanner(toLoad).useDelimiter(",|\\n");
        parse.nextLine(); //skips the first line which represents the next players turn
        try {
        int number2 = Integer.parseInt(parse.next());
        takeTurn(1,1,number);
        number2 = Integer.parseInt(parse.next());
        takeTurn(2,1,number);
        number2 = Integer.parseInt(parse.next());
        takeTurn(3,1,number);
        number2 = Integer.parseInt(parse.next());
        takeTurn(1,2,number);
        number2 = Integer.parseInt(parse.next());
        takeTurn(2,2,number);
        number2 = Integer.parseInt(parse.next());
        takeTurn(3,2,number);
        number2 = Integer.parseInt(parse.next());
        takeTurn(1,3,number);
        number2 = Integer.parseInt(parse.next());
        takeTurn(2,3,number);
        number2 = Integer.parseInt(parse.next());
        takeTurn(3,3,number);
        } catch (NumberFormatException e) {
            parse.next();
        }
    }

    /**
     * Method that fills the entire grid with 88's
     */
    public void resetGrid() {
        //using "88" as a placeholder so you can visualize the board.
        takeTurn(1,1,88);
        takeTurn(2,1,88);
        takeTurn(3,1,88);
        takeTurn(1,2,88);
        takeTurn(2,2,88);
        takeTurn(3,2,88);
        takeTurn(1,3,88);
        takeTurn(2,3,88);
        takeTurn(3,3,88);
    }

    /**
     * Method that swaps the players turn
     * @param currentTurn String representing the current turn (0 or 1)
     */
    public void setTurn(int currentTurn) {
        if (currentTurn == 0) {
            nextTurn = 1;
        } else {
            nextTurn = 0;
        }
    }

    /**
     * Method to get the current turn
     * @return int  returns the current turn symbol (0 or 1)
     */
    public int getNextTurn() {
        return nextTurn;
    }

    /**
     * Method to check if the cell is available or occupied by returning the 
     * value in that cell
     * @param position  String representing the position on the board from 1 to 9
     * @return int  represnting what is stored in the cell we are searching for
     */
    public int checkBoard(String position) {
        switch(position) {
            case "1": return Integer.parseInt(getCell(1,1));
            case "2": return Integer.parseInt(getCell(2,1));
            case "3": return Integer.parseInt(getCell(3,1));
            case "4": return Integer.parseInt(getCell(1,2));
            case "5": return Integer.parseInt(getCell(2,2));
            case "6": return Integer.parseInt(getCell(3,2));
            case "7": return Integer.parseInt(getCell(1,3));
            case "8": return Integer.parseInt(getCell(2,3));
            case "9": return Integer.parseInt(getCell(3,3));
            case "S": return 88;
            default: return 0;
        }
    }

    /**
     * Method that calls either the number select for odd or even
     * numbers depending on who's turn it currently is
     * @param player
     * @param position
     * @return int the position they used
     */
    public int numberSelect(int player, int position) {
        if (player == 0) {
            number = numberSelectOdd(player);
        } else if (player == 1) {
            number = numberSelectEven(player);
        }
        return position;
    }

    /**
     * Method to get the players number they input
     * @return int returns the number
     */
    public int getNumber() {
        return number;
    }

    /**
     * Method to allow the user to enter an odd number
     * Is only called if the it is player 0's turn (Odd turn)
     * @param player
     * @return int  the number they select
     */
    public int numberSelectOdd(int player) {
        System.out.print("Now enter an odd number (1,3,5,7,9): ");
        while(true) {
            String position; 
            position = userInput.nextLine().trim();
            switch(position) {
                case "1": return 1;
                    
                case "3": return 3;
                       
                case "5": return 5;
                    
                case "7": return 7;
                    
                case "9": return 9;
                    
                default:
                    System.out.print("Invalid input. Must be 1 to 9 and ODD. Try again: ");
                    continue;                
            }
        }
    }

    /**
     * Method to allow the user to enter an even number
     * Is only called if the it is player 1's turn (Even turn)
     * @param player
     * @return int  the number they select
     */
    public int numberSelectEven(int player) {
        System.out.print("Now enter an even number (2,4,6,8): ");
        while(true) {
            String position; 
            position = userInput.nextLine().trim();
            switch(position) {
                case "2": return 2;
                    
                case "4": return 4;
                       
                case "6": return 6;
                    
                case "8": return 8;
                    
                default:
                    System.out.print("Invalid input. Must be 1 to 9 and EVEN. Try again: ");
                    continue;                
            }
        }
    }

    /**
     * Method that parses the board and creates a
     * comma seperating string representation in parsedBoard
     * @param player  representing the current turn so the game knows who played last
     * @return String storing with the parsed board
     */
    public String parser(int player) {
        parsedBoard = player + "\n"
        + getCell(1,1) + ',' + getCell(2,1) + ',' + getCell(3,1) + "\n"
        + getCell(1,2) + ',' + getCell(2,2) + ',' + getCell(3,2) + "\n"
        + getCell(1,3) + ',' + getCell(2,3) + ',' + getCell(3,3) + "\n";
        return parsedBoard;
    }

    public int selection(int value) {
        return value;
    }
    
}
