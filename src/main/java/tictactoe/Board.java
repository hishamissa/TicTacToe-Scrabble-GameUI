package tictactoe;

public class Board extends boardgame.Grid {

    public Board(int wide, int tall) {
        super(wide, tall);
    }

    private char[] board = {'1','|','2','|','3','\n',
                            '-','+','-','+','-','\n',
                            '4','|','5','|','6','\n',
                            '-','+','-','+','-','\n',
                            '7','|','8','|','9','\n'};


}
