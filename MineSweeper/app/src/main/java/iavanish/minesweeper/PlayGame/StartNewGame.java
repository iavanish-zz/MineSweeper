
package iavanish.minesweeper.PlayGame;


/**
 * Utility class
 */

/**
 * Created by iavanish on 3/18/2015.
 */


public class Game {

    String[] grid;
    String[] invisibleGrid;
    int n;

    public Game(String[] grid, String[] invisibleGrid, int n) {
        this.grid = grid;
        this.invisibleGrid = invisibleGrid;
        this.n = n;
    }

    public void initializeGrid() {

        for(int i = 0; i < n; i++) {
            grid[i] = "$";
            invisibleGrid[i] = "$";
        }

    }

    public void initializeInvisibleGrid(int noOfMines) {

        for(int i = 0; i < noOfMines; i++) {
            int x = (int)Math.random();
            x %= n;
            if(invisibleGrid[x].equals("M")) {
                i--;
                continue;
            }
            else {
                invisibleGrid[x] = "M";
            }
        }

        while(true) {
            int x = (int)Math.random();
            if(!invisibleGrid[x].equals("M")) {
                invisibleGrid[x] = "F";
                break;
            }
        }

    }

}
