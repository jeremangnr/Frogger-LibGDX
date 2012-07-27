package org.jn.frogger.core.elements;

import org.jn.frogger.core.FroggerGame;
import org.jn.frogger.core.sprites.FroggerGameSprite;
import org.jn.frogger.core.sprites.FroggerNumberSprite;

/**
 * Created with IntelliJ IDEA.
 * User: jere
 * Date: 7/26/12
 * Time: 11:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class FroggerLevel extends FroggerNumberSprite {

    public FroggerLevel(FroggerGame game, float x, float y, String nameRoot) {
        super(game, x, y, nameRoot);
    }

    @Override
    public void draw() {
        value = _game.gameData.level;
        super.draw();
    }
}
