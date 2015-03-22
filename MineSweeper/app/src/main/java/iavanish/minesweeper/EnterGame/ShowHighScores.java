
package iavanish.minesweeper.EnterGame;


import iavanish.minesweeper.CommonClasses.Player;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import iavanish.minesweeper.CommonClasses.DataBaseRead;
import iavanish.minesweeper.R;

import java.util.*;


/**
 * Displays the ScoreBoard
 */

/**
 * Created by iavanish on 22-Mar-15.
 */


public class ShowHighScores extends Activity {

    private TableLayout tableLayout;

    private DataBaseRead dataBaseRead;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_high_scores);

        tableLayout = (TableLayout) findViewById(R.id.tableLayout);
        dataBaseRead = new DataBaseRead(this);
        displayHighScores();

    }

    private void displayHighScores() {

        ArrayList <Player> existingPlayers = dataBaseRead.getPlayersFromDataBase();
        Iterator <Player> iterator = existingPlayers.iterator();

        TableRow tableRow;
        Button name;
        Button score;
        Button level;

        while(iterator.hasNext()) {
            Player player = iterator.next();

            tableRow = new TableRow(this);
            name = new Button(this);
            score = new Button(this);
            level = new Button(this);
            name.setText(player.nameOfPlayer);
            score.setText(String.valueOf(player.scoreOfPlayer.score));
            level.setText(String.valueOf(player.levelOfPlayer.ordinal() + 1));
            tableRow.addView(name);
            tableRow.addView(score);
            tableRow.addView(level);
            tableLayout.addView(tableRow);

        }

    }

}
