
package iavanish.minesweeper.CommonClasses;


import iavanish.minesweeper.PlayGame.Score;

import java.util.*;

import android.database.sqlite.SQLiteDatabase;
import android.app.Activity;
import android.content.Context;
import android.database.Cursor;


/**
 * All the DataBase read operations take place over here
 */

/**
 * Created by iavanish on 3/18/2015.
 */


public class DataBaseRead {

    private SQLiteDatabase mineSweeper;

    public DataBaseRead(Activity activity) {

        mineSweeper = activity.openOrCreateDatabase("MineSweeperDB", Context.MODE_PRIVATE, null);
        mineSweeper.execSQL("CREATE TABLE IF NOT EXISTS Player " +
                "(Name VARCHAR PRIMARY KEY, " +
                "Score VARCHAR, " +
                "Level VARCHAR);");

    }

    protected boolean checkIfUserNameAlreadyExists(String userName) {

        Cursor resultSet = mineSweeper.rawQuery("Select Name from Player", null);

        if(resultSet.getCount() > 0) {

            resultSet.moveToFirst();
            do {
                String name = resultSet.getString(0);
                if (name.equalsIgnoreCase(userName)) {
                    resultSet.close();
                    return true;
                }
                else {

                }
            }   while(resultSet.moveToNext());

        }

        resultSet.close();

        return false;

    }

    public ArrayList <Player> getPlayersFromDataBase() {

        ArrayList <Player> existingPlayers = new ArrayList <Player> ();

        Cursor resultSet = mineSweeper.rawQuery("Select * from Player", null);

        if(resultSet.getCount() > 0) {

            resultSet.moveToFirst();
            do {
                String name = resultSet.getString(0);
                int intScore = Integer.parseInt(resultSet.getString(1));
                int intLevel = Integer.parseInt(resultSet.getString(2));

                Level level = Level.LEVEL_1;
                Score score = new Score(intScore);

                if(intLevel == 1) {
                    level = Level.LEVEL_1;
                }
                else if(intLevel == 2) {
                    level = Level.LEVEL_2;
                }

                Player tempPlayer = new Player(name, score, level);

                existingPlayers.add(tempPlayer);

            }   while(resultSet.moveToNext());

        }

        resultSet.close();

        return existingPlayers;

    }

}
