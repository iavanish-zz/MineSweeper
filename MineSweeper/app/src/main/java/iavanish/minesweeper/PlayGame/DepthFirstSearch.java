
package iavanish.minesweeper.PlayGame;


/**
 * Utility class
 */

/**
 * Created by iavanish on 3/18/2015.
 */


public class DepthFirstSearch {

    int row;
    int column;
    int n;
    boolean[][] visited;

    public DepthFirstSearch(int n) {
        this.n = n;
        row = (int)Math.sqrt(n);
        column = (int)Math.sqrt(n);
        visited = new boolean[row][column];
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < column; j++) {
                visited[i][j] = false;
            }
        }
    }

    public void dfs(String[] grid, String[] invisibleGrid, int position) {

        int i = position / column;
        int j = position - i;
        int neighbouringMines = 0;



    }

}
