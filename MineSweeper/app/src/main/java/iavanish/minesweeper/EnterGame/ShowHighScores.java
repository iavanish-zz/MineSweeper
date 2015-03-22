
package iavanish.minesweeper.EnterGame;


import iavanish.minesweeper.CommonClasses.Player;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;
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

    private LinearLayout showHighScores;

    private DataBaseRead dataBaseRead;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_high_scores);

        showHighScores = (LinearLayout) findViewById(R.id.showHighScores);
        dataBaseRead = new DataBaseRead(this);
        displayHighScores();

    }

    private void displayHighScores() {

        ArrayList <Player> existingPlayers = dataBaseRead.getPlayersFromDataBase();
        Iterator <Player> iterator = existingPlayers.iterator();

        TextView heading = new TextView(this);
        heading.setText("Name" + "\t" + "Score" + "\t" + "Level");
        showHighScores.addView(heading);

        while(iterator.hasNext()) {
            Player player = iterator.next();
            TextView displayPlayer = new TextView(this);
            String data = player.nameOfPlayer + "\t" + player.scoreOfPlayer + "\t" + player.levelOfPlayer;
            displayPlayer.setText(data);
            showHighScores.addView(displayPlayer);
        }

    }

}
