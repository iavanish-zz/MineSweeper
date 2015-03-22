
package iavanish.minesweeper.EndGame;


import android.app.Activity;

import iavanish.minesweeper.PlayGame.Score;
import iavanish.minesweeper.CommonClasses.DataBaseWrite;


/**
 * Used when the player has successfully played the game
 */

/**
 * Created by iavanish on 21-Mar-15.
 */


public class Win extends EndGame {

    public Win() {

    }

    public void updateScore(Activity currentActivity, String playerName, Score score) {
        DataBaseWrite dataBaseWrite = new DataBaseWrite(currentActivity);
        dataBaseWrite.updateScore(playerName, score.score, 50);
    }

    public void enterNewGame(Activity currentActivity) {
        super.enterNewGame(currentActivity);
    }

}
