
package iavanish.minesweeper.PlayGame;


import java.util.*;

import android.media.AudioManager;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View.OnClickListener;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import iavanish.minesweeper.EndGame.Win;
import iavanish.minesweeper.EndGame.Loss;

import iavanish.minesweeper.R;


/**
 * Created by iavanish on 21-Mar-15.
 */

/**
 * Level-1 Game
 */


public class Level1Game extends Activity implements OnClickListener {

    private String playerName;

    private Button scoreTracker;
    private Button start;
    private Button timer;

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
    private Score score;

    private StartNewGame newGame;
    private Game game;

    private long startTime = 0;
    private int seconds = 0;

    Handler timerHandler = new Handler();
    Runnable timerRunnable = new Runnable() {

        @Override
        public void run() {

            long millis = System.currentTimeMillis() - startTime;
            seconds = (int) (millis / 1000);

            timer.setText(String.format("%02d", seconds));

            score.score = seconds;

            scoreTracker.setText(String.format("%02d", score.score));

            timerHandler.postDelayed(this, 500);

        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level1_game);

        MediaPlayer mMediaPlayer;
        mMediaPlayer = MediaPlayer.create(this, R.mipmap.soft2);
        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mMediaPlayer.setLooping(true);
        mMediaPlayer.start();

        Intent intent = getIntent();
        playerName = intent.getStringExtra("name");

        scoreTracker = (Button) findViewById(R.id.scoreTracker);
        start = (Button) findViewById(R.id.start);
        timer = (Button) findViewById(R.id.timer);

        start.setOnClickListener(this);

        tableLayout = (TableLayout) findViewById(R.id.tableLayout);
        tableRows = new TableRow[noOfRows];
        cells = new Button[noOfRows][noOfColumns];
        contentOfCells = new ContentOfCell[noOfRows][noOfColumns];
        statusOfCells = new StatusOfCell[noOfRows][noOfColumns];

        grid = new Grid(noOfRows*noOfColumns, 0);
        lives = new Lives(1);
        score = new Score(0);

        newGame = new StartNewGame(this, tableLayout, tableRows, cells, contentOfCells, statusOfCells, grid, noOfRows, noOfColumns, noOfMines, lives);
        newGame.initializeGame();

        for(int i = 0; i < noOfRows; i++) {
            for(int j = 0; j < noOfColumns; j++) {
                cells[i][j].setOnClickListener(this);
            }
        }

        game = new Game();

        startTime = System.currentTimeMillis();
        timerHandler.postDelayed(timerRunnable, 0);

    }

    @Override
    public void onClick(View v) {

        timeOut();

        if (v.getId() == R.id.start) {
            Intent intent = new Intent(this, Level1Game.class);
            intent.putExtra("name", playerName);
            startActivity(intent);
        }

        else {
            int clickedCell = v.getId();
            int row = clickedCell / noOfColumns;
            int column = clickedCell % noOfColumns;

            if (statusOfCells[row][column] == StatusOfCell.UNCOVERED) {

            } else {

                boolean gameOver = game.cellClickedEvent(this, cells, contentOfCells, statusOfCells,
                        grid, noOfRows, noOfColumns, noOfMines, lives, row, column);

                if (gameOver) {
                    Toast.makeText(this, "YOU WON", Toast.LENGTH_LONG).show();


                    Win win = new Win();
                    win.updateScore(this, playerName, score);
                    win.enterNewGame(this);
                }

            }

        }

    }

    public void timeOut() {

        if(score.score >= 60) {

            Toast.makeText(this, "TIME OVER. YOU LOST", Toast.LENGTH_LONG).show();


            Loss loss = new Loss();
            loss.enterNewGame(this);

        }

    }

}
