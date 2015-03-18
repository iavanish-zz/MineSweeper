
package iavanish.minesweeper.PlayGame;


/**
 * The GRID
 */

/**
 * Created by iavanish on 3/18/2015.
 */


public class Grid {

    public int noOfRows;
    public int noOfColumns;
    public int noOfCells;
    public int noOfMines;
    public Cell[] grid;

    public Grid(int noOfRows, int noOfColumns, int noOfMines) {

        this.noOfRows = noOfRows;
        this.noOfColumns = noOfColumns;
        this.noOfCells = this.noOfRows * this.noOfColumns;
        this.noOfMines = noOfMines;
        this.grid = new Cell[this.noOfCells];
        initializeGrid(this.noOfMines);

    }

    private void initializeGrid(int noOfMines) {

    }

}
