
/**
 * Author: aayan Shah
 * file name: board.java
 * LAst modified: 24oct
 * purpose: to keep track of the sudoku board
 */

import java.io.*;
import java.util.Random;
import java.awt.Color;
import java.awt.Graphics;

public class Board {

  // 2D array to store cells of my board
  private Cell[][] myBoard;
  private int boardSize = 9;
  protected boolean finished;

  // private Cell[][]

  // initialize a 9*9 board
  public Board() {
    myBoard = new Cell[boardSize][boardSize];
    for (int i = 0; i < boardSize; i++) {
      for (int j = 0; j < boardSize; j++) {

        myBoard[i][j] = new Cell(i, j, 0);
      }
    }
  }

  // auxillary constructor by reading from some file
  public Board(String filename) {
    this();
    this.read(filename);
  }

  // another constructor to generate random board with given num of locked cells
  public Board(int numLocked) {
    this();
    Random r = new Random();
    for (int i = 0; i < numLocked; i++) {
      int randomRow = r.nextInt(9);
      int randomCol = r.nextInt(9);
      int randomValue =r.nextInt(9) + 1;
      if (myBoard[randomRow][randomCol].getValue() == 0 && validValue(randomRow, randomCol, randomValue)) {
        this.set(randomRow, randomCol, randomValue);
        this.set(randomRow, randomCol, true);
      } else
        i--;
      
    }

  }

  // return cell at given row and col
  public Cell get(int row, int col) {
    return myBoard[row][col];
  }

  // sets the cell at given row and col to the provided value
  public void set(int row, int col, int value) {
    myBoard[row][col].setValue(value);
  }

  // sets the locked status cell at given row and col to the provided status
  public void set(int row, int col, boolean locked) {
    myBoard[row][col].setLocked(locked);
  }

  // using the given template
  public boolean read(String filename) {
    try {
      // assign to a variable of type FileReader a new FileReader object, passing
      // filename to the constructor
      FileReader fr = new FileReader(filename);
      // assign to a variable of type BufferedReader a new BufferedReader, passing the
      // FileReader variable to the constructor
      BufferedReader br = new BufferedReader(fr);

      // assign to a variable of type String line the result of calling the readLine
      // method of your BufferedReader object.
      String line = br.readLine();
      int linenum = 0;
      // start a while loop that loops while line isn't null
      while (line != null) {
        // print line
        // System.out.println( line );
        // assign to an array of Strings the result of splitting the line up by spaces
        // (line.split("[ ]+"))
        String[] arr = line.split("[ ]+");
        // let's see what this array holds:
        // System.out.println("the first item in arr: " + arr[0] + ", the second item in
        // arr: " + arr[1]);
        // print the size of the String array (you can use .length)
        // System.out.println( arr.length );

        // use the line to set various Cells of this Board accordingly

        for (int j = 0; j < 9; j++) {

          // System.out.println(Integer.parseInt(arr[j]));

          myBoard[linenum][j].setValue(Integer.parseInt(arr[j]));
          if (Integer.parseInt(arr[j]) != 0) {
            myBoard[linenum][j].setLocked(true);
          }
          // System.out.println(myBoard[linenum][j]);

        }
        linenum++;

        // assign to line the result of calling the readLine method of your
        // BufferedReader object.
        line = br.readLine();
      }
      // call the close method of the BufferedReader
      br.close();
      // for(int i=0;i<9;i++){
      // if(i%3==0) System.out.println();
      // for(int j=0;j<9;j++){
      // if(j%3==0) System.out.print(" ");
      // System.out.print(myBoard[i][j] + " ");
      // }
      // System.out.println();
      // }
      return true;
    } catch (FileNotFoundException ex) {
      System.out.println("Board.read():: unable to open file " + filename);
    } catch (IOException ex) {
      System.out.println("Board.read():: error reading file " + filename);
    }

    return false;
  }

  // To print out the whole board
  public String toString() {

    for (int i = 0; i < 9; i++) {
      if (i % 3 == 0)
        System.out.println();
      for (int j = 0; j < 9; j++) {
        if (j % 3 == 0)
          System.out.print("   ");
        System.out.print(myBoard[i][j] + "  ");
      }
      System.out.println();
    }
    return " ";
  }

  // returns the number of columns
  public int getCols() {
    return boardSize;
  }

