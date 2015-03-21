
package iavanish.minesweeper.PlayGame;


import android.app.Activity;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

import java.util.Random;


/**
 * Used to initialize the game
 */

/**
 * Created by iavanish on 21-Mar-15.
 */


public class StartNewGame {

    private Activity currentActivity;
    private TableLayout tableLayout;
    private TableRow[] tableRows;
    private Button[][] cells;
    private ContentOfCell[][] contentOfCells;
    private StatusOfCell[][] statusOfCells;

    private Grid grid;
    private Lives lives;

    private int noOfRows;
    private int noOfColumns;
    private int noOfMines;

    public StartNewGame(Activity currentActivity, TableLayout tableLayout, TableRow[] tableRows,
                        Button[][] cells, ContentOfCell[][] contentOfCells, StatusOfCell[][] statusOfCells,
                        Grid grid, int noOfRows, int noOfColumns, int noOfMines, Lives lives) {

        this.currentActivity = currentActivity;
        this.tableLayout = tableLayout;
        this.tableRows = tableRows;
        this.cells = cells;
        this.contentOfCells = contentOfCells;
        this.statusOfCells = statusOfCells;
        this.grid = grid;
        this.noOfRows = noOfRows;
        this.noOfColumns = noOfColumns;
        this.noOfMines = noOfMines;
        this.lives = lives;

    }

    public void initializeGame() {

        for(int i = 0; i < noOfRows; i++) {

            tableRows[i] = new TableRow(currentActivity);

            for(int j = 0; j < noOfColumns; j++) {

                cells[i][j] = new Button(currentActivity);
                cells[i][j].setId(i*noOfColumns+j);
                tableRows[i].addView(cells[i][j]);

                contentOfCells[i][j] = ContentOfCell.NOTHING;
                statusOfCells[i][j] = StatusOfCell.COVERED;

            }

            tableLayout.addView(tableRows[i]);

        }

        Random random = new Random();

        int flagID = random.nextInt(noOfRows*noOfColumns-1);
        int flagRow = flagID / noOfColumns;
        int flagColumn = flagID % noOfColumns;
        contentOfCells[flagRow][flagColumn] = ContentOfCell.FLAG;

        for(int i = 0; i < noOfMines; i++) {

            int mineID = random.nextInt(noOfRows*noOfColumns-1);
            int mineRow = mineID / noOfColumns;
            int mineColumn = mineID % noOfColumns;

            if((contentOfCells[mineRow][mineColumn] == ContentOfCell.FLAG) || (contentOfCells[mineRow][mineColumn] == ContentOfCell.MINE)) {
                i--;
            }
            else {
                contentOfCells[mineRow][mineColumn] = ContentOfCell.MINE;
            }

        }

    }

}
