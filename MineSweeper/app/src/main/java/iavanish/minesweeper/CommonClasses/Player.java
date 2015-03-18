
package iavanish.minesweeper.CommonClasses;

import iavanish.minesweeper.PlayGame.Score;


/**
 * Player class
 */

/**
 * Created by iavanish on 3/18/2015.
 */


public class Player implements Person {

    public String nameOfPlayer;

    public Score scoreOfPlayer;

    public Level levelOfPlayer;
    //  If the player has unlocked Level-2: levelOfPlayer = LEVEL_2
    //  Else: levelOfPlayer = LEVEL_1

    public Player(String nameOfPlayer, Score scoreOfPlayer, Level levelOfPlayer) {

        this.nameOfPlayer = nameOfPlayer;
        this.scoreOfPlayer = scoreOfPlayer;
        this.levelOfPlayer = levelOfPlayer;

    }

}