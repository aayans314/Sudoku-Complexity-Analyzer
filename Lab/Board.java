/**
 * Author: aayan Shah
 * file name: board.java
 * LAst modified: 24oct
 * purpose: to keep track of the sudoku board
 */

import java.io.*;
public class Board {

    //2D array to store cells of my board
    private Cell[][] myBoard;

    //initialize a 9*9 board
    public Board() {
        myBoard = new Cell[9][9];
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){

                myBoard[i][j] = new Cell(i,j,0);
            }
        }
    }

    //return cell at given row and col
    public Cell get(int row, int col){
        return myBoard[row][col];
    }

    //sets the cell at given row and col to the provided value
    public void set(int row, int col, int value){
        myBoard[row][col].setValue(value);
    }
    
    //sets the locked status cell at given row and col to the provided status
    public void set(int row, int col, boolean locked){
        myBoard[row][col].setLocked(locked);
    }

    //using the given template
    public boolean read(String filename) {
    try {
      // assign to a variable of type FileReader a new FileReader object, passing filename to the constructor
      FileReader fr = new FileReader(filename);
      // assign to a variable of type BufferedReader a new BufferedReader, passing the FileReader variable to the constructor
      BufferedReader br = new BufferedReader(fr);
      
      // assign to a variable of type String line the result of calling the readLine method of your BufferedReader object.
      String line = br.readLine();
      int linenum =0;
      // start a while loop that loops while line isn't null
      while(line != null){
          // print line
	  //System.out.println( line );
          // assign to an array of Strings the result of splitting the line up by spaces (line.split("[ ]+"))
          String[] arr = line.split( "[ ]+" );
          // let's see what this array holds: 
          //System.out.println("the first item in arr: " + arr[0] + ", the second item in arr: " + arr[1]);
          // print the size of the String array (you can use .length)
          //System.out.println( arr.length );
        
          // use the line to set various Cells of this Board accordingly
          
        for(int j=0;j<9;j++){

          //  System.out.println(Integer.parseInt(arr[j]));
            
                myBoard[linenum][j].setValue(Integer.parseInt(arr[j]));
                //System.out.println(myBoard[linenum][j]);
            
        }
        linenum++;
        
          // assign to line the result of calling the readLine method of your BufferedReader object.
          line = br.readLine();
      }
      // call the close method of the BufferedReader
      br.close();
   //   for(int i=0;i<9;i++){
   //     if(i%3==0) System.out.println();
   //     for(int j=0;j<9;j++){
   //         if(j%3==0) System.out.print("   ");
   //             System.out.print(myBoard[i][j] + "  ");
   //     }
   //     System.out.println();
   // }
      return true;
    }
    catch(FileNotFoundException ex) {
      System.out.println("Board.read():: unable to open file " + filename );
    }
    catch(IOException ex) {
      System.out.println("Board.read():: error reading file " + filename);
    }

    return false;
  }

  //To print out the whole board
  public String toString(){
    
    for(int i=0;i<9;i++){
        if(i%3==0) System.out.println();
        for(int j=0;j<9;j++){
            if(j%3==0) System.out.print("   ");
                System.out.print(myBoard[i][j] + "  ");
        }
        System.out.println();
    }
    return " ";
  }

public static void main(String[] args){
    Board newBoard = new Board();
    //System.out.println(args[0]);
    newBoard.read(args[0]);
    System.out.println(newBoard);
   
        
}
  

}