  // returns the number of rows
  public int getRows() {
    return boardSize;
  }

  // return if the cell at given locatoin is locked
  public boolean isLocked(int r, int c) {
    return myBoard[r][c].isLocked();
  }

  // returns the number of locked cells in the board
  public int numLocked() {
    int lockedCount = 0;
    for (Cell[] rowCell : myBoard) {
      for (Cell oneCell : rowCell) {
        if (oneCell.isLocked())
          lockedCount++;
      }
    }
    return lockedCount;
  }

  // return value of cell at r,c
  public int value(int r, int c) {
    return myBoard[r][c].getValue();
  }

  // Validating the value using 3 other methods
  public boolean validValue(int row, int col, int value) {
    
    if (!validateRow(row, col, value))
      return false;
    if (!validateCol(col, row, value))
      return false;
    if (!validateNeighbourhood(row, col, value))
      return false;

    return true;

  }

  // validate the value in row
  public boolean validateRow(int row, int base, int value) {
    int count = 0;
    //System.out.println("Value i am validating "+value);
    for (int col = 0; col < boardSize; col++) {
      //System.out.println(myBoard[row][col] + " is the value of cell while i am looping");
      //System.out.println(count+ "  this is how many times i see that");
      if (myBoard[row][col].getValue() == value){

        
        //System.out.println(col);
        if(col!=base){
          if(myBoard[row][base].isLocked()) return false;
        count++;}}
      if (count > 0){
       // System.out.println(" invalid in row ");
        return false;}

    }
    
    return true;
  }

  // validate the value in column
  public boolean validateCol(int col, int base, int value) {
    int count = 0;
    for (int row = 0; row < boardSize; row++) {
      if (myBoard[row][col].getValue() == value){
        if(row!=base){
          if(myBoard[base][col].isLocked()) return false;
        count++;}}
      if (count > 0){
        //System.out.println(" invalid in column ");
        return false;}
    }
    return true;
  }

  // validate the value in neighbourhood
  //validate the value in neighbourhood (3x3 subgrid)
public boolean validateNeighbourhood(int row, int col, int value) {
  // Calculate the top-left corner of the 3x3 grid (subgrid)
  int firstCellRow = (row / 3) * 3;
  int firstCellCol = (col / 3) * 3;
  
  int count = 0;
  // Loop through the 3x3 subgrid
  for (int i = firstCellRow; i < firstCellRow + 3; i++) {
      for (int j = firstCellCol; j < firstCellCol + 3; j++) {
          if (myBoard[i][j].getValue() == value){ 
            if(i!=row && j!=col){
            if(myBoard[row][col].isLocked()) return false;
            count++;}}
          if (count > 0) return false;  // More than one occurrence of value
      }
  }
  return true;
}

    

  // testing if the board is solved
  public boolean validSolution() {
    for (int row = 0; row < boardSize; row++) {
      for (int col = 0; col < boardSize; col++) {
        int val = myBoard[row][col].getValue();
        if (val == 0 || !validValue(row,col,val)){     
           
          return false;
      }
          
      }
    }
    return true;
  }

  public boolean validBoard(){
    for (int row = 0; row < boardSize; row++) {
      for (int col = 0; col < boardSize; col++) {
        int val = myBoard[row][col].getValue();
        boolean locked = myBoard[row][col].isLocked();
        if (locked && !validValue(row,col,val)){     
           
          return false;
      }
          
      }
    }
    return true;
  }

  public void draw(Graphics g, int scale) {
    for (int i = 0; i < getRows(); i++) {
      for (int j = 0; j < getCols(); j++) {
        get(i, j).draw(g, j * scale + 5, i * scale + 10, scale);
      }
    }
    if (finished) {
      if (validSolution()) {
        g.setColor(new Color(0, 127, 0));
        g.drawChars("Hurray!".toCharArray(), 0, "Hurray!".length(), scale * 3 + 5, scale * 10 + 10);
      } else {
        g.setColor(new Color(127, 0, 0));
        g.drawChars("No solution!".toCharArray(), 0, "No Solution!".length(), scale * 3 + 5, scale * 10 + 10);
      }
    }
  }

  public static void main(String[] args) {
    Board newBoard = new Board("board3.txt");
    // System.out.println(args[0]);
    System.out.println(newBoard.validSolution());

  }

}
