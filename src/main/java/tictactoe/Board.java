package tictactoe;

public class Board extends boardgame.Grid {

    public Board(int wide, int tall) {
        super(wide, tall);
    }

    public void resetGrid() {
        emptyGrid();
    }


}
