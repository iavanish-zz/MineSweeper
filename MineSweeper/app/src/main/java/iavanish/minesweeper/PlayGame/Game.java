
package iavanish.minesweeper.PlayGame;


/**
 * Utility class
 */

/**
 * Created by iavanish on 3/18/2015.
 */


public class Game {

    public Game() {

    }

    public void nonMineClickedEvent(String[] grid, String[] invisibleGrid, int n, int position) {
        grid[position] = " ";
        uncoverNeighboursInCaseNoNeighbourContainsMine(grid, invisibleGrid, n, position);
    }

    public void flaggedCellClickedEvent(String[] grid, String[] invisibleGrid, int n, int position) {

    }

    public void uncoverNeighboursInCaseNoNeighbourContainsMine(String[] grid, String[] invisibleGrid, int n, int position) {

    }

    public boolean allCellsUncovered(String[] grid, String[] invisibleGrid, int n) {

        for(int i = 0; i < n; i++) {
            if(!(grid[i].equals(" ")) && !(invisibleGrid[i].equals("M"))) {
                return false;
            }
        }

        return true;

    }

    public void uncoverAllCells(String[] grid, int n) {

        for(int i = 0; i < n; i++) {
            if(grid[i].equals("$")) {
                grid[i] = "M";
            }
        }

    }

}
