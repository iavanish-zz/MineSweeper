
package iavanish.minesweeper.EnterGame;


import iavanish.minesweeper.CommonClasses.DataBaseRead;
import iavanish.minesweeper.CommonClasses.Player;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;

import java.util.*;

import iavanish.minesweeper.R;


/**
 * Created by iavanish on 3/18/2015.
 */

/**
 * Asks the user to select an existing user-name to play the game
 */


public class ChooseExistingPlayerToStartTheGame extends Activity implements OnClickListener, SelectPlayerToStartTheGame {

    private Button player1;
    private Button player2;
    private Button player3;
    private Button player4;
    private Button player5;

    private DataBaseRead dataBaseRead;
    private ArrayList <Player> players;

    public ChooseExistingPlayerToStartTheGame() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_existing_player_to_start_the_game);

        dataBaseRead = new DataBaseRead(this);

        player1 = (Button) findViewById(R.id.Player1);
        player2 = (Button) findViewById(R.id.Player2);
        player3 = (Button) findViewById(R.id.Player3);
        player4 = (Button) findViewById(R.id.Player4);
        player5 = (Button) findViewById(R.id.Player5);

        player1.setOnClickListener(this);
        player2.setOnClickListener(this);
        player3.setOnClickListener(this);
        player4.setOnClickListener(this);
        player5.setOnClickListener(this);

        displayPlayerList();

    }

    private void displayPlayerList() {

        players = dataBaseRead.getPlayersFromDataBase();

        int n = players.size();

        if(n > 0) {
            player1.setVisibility(View.VISIBLE);
            player1.setText(players.get(0).nameOfPlayer);
        }

        if(n > 1) {
            player2.setVisibility(View.VISIBLE);
            player2.setText(players.get(1).nameOfPlayer);
        }

        if(n > 2) {
            player3.setVisibility(View.VISIBLE);
            player3.setText(players.get(2).nameOfPlayer);
        }

        if(n > 3) {
            player4.setVisibility(View.VISIBLE);
            player4.setText(players.get(3).nameOfPlayer);
        }

        if(n > 4) {
            player5.setVisibility(View.VISIBLE);
            player5.setText(players.get(4).nameOfPlayer);
        }

    }

    public void selectPlayer(Player player) {

        Intent intent = new Intent(this, SelectLevelOfGameToPlay.class);
        intent.putExtra("name", player.nameOfPlayer);
        intent.putExtra("score", player.scoreOfPlayer.score);
        intent.putExtra("level", player.levelOfPlayer.ordinal());
        startActivity(intent);

    }

    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.Player1) {
            Player newPlayer = players.get(0);
            selectPlayer(newPlayer);
        }
        else if(v.getId() == R.id.Player2) {
            Player newPlayer = players.get(1);
            selectPlayer(newPlayer);
        }
        else if(v.getId() == R.id.Player3) {
            Player newPlayer = players.get(2);
            selectPlayer(newPlayer);
        }
        else if(v.getId() == R.id.Player4) {
            Player newPlayer = players.get(3);
            selectPlayer(newPlayer);
        }
        else if(v.getId() == R.id.Player5) {
            Player newPlayer = players.get(4);
            selectPlayer(newPlayer);
        }

    }
}
