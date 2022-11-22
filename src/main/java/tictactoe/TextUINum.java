package tictactoe;

import java.util.Scanner;

public class TextUINum {

    private Scanner userInput = new Scanner(System.in);

    private NumericalTTT game = new NumericalTTT(3,3); //calls tictactoe construcer, 
                                            
    private Board board = new Board(3,3);

    public void play() {
        game.resetGrid();
        int winner = -1;
        int player = 0; //odd player = 0 | even player = 1
        while (winner == -1) {
            System.out.println("Welcome to 3x3 Numerical TicTacToe! (Even's vs Odd's!)");
            System.out.println("Odd numbers go first!");
            System.out.print("Enter a position on the board from 1 to 9: ");
            game.positionOnBoard(player);

            winner = 0;
        }
    }

    public static void main(String[] args) {
        TextUINum tester = new TextUINum();
        tester.play();
    }
}
