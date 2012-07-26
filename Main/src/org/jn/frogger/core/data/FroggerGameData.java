package org.jn.frogger.core.data;

import org.jn.frogger.core.FroggerGame;

/**
 * Created with IntelliJ IDEA.
 * User: jere
 * Date: 6/28/12
 * Time: 9:22 PM
 *
 * This class encapsulates some of the basic game statistics, like score, lives, level, game speed
 */
public class FroggerGameData {

    public static final int POINTS_JUMP = 10;
    public static final int POINTS_TARGET = 100;
    public static final int POINTS_FLY = 100;
    public static final int POINTS_BONUS = 200;

    // game info
    public int score = 0;
    public int level = 1;
    public int lives = 3;

    public int gameMode;
    public int targetsReached = 0;

    public float tierSpeed1 = 1.0f;
    public float tierSpeed2 = 1.0f;

    public FroggerGameData(FroggerGame game) {
        this.gameMode = FroggerGame.GAME_STATE_PAUSE;
    }

    public void reset () {
        score  = 0;
        level = 1;
        lives = 3;
        tierSpeed1 = 1.0f;
        tierSpeed2 = 1.0f;
        targetsReached = 0;
    }

}
