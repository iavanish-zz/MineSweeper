
package iavanish.minesweeper.EnterGame;


import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.content.Intent;

import iavanish.minesweeper.R;


/**
 * Created by iavanish on 3/18/2015.
 */

/**
 * Entry point into the app
 */


public class EnterGame extends Activity implements OnClickListener {

    private Button chooseExistingPlayerToStartTheGame;
    private Button enterNewPlayerToStartTheGame;
    private Button showHighScores;
    private Button changeSettings;
    private Button provideHelp;

    public EnterGame() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_game);

        MediaPlayer mMediaPlayer;
        mMediaPlayer = MediaPlayer.create(this, R.mipmap.soft1);
        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mMediaPlayer.setLooping(true);
        mMediaPlayer.start();

        chooseExistingPlayerToStartTheGame = (Button) findViewById(R.id.chooseExistingPlayerToStartTheGame);
        enterNewPlayerToStartTheGame = (Button) findViewById(R.id.enterNewPlayerToStartTheGame);
        showHighScores = (Button) findViewById(R.id.showHighScores);
        changeSettings = (Button) findViewById(R.id.changeSettings);
        provideHelp = (Button) findViewById(R.id.provideHelp);

        chooseExistingPlayerToStartTheGame.setOnClickListener(this);
        enterNewPlayerToStartTheGame.setOnClickListener(this);
        showHighScores.setOnClickListener(this);
        changeSettings.setOnClickListener(this);
        provideHelp.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.chooseExistingPlayerToStartTheGame) {
            Intent intent = new Intent(this, ChooseExistingPlayerToStartTheGame.class);
            startActivity(intent);
        }

        else if(v.getId() == R.id.enterNewPlayerToStartTheGame) {
            Intent intent = new Intent(this, EnterNewPlayerToStartTheGame.class);
            startActivity(intent);
        }

        else if(v.getId() == R.id.showHighScores) {
            Intent intent = new Intent(this, ShowHighScores.class);
            startActivity(intent);
        }

        else if(v.getId() == R.id.changeSettings) {
            Intent intent = new Intent(this, ChangeSettings.class);
            startActivity(intent);
        }

        else if(v.getId() == R.id.provideHelp) {

        }

        else {

        }

    }

}
