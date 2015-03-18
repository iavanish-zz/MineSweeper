
package iavanish.minesweeper.EnterGame;


import iavanish.minesweeper.CommonClasses.DataBaseWrite;
import iavanish.minesweeper.CommonClasses.Level;
import iavanish.minesweeper.CommonClasses.Player;
import iavanish.minesweeper.PlayGame.Score;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import iavanish.minesweeper.R;


/**
 * Created by iavanish on 3/18/2015.
 */

/**
 * Asks the user to enter his/her user-name so that he/she could play the game
 */


public class EnterNewPlayerToStartTheGame extends Activity implements OnClickListener, SelectPlayerToStartTheGame {

    private EditText enterPlayerName;
    private Button ok;
    private Button clear;

    private DataBaseWrite dataBaseWrite;

    public EnterNewPlayerToStartTheGame() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_new_player_to_start_the_game);

        dataBaseWrite = new DataBaseWrite(this);

        enterPlayerName = (EditText) findViewById(R.id.enterPlayerName);
        ok = (Button) findViewById(R.id.ok);
        clear = (Button) findViewById(R.id.clear);

        ok.setOnClickListener(this);
        clear.setOnClickListener(this);

    }

    private void updateDatabase(String playerName) {

        Player newPlayer = new Player(playerName, new Score(Integer.MAX_VALUE), Level.LEVEL_1);
        boolean flag = dataBaseWrite.enterNewUser(newPlayer);

        if(!flag) {
            Toast.makeText(getApplicationContext(), "Player already exists", Toast.LENGTH_SHORT).show();
            enterPlayerName.getText().clear();
        }
        else {
            selectPlayer(newPlayer);
        }

    }

    @Override
    public void selectPlayer(Player player) {

        Intent intent = new Intent(this, SelectLevelOfGameToPlay.class);
        intent.putExtra("name", player.nameOfPlayer);
        intent.putExtra("score", player.scoreOfPlayer.score);
        intent.putExtra("level", player.levelOfPlayer.ordinal());
        startActivity(intent);

    }

    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.ok) {
            String playerName = enterPlayerName.getText().toString();

            updateDatabase(playerName);
        }

        else if(v.getId() == R.id.clear) {
            enterPlayerName.getText().clear();
        }

        else {

        }

    }

}
