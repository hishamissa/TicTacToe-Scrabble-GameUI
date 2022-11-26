package tictactoe;
/*
 * Class to provide a GUI button component that knows what its position is in a grid.
 */

public class PositionAwareButton extends javax.swing.JButton{
    private int yPos;
    private int xPos;

    /**
     * Construcer to call super()
     */
    public PositionAwareButton(){
        super();
    }
    
    /**
     * Construcer to call super() with a string value
     * @param val
     */
    public PositionAwareButton(String val){
        super(val);
    }

    /**
     * @return int
     */
    public int getAcross(){
        return xPos;
    }

    /**
     * @return int
     */
    public int getDown(){
        return yPos;
    }

    /**
     * @param val
     */
    public void setAcross(int val){
        xPos = val;
    }

    /**
     * @param val
     */
    public void setDown(int val){
        yPos = val;
    }

}