
package iavanish.minesweeper.CommonClasses;

import iavanish.minesweeper.PlayGame.Score;


/**
 * Interface to be implemented by Player Class
 */

/**
 * Created by iavanish on 3/18/2015.
 */


public interface Person {

    public String nameOfPlayer = null;

    public Score scoreOfPlayer = null;

    public Level levelOfPlayer = Level.LEVEL_1;

}