
package iavanish.minesweeper.PlayGame;


/**
 * Initialize Game
 */

/**
 * Created by iavanish on 3/18/2015.
 */


public class StartNewGame {

    public StartNewGame() {

    }

    public void initializeGrid(String[] grid, String[] invisibleGrid, int n) {

        for(int i = 0; i < n; i++) {
            grid[i] = "$";
            invisibleGrid[i] = "$";
        }

    }

    public void initializeInvisibleGrid(String[] grid, String[] invisibleGrid, int n, int noOfMines) {

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
