package tictactoe;

import java.util.Scanner;

public class TicTacToe extends boardgame.BoardGame implements Saveable {

    private String nextTurn = "O";
    private String parsedBoard;
    private int counter = 0;

    public TicTacToe(int wide, int tall) {
        super(wide, tall);
    }

    /* if method in parent class is being changed must use Override */
    @Override
    public boolean takeTurn(int across, int down, String input) {
        setValue(across, down, input);
        return true;

    }
    @Override
    public boolean takeTurn(int across, int down, int input) {
        setValue(across, down, input);
        return true;
    }
    @Override
    public boolean isDone() {
        if (getCell(1,1) != " " && getCell(2,1) != " " && getCell(3,1) != " "
            && getCell(1,2) != " " && getCell(2,2) != " " && getCell(3,2) != " "
            && getCell(1,3) != " " && getCell(2,3) != " " && getCell(3,3) != " ") {
            return true;
        // } else {
        //     return false;
        }
        return false;
    }
    /*
     * Method to retrieve the winner.
     * Return 1 if X wins
     * Return 2 if O wins
     * Return 0 if Tie
     */
    @Override
    public int getWinner() {
        String winner = "";
        if (checkBoard("1").equals(checkBoard("2")) && checkBoard("2").equals(checkBoard("3"))) {
            winner = checkBoard("1");
        } else if (checkBoard("4").equals(checkBoard("5")) && checkBoard("5").equals(checkBoard("6"))) {
            winner = checkBoard("4");
        } else if (checkBoard("7").equals(checkBoard("8")) && checkBoard("8").equals(checkBoard("9"))) {
            winner = checkBoard("7");
        } else if (checkBoard("1").equals(checkBoard("4")) && checkBoard("4").equals(checkBoard("7"))) {
            winner = checkBoard("1");
        } else if (checkBoard("2").equals(checkBoard("5")) && checkBoard("5").equals(checkBoard("8"))) {
            winner = checkBoard("2");
        } else if (checkBoard("3").equals(checkBoard("6")) && checkBoard("6").equals(checkBoard("9"))) {
            winner = checkBoard("3");
        } else if (checkBoard("1").equals(checkBoard("5")) && checkBoard("5").equals(checkBoard("9"))) {
            winner = checkBoard("1");
        } else if (checkBoard("3").equals(checkBoard("5")) && checkBoard("5").equals(checkBoard("7"))) {
            winner = checkBoard("3");
        }
        counter++;
        if (winner == "X") { 
            return 1;
        } else if (winner == "O") {
            return 2;
        } else if (counter == 10) {
            return 0;
        } else {
            return -1; 
        } 
    }

    @Override
    public String getGameStateMessage() {
        return "T";
    }

    public void setTurn(String currentTurn) {
        if (currentTurn == "X") {
            nextTurn = "O";
        } else {
            nextTurn = "X";
        }
    }

    public String getNextTurn() {
        return nextTurn;
    }

    public String checkBoard(String position) {
        switch(position) {
            case "1": return getCell(1,1);
            case "2": return getCell(2,1);
            case "3": return getCell(3,1);
            case "4": return getCell(1,2);
            case "5": return getCell(2,2);
            case "6": return getCell(3,2);
            case "7": return getCell(1,3);
            case "8": return getCell(2,3);
            case "9": return getCell(3,3);
            default: return "";
        }
    }

    public void restartGrid() {
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

    /*
     * This method will parse the board and create a 
     * csv delimited string to write to a file
     */
    @Override
    public String getStringToSave() {
        return parsedBoard;
    }
    /*
     * Method that takes the loaded game board and parses it
     * and creates the new game board
     */
    @Override
    public void loadSavedString(String toLoad) {
        Scanner parse = new Scanner(toLoad).useDelimiter(",|\\n");
        parse.nextLine(); //skips the first line which represents the next players turn
        String player = parse.next();
        takeTurn(1,1,player);
        player = parse.next();
        takeTurn(2,1,player);
        player = parse.next();
        takeTurn(3,1,player);
        player = parse.next();
        takeTurn(1,2,player);
        player = parse.next();
        takeTurn(2,2,player);
        player = parse.next();
        takeTurn(3,2,player);
        player = parse.next();
        takeTurn(1,3,player);
        player = parse.next();
        takeTurn(2,3,player);
        player = parse.next();
        takeTurn(3,3,player);
    }
    /*
     * Method that parses the board and creates a
     * csv string representation in parsedBoard
     */
    public String parser(String player) {
        parsedBoard = player + "\n"
        + getCell(1,1) + ',' + getCell(2,1) + ',' + getCell(3,1) + "\n"
        + getCell(1,2) + ',' + getCell(2,2) + ',' + getCell(3,2) + "\n"
        + getCell(1,3) + ',' + getCell(2,3) + ',' + getCell(3,3) + "\n";
        return parsedBoard;
    }

}