package tictactoe;

import java.util.Scanner;
import java.util.InputMismatchException;

public class NumericalTTT extends boardgame.BoardGame implements Saveable {

    private Scanner userInput = new Scanner(System.in);
    private String parsedBoard;
    private int nextTurn;
    private int number;
    private int counter = 0;

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


    @Override
    public String getStringToSave() {
        return parsedBoard;
    }

    @Override
    public void loadSavedString(String toLoad) {
        Scanner parse = new Scanner(toLoad).useDelimiter(",|\\n");
        parse.nextLine(); //skips the first line which represents the next players turn
        try {
        int number = Integer.parseInt(parse.next());
        takeTurn(1,1,number);
        number = Integer.parseInt(parse.next());
        takeTurn(2,1,number);
        number = Integer.parseInt(parse.next());
        takeTurn(3,1,number);
        number = Integer.parseInt(parse.next());
        takeTurn(1,2,number);
        number = Integer.parseInt(parse.next());
        takeTurn(2,2,number);
        number = Integer.parseInt(parse.next());
        takeTurn(3,2,number);
        number = Integer.parseInt(parse.next());
        takeTurn(1,3,number);
        number = Integer.parseInt(parse.next());
        takeTurn(2,3,number);
        number = Integer.parseInt(parse.next());
        takeTurn(3,3,number);
        } catch (NumberFormatException e) {
            parse.next();
        }
    }

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

    public int numberSelect(int player, int position) {
        if (player == 0) {
            number = numberSelectOdd(player);
        } else if (player == 1) {
            number = numberSelectEven(player);
        }
        return position;
    }

    public int getNumber() {
        return number;
    }

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
