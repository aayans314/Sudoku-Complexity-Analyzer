/**
 * Author : aayan shah
 * filename: cell.java
 * purpose: store a single cell of the board
 * last modified: 24 oct 2024
 */
import java.awt.Graphics;
import java.awt.Color;


public class Cell {
    // basic fields
    private int rowIndex;
    private int colIndex;
    private int value;
    private boolean isLocked;

    // constructor without locked data
    public Cell(int row, int col, int value) {
        this.colIndex = col;
        this.rowIndex = row;
        this.value = value;
        this.isLocked = false;

    }

    // constructor with locked data
    public Cell(int row, int col, int value, boolean locked) {
        this.colIndex = col;
        this.rowIndex = row;
        this.value = value;
        this.isLocked = locked;
    }
    
//return row
    public int getRow() {
        return this.rowIndex;
    }
//return column
    public int getCol() {
        return this.colIndex;
    }
    //return value
    public int getValue(){
        return this.value;
    }

    //set cell value
    public void setValue(int newval){
        this.value=newval;
    }
    //return locked status
    public boolean isLocked(){
        return this.isLocked;
    }
    //set new locked status
    public void setLocked(boolean lock){
        this.isLocked=lock;
    }
    //toStirng implementaiton
    public String toString(){
        return this.value + "";
    }

    public void draw(Graphics g, int x, int y, int scale){
        char toDraw = (char) ((int) '0' + getValue());
        g.setColor(isLocked()? Color.BLUE : Color.RED);
        g.drawChars(new char[] {toDraw}, 0, 1, x, y);
    }
    
}

