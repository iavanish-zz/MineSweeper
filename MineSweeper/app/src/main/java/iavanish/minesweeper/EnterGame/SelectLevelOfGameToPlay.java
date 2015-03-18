
package iavanish.minesweeper.EnterGame;


import iavanish.minesweeper.PlayGame.Level1Game;
import iavanish.minesweeper.PlayGame.Level2Game;
import iavanish.minesweeper.PlayGame.Score;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;

import iavanish.minesweeper.CommonClasses.Level;
import iavanish.minesweeper.CommonClasses.Player;
import iavanish.minesweeper.R;


/**
 * Created by iavanish on 3/18/2015.
 */

/**
 * Gives the option of playing Level-1 or Level-2 to the given player,
 * depending on whether the player had earlier un-locked Level-2 or not
 */


public class SelectLevelOfGameToPlay extends Activity implements OnClickListener {

    private Player player;

    private Button level1;
    private Button level2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_level_of_game_to_play);

        level1 = (Button) findViewById(R.id.level1);
        level2 = (Button) findViewById(R.id.level2);

        level1.setOnClickListener(this);
        level2.setOnClickListener(this);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        int score = intent.getIntExtra("score", Integer.MAX_VALUE);
        int intLevel = intent.getIntExtra("level", 0);
        Level level;
        if(intLevel == 0) {
            level = Level.LEVEL_1;
        }
        else {
            level = Level.LEVEL_2;
        }
        player = new Player(name, new Score(score), level);

    }

    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.level1) {
            Intent intent = new Intent(this, Level1Game.class);
            intent.putExtra("name", player.nameOfPlayer);
            startActivity(intent);
        }
        else if(v.getId() == R.id.level2) {
            Intent intent = new Intent(this, Level2Game.class);
            intent.putExtra("name", player.nameOfPlayer);
            startActivity(intent);
        }
        else {

        }

    }
}
