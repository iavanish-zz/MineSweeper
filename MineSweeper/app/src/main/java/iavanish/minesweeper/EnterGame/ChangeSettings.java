
package iavanish.minesweeper.EnterGame;


import android.app.Activity;
import android.os.Bundle;

import iavanish.minesweeper.R;


/**
 * Created by iavanish on 3/18/2015.
 */

/**
 * Allows the user to change settings of the game (at the moment, only sound settings)
 */


public class ChangeSettings extends Activity {

    public ChangeSettings() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_settings);

    }

}
