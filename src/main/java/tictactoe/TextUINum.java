package tictactoe;

import java.util.Scanner;

public class TextUINum {

    private Scanner userInput = new Scanner(System.in);

    private NumericalTTT game = new NumericalTTT(3,3); //calls tictactoe construcer, 
                                            
    private Board board = new Board(3,3);

    public void play() {
        game.resetGrid();
        int winner = -1;
        int position = 0;
        int num = 0;
        int player = 0; //odd player = 0 | even player = 1
        while (winner == -1) {
            System.out.println("Welcome to 3x3 Numerical TicTacToe! (Even's vs Odd's!)");
            System.out.println("Odd numbers go first!");
            System.out.print("Enter a position on the board from 1 to 9: ");
            position = game.positionOnBoard(player);
            num = game.number;
            System.out.println("Position = " + position);
            System.out.println("Number = " + num);
            setPosition(position, num);
            System.out.println(game);

            //NEXT UP: win conditions, looping game/swapping turns, loading/saving

            winner = 0;
        }
    }

    public void setPosition(int position, int num) {
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

    public static void main(String[] args) {
        TextUINum tester = new TextUINum();
        tester.play();
    }
}
