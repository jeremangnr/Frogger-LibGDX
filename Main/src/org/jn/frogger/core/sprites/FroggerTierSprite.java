package org.jn.frogger.core.sprites;

import org.jn.frogger.core.FroggerGame;

/**
 * Created with IntelliJ IDEA.
 * User: jere
 * Date: 7/3/12
 * Time: 10:48 PM
 *
 *
 * Represents a "line" of obstacles in the game
 */
public class FroggerTierSprite extends FroggerMovingSprite {

    public float distance;

    public FroggerTierSprite(FroggerGame game, float x, float y) {
        super(game, x, y);
    }

}
