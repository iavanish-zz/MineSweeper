
package iavanish.minesweeper.CommonClasses;


import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;


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
                            "Score INTEGER, " +
                            "Level INTEGER);");

    }

    public boolean enterNewUser(Player player) {

        String userName = player.nameOfPlayer;

        if(checkIfUserNameAlreadyExists(userName)) {
            return false;
        }

        else {
            ContentValues values = new ContentValues();
            values.put("Name", userName);
            values.put("Score", Integer.MAX_VALUE);
            values.put("Level", 1);

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
        values.put("Score", score);

        if(score < threshold) {
            values.put("Level", 1);
        }
        else {
            values.put("Level", 2);
        }

        mineSweeper.update("Player", values, "Name = ?", whereClauseArgument);

    }

}
