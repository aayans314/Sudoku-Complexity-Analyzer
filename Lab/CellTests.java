/**
 * Author : aayan shah
 * file name: celltests.java
 * prupose: testing the cell class
 * last modified: 24th oct
 */


public class CellTests {
    
    public static void main(String[] args){

        //case 1: testing constructor without locked status
        {
            //setup:
            Cell cell1 = new Cell(0, 0, 5);
            
            //verify
            System.out.println(cell1 + " != null");
            System.out.println(cell1.isLocked() + " == false");

            //test
            assert cell1!=null : "Error in Cell::Cell(int row, int col, int value)";
            assert cell1.isLocked()!=true : "Error in Cell::Cell(int row, int col, int value)";

        }
        //case 2: testing constructor with locked status
        {
            //setup:
            Cell cell1 = new Cell(0, 0, 5, true);
            
            //verify
            System.out.println(cell1 + " != null");
            System.out.println(cell1.isLocked() + " == true");

            //test
            assert cell1!=null : "Error in Cell::Cell(int row, int col, int value, boolean locked)";
            assert cell1.isLocked()!=false : "Error in Cell::Cell(int row, int col, int value, boolean locked)";

        }

        //case 3: testing getRow, getCol, getValue
        {
            //setup
            Cell cell1 = new Cell(1,2,3, true);

            //verify
            System.out.println(cell1.getRow() + " == 1");
            System.out.println(cell1.getCol() + " == 2");
            System.out.println(cell1.getValue() + " == 3");

            //test
            assert cell1.getRow() == 1 : "Error in Cell:getCol()";
            assert cell1.getCol() == 2 : "Error in Cell:get()Row";
            assert cell1.getValue() == 3 : "Error in Cell:getValue()";

        }

        //case 4: testing setValue(int newval)
        {
            //setup
            Cell cell1 = new Cell(1,2,3);
            //verify
            System.out.println(cell1.getValue() + " == 3");
            cell1.setValue(5);
            System.out.println(cell1.getValue() + " == 5");

            //test
            assert cell1.getValue() == 5 : "Error in Cell::setValue(int newval)";

        }
        //case 5: testing setLocked(boolean lock)
        {
            //setup
            Cell cell1 = new Cell(1,2,3);
            //verify
            System.out.println(cell1.isLocked() + " == false");
            cell1.setLocked(true);
            System.out.println(cell1.isLocked() + " == true");

            //test
            assert cell1.isLocked() == true : "Error in Cell::setLocked(boolean lock)";

        }

        System.out.println("Done testing Cells!");

    }
}
