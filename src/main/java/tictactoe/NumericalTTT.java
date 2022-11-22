package tictactoe;

import java.util.Scanner;

public class NumericalTTT extends boardgame.BoardGame implements Saveable {

    private Scanner userInput = new Scanner(System.in);
    private String parsedBoard;
    private int nextTurn;

    public NumericalTTT(int wide, int tall) {
        super(wide, tall);
    }

    @Override
    public boolean takeTurn(int across, int down, String input) {
        setValue(across, down, input);
        return true;
    }

    @Override
    public boolean takeTurn(int across, int down, int input) {
        setValue(across, down, input);
        return false;
    }
    @Override
    public boolean isDone() {
        return false;
    }

    @Override
    public String getGameStateMessage() {
        return "T";
    }

    @Override
    public int getWinner() {
        return 0;
    }


    @Override
    public String getStringToSave() {
        return parsedBoard;
    }

    @Override
    public void loadSavedString(String toLoad) {
    }

    public void resetGrid() {
        takeTurn(1,1," ");
        takeTurn(2,1," ");
        takeTurn(3,1," ");
        takeTurn(1,2," ");
        takeTurn(2,2," ");
        takeTurn(3,2," ");
        takeTurn(1,3," ");
        takeTurn(2,3," ");
        takeTurn(3,3," ");
    }

    public void setTurn(int currentTurn) {
        if (currentTurn == 0) {
            nextTurn = 1;
        } else {
            nextTurn = 0;
        }
    }

    public int getNextTurn() {
        return nextTurn;
    }

    /*
     * Method to determine which position on the board the user
     * wants to play their number. This method will call another method
     * to actually determine what number they will choose
     */
    public void positionOnBoard(int player) {
        while(true) {
            String position; 
            position = userInput.nextLine().trim();
            switch(position) {
                case "1": numberSelect(player, 1);
                    break;
                case "2": numberSelect(player, 2);
                    break;   
                case "3": numberSelect(player, 3);
                    break;
                case "4": numberSelect(player, 4);
                    break;
                case "5": numberSelect(player, 5);
                    break;
                case "6": numberSelect(player, 6);
                    break;
                case "7": numberSelect(player, 7);
                    break;
                case "8": numberSelect(player, 8);
                    break;
                case "9": numberSelect(player, 9);
                    break;
                default:
                    System.out.println("Invalid input. Must be 1 to 9. Try again.");
                    continue;                
            }
            break;
        }
    }

    public void numberSelect(int player, int position) {
        if (player == 0) {
            numberSelectOdd(player);
        } else if (player == 1) {
            numberSelectEven(player);
        }
    }

    public void numberSelectOdd(int player) {
        System.out.print("Now enter an odd number (1,3,5,7,9): ");
        while(true) {
            String position; 
            position = userInput.nextLine().trim();
            switch(position) {
                case "1": 
                    break;
                case "3": 
                    break;   
                case "5": 
                    break;
                case "7": 
                    break;
                case "9": 
                    break;
                default:
                    System.out.print("Invalid input. Must be 1 to 9 and ODD. Try again: ");
                    continue;                
            }
            break;
        }
    }

    public void numberSelectEven(int player) {
        System.out.print("Now enter an even number (2,4,6,8): ");
        while(true) {
            String position; 
            position = userInput.nextLine().trim();
            switch(position) {
                case "2": 
                    break;
                case "4": 
                    break;   
                case "6": 
                    break;
                case "8": 
                    break;
                default:
                    System.out.print("Invalid input. Must be 1 to 9 and EVEN. Try again: ");
                    continue;                
            }
            break;
        }
    }
    
}
