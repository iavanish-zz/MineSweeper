
package iavanish.minesweeper.CommonClasses;


import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Button;
import android.widget.TableRow;

import java.util.ArrayList;
import java.util.Iterator;

import iavanish.minesweeper.PlayGame.Score;


/**
 * All the DataBase write operation take place over here
 */

/**
 * Created by iavanish on 3/18/2015.
 */


public class DataBaseWrite extends DataBaseRead {

    private SQLiteDatabase mineSweeper;

    public DataBaseWrite(Activity activity) {

        super(activity);

        mineSweeper = activity.openOrCreateDatabase("MineSweeperDB", Context.MODE_PRIVATE, null);
        mineSweeper.execSQL("CREATE TABLE IF NOT EXISTS Player " +
                            "(Name VARCHAR PRIMARY KEY, " +
                            "Score VARCHAR, " +
                            "Level VARCHAR);");

    }

    public boolean enterNewUser(Player player) {

        String userName = player.nameOfPlayer;

        if(checkIfUserNameAlreadyExists(userName)) {
            return false;
        }

        else {
            ContentValues values = new ContentValues();
            values.put("Name", userName);
            values.put("Score", "1000");
            values.put("Level", "1");

            mineSweeper.insert("Player", null, values);

            return true;
        }

    }

    protected boolean checkIfUserNameAlreadyExists(String userName) {

        return super.checkIfUserNameAlreadyExists(userName);

        //  returns false or true depending on whether userName already exists in database or not

    }

    public void updateScore(String userName, int score, int threshold) {

        String[] whereClauseArgument = new String[1];
        whereClauseArgument[0] = userName;

        ContentValues values = new ContentValues();
        values.put("Name", userName);
        values.put("Score", String.valueOf(score));

        if(score <= threshold) {
            values.put("Level", "2");
        }
        else {
            values.put("Level", "1");
        }

        int intScore = 1000;

        ArrayList<Player> existingPlayers = getPlayersFromDataBase();
        Iterator <Player> iterator = existingPlayers.iterator();

        while(iterator.hasNext()) {
            Player player = iterator.next();
            if(player.nameOfPlayer.equalsIgnoreCase(userName)) {
                intScore = player.scoreOfPlayer.score;
                break;
            }
            else {

            }
        }

        if(intScore > score) {
            mineSweeper.update("Player", values, "Name = ?", whereClauseArgument);
        }
        else {

        }

    }

}
