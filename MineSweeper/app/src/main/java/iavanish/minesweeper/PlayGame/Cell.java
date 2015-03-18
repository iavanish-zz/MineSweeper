
package iavanish.minesweeper.PlayGame;


/**
 * Cell of the grid
 */

/**
 * Created by iavanish on 3/18/2015.
 */


public class Cell {

    public StatusOfCell statusOfCell;
    public ContentOfCell contentOfCell;
    public Cell[] neighbours;
    public int noOfNeighbouringMines;

    public Cell() {

        neighbours = new Cell[8];

    }

}
