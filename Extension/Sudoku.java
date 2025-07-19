
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
    public Sudoku(int numLocked) {
        this.numLocked=numLocked;
        aBoard = new Board(numLocked);
         ld = new LandscapeDisplay(aBoard, aBoard.getCols());

    }
    //Consructor for taking size of the board
    public Sudoku(int size, int numLocked) {
        if(Math.sqrt(size)*Math.sqrt(size)!=size){
            System.err.println("Invalid input ");
            System.exit(0);
            
        }
        this.numLocked=numLocked;
        aBoard = new Board(size, numLocked);
        ld = new LandscapeDisplay(aBoard, aBoard.getCols());
    }
    public Sudoku(String filename) {
        this.numLocked=0;
        aBoard = new Board(filename);
        ld = new LandscapeDisplay(aBoard, aBoard.getCols());
    }
    public Sudoku() {
        this.numLocked=0;
        aBoard = new Board(numLocked);
        ld = new LandscapeDisplay(aBoard, aBoard.getCols());
    }

    // finding next correct value for a given cell
    public int findNextValue(Cell cell) {
        int row = cell.getRow();
        int col = cell.getCol();
        int value = cell.getValue() + 1; // Start checking from the next possible value

        while (value <= aBoard.getCols()) { // Values range from 1 to 9
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
        for (int i = 0; i < aBoard.getRows(); i++) {
            for (int j = 0; j < aBoard.getCols(); j++) {
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

        int min = aBoard.getCols();
        for (int i = 0; i < aBoard.getRows(); i++) {
            for (int j = 0; j < aBoard.getCols(); j++) {
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
                if (iteration > iterations) {
                    timeout=true;
                    aBoard.finished = true;
                    return true;
                }
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
        if(args.length == 0){
            System.out.println("First parameter to select boardsize (enter square), second to have number of locked cells ");
            System.exit(0);
        }
        int boardsize = Integer.parseInt(args[0]);
        int numLocked = Integer.parseInt(args[1]);
        

        Sudoku sudoku = new Sudoku(boardsize,numLocked);
        System.out.println(sudoku.aBoard);
        sudoku.solve(0,1000000);
        System.out.println(sudoku.aBoard);
       

        
    }
}