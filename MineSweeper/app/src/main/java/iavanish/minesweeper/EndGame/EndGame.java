
package iavanish.minesweeper.EndGame;


import android.app.Activity;
import android.content.Intent;

import iavanish.minesweeper.EnterGame.EnterGame;


/**
 * To be extended by Win and Loss
 */

/**
 * Created by iavanish on 21-Mar-15.
 */


public class EndGame {

    public EndGame() {

    }

    protected void enterNewGame(Activity currentActivity) {
        Intent intent = new Intent(currentActivity, EnterGame.class);
        currentActivity.startActivity(intent);
    }

}
