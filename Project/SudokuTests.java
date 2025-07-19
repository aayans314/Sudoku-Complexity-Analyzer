/*
file name:      SudokuTests.java
Authors:        Max Bender and Ike Lage 
modified:       Muneeb Azfar Nafees
last modified:  04/08/2025

How to run:     java -ea SudokuTests
*/

public class SudokuTests {

    /**
    * Stack Tests
     * @throws InterruptedException 
    */
    public static double sudokuTests() throws InterruptedException {

        double testScore = 0. ;
        /**
            * Solve Blank Board
        */
        Sudoku game = new Sudoku( 0 , false ) ;
        if ( game.solve(0,100000000) ) {
            testScore += 2;
        } else {
            System.out.println( "Blank board not solving using brute force (solve)" );
        }

        /**
        * Solve a Board with a small number of elements
        */
        Sudoku game1 = new Sudoku( 5 , false ) ;
        if ( game1.solve(0,100000000) ) {
            testScore += 2;
        } else {
            System.out.println( "5-element board not solving using brute force (solve)" );
        }

        /**
        * Solve a Board with a more elements
        */
        Sudoku game2 = new Sudoku( 40 , false ) ;
        System.out.println( "If your program hangs here, you have an error" );
        game2.solve(0,100000000) ;
        System.out.println( "Your program isn't hanging, disregard the note above" );

        testScore += 2;

        Sudoku game3 = new Sudoku( 0 , false ) ;
        if ( game3.solve(0,100000000) ) {
            testScore += 2;
        } else {
            System.out.println( "Blank board not solving using MRV heuristic (solve2)" );
        }

        /**
        * Solve a Board with a small number of elements
        */
        Sudoku game4 = new Sudoku( 5 , false ) ;
        if ( game4.solve(0,100000000) ) {
            testScore += 2;
        } else {
            System.out.println( "5-element board not solving MRV heuristic (solve2)" );
        }

        /**
        * Solve a Board with a more elements
        */
        Sudoku game5 = new Sudoku( 40 , false ) ;
        System.out.println( "If your program hangs here, you have an error" );
        game5.solve(0,100000000) ;
        System.out.println( "Your program isn't hanging, disregard the note above" );

        testScore += 2;

        return testScore ;
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println( sudokuTests() + "/12" );
    }

}