
package iavanish.minesweeper.PlayGame;


import android.app.Activity;
import android.os.Bundle;
import android.view.View.OnClickListener;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.TableLayout;
import android.widget.TableRow;

import iavanish.minesweeper.EndGame.Win;

import iavanish.minesweeper.R;


/**
 * Created by iavanish on 21-Mar-15.
 */

/**
 * Level-1 Game
 */


public class Level1Game extends Activity implements OnClickListener {

    private String playerName;
    private Score score;

    private TableLayout tableLayout;
    private TableRow[] tableRows;
    private Button[][] cells;
    private ContentOfCell[][] contentOfCells;
    private StatusOfCell[][] statusOfCells;

    private static final int noOfRows = 8;
    private static final int noOfColumns = 8;
    private static final int noOfMines = 8;

    private Grid grid;
    private Lives lives;

    private StartNewGame newGame;
    private Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level1_game);

        Intent intent = getIntent();
        playerName = intent.getStringExtra("name");

        tableLayout = (TableLayout) findViewById(R.id.tableLayout);
        tableRows = new TableRow[noOfRows];
        cells = new Button[noOfRows][noOfColumns];
        contentOfCells = new ContentOfCell[noOfRows][noOfColumns];
        statusOfCells = new StatusOfCell[noOfRows][noOfColumns];

        grid = new Grid(noOfRows*noOfColumns, 0);
        lives = new Lives(1);
        score = new Score(100);

        newGame = new StartNewGame(this, tableLayout, tableRows, cells, contentOfCells, statusOfCells, grid, noOfRows, noOfColumns, noOfMines, lives);
        newGame.initializeGame();

        for(int i = 0; i < noOfRows; i++) {
            for(int j = 0; j < noOfColumns; j++) {
                cells[i][j].setOnClickListener(this);
            }
        }

        game = new Game();

    }

    @Override
    public void onClick(View v) {

        int clickedCell = v.getId();
        int row = clickedCell / noOfColumns;
        int column = clickedCell % noOfColumns;

        if(statusOfCells[row][column] == StatusOfCell.UNCOVERED) {

        }

        else {

            boolean gameOver = game.cellClickedEvent(this, cells, contentOfCells, statusOfCells,
                    grid, noOfRows, noOfColumns, noOfMines, lives, row, column);

            if(gameOver) {
                Win win = new Win();
                win.updateScore(this, playerName, score);
                win.enterNewGame(this);
            }

        }

    }

}
