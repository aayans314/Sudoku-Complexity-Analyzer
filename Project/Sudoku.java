
/**
 * Author: aayan shah
 * file name: sudoku.java
 * purpose: solving the sudoku puzzle
 * last modified: 24 oct
 */

public class Sudoku {
    // fields
    private Board aBoard;
    private LandscapeDisplay ld;
    private boolean timeout=false;
    private int numLocked;
    
    

    // constructor for board iwth some pre determined values
    public Sudoku(int numLocked, boolean show) {
        this.numLocked=numLocked;
        aBoard = new Board(numLocked);
         if (show) ld = new LandscapeDisplay(aBoard);

    }
    public Sudoku(String filename) {
        this.numLocked=0;
        aBoard = new Board(filename);
         ld = new LandscapeDisplay(aBoard);

    }
    public Sudoku() {
        this.numLocked=0;
        aBoard = new Board(numLocked);
         ld = new LandscapeDisplay(aBoard);

    }

    // finding next correct value for a given cell
    public int findNextValue(Cell cell) {
        int row = cell.getRow();
        int col = cell.getCol();
        int value = cell.getValue() + 1; // Start checking from the next possible value

        while (value <= 9) { // Values range from 1 to 9
            // System.out.println("Checking value "+value);
            // System.out.println("for cell "+ cell.getValue() + " at location " +
            // cell.getRow() + " "+ cell.getCol());
            if (aBoard.validValue(row, col, value)) {
                // aBoard.set(row, col, value); // Check if value is valid for this cell
                return value;
            }
            value++;
        }
        return 0; // No valid value found, need to backtrack
    }

    // finding next cell to validate its value
    public Cell findNextCell() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (aBoard.get(i, j).getValue() == 0) {
                    int value = this.findNextValue(aBoard.get(i, j));
                    if (value == 0)
                        return null;
                    aBoard.set(i, j, value); // Find the first empty cell
                    return aBoard.get(i, j); // Return the empty cell
                }
            }
        }
        return null; // No empty cell found, the puzzle might be solved
    }

    // making a better algorithm to find the next cell
    public Cell findBetterCell() {

        Cell bestCell = null;

        int min = 9;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                Cell oneCell = aBoard.get(i, j);
                if (!oneCell.isLocked() && oneCell.getValue() == 0) {

                    int count = 0;
                    int value = 1;
                    while (value != 0) {
                        value = findNextValue(oneCell);
                        oneCell.setValue(value);
                        if (value == 0)
                            break;
                        count++;
                    }

                    if (count <= min) {

                        min = count;
                        bestCell = oneCell;
                    }
                }
            }
        }
        if (bestCell == null)
            return null;
        int nextVal = findNextValue(bestCell);
        if (nextVal == 0)
            return null;
        aBoard.set(bestCell.getRow(), bestCell.getCol(), nextVal);
        return bestCell;

    }

    // finding better cells to test on

    // solving the sudoku puzzle
    public boolean solve(int delay, int iterations) throws InterruptedException {
        LinkedList<Cell> stack = new LinkedList<>();
        int iteration = 0;
        
        while (stack.size() < aBoard.getCols() * aBoard.getRows() - aBoard.numLocked()) {

            Cell next = findBetterCell();
            
            if (delay > 0)
                Thread.sleep(delay);
            if (ld != null)
                ld.repaint();

            while (next == null && !stack.isEmpty()) {
                if (delay > 0)
                    Thread.sleep(delay);
                if (ld != null)
                    ld.repaint();

                Cell poppedCell = stack.pop();
                int result = findNextValue(poppedCell);
                poppedCell.setValue(result);

                if (poppedCell.getValue() != 0) {
                    next = poppedCell;
                }
                iteration++;
                //System.out.println(iteration+ " times ran for "+ numLocked);
               /*  if (iteration > iterations) {
                    timeout=true;
                    aBoard.finished = true;
                    return true;
                }
                    */
            }
            if (next == null) {
                aBoard.finished = true;
                return false;
            } else {
                stack.push(next);
            }

        }
        aBoard.finished = true;
        return true;
    }

    public static void main(String[] args) throws InterruptedException {
 
        /* 
        boolean showVis=true;
        int iterations=1000000;
        int boardnum=0;
        for(int i=0;i<=40;i+=5){
            int timesSolved=0;
            long totalTime=0;
            int timeOuts=0;
            for( boardnum = 0;boardnum<1000;boardnum++){
                Sudoku sudoku = new Sudoku(i, showVis);
                long startTime=System.nanoTime();
                sudoku.solve(0,iterations);
                if(sudoku.aBoard.finished){
                    if(sudoku.aBoard.validSolution()){
                        timesSolved++;
                        
                    }
                    if(sudoku.timeout){
                        timeOuts++;
                    }
                }
                totalTime += (System.nanoTime() - startTime);
                
            }
           double averageTime=totalTime/(boardnum);
            System.out.print(("Initial number of provided cells: "+i+" Times the puzzles got solved: "+timesSolved ));
            System.out.print(" Number of timeouts: "+timeOuts);
            System.out.println(" Average time for determination: "+averageTime/1000000);
        }

 


        // Interchange between findBetterCell and findNextCell on line 122 
        Sudoku sudoku = new Sudoku("breakalgorithm.txt");
        System.out.println(sudoku.aBoard);
        long time = System.nanoTime();
        sudoku.solve(0,100000000);
        time-=System.nanoTime();
        System.out.println("Time taken to solve this in ms "+Math.negateExact(time)/1000000);
        System.out.println(sudoku.aBoard);
    

        */


        Sudoku sudoku = new Sudoku("breakalgorithm.txt");
        System.out.println(sudoku.aBoard);

        sudoku.solve(0,100000000);
        System.out.println(sudoku.aBoard);
    }
}