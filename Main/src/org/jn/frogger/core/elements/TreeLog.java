package org.jn.frogger.core.elements;

import com.badlogic.gdx.math.Rectangle;
import org.jn.frogger.core.FroggerGame;
import org.jn.frogger.core.sprites.FroggerTierSprite;

/**
 * Created with IntelliJ IDEA.
 * User: jere
 * Date: 7/24/12
 * Time: 10:19 PM
 * To change this template use File | Settings | File Templates.
 */
public class TreeLog extends FroggerTierSprite {

    public TreeLog(String skinName, FroggerGame game, float x, float y) {

        super(game, x, y);

        this._game = game;
        active = true;
        visible = true;

        setSkin(skinName);

        this.x = x - skin.getRegionWidth() * 0.5f;
        this.y = y - skin.getRegionHeight() * 0.5f;

        this._game.screen.elements.add(this);
    }

    @Override
    public Rectangle bounds() {
        body = super.bounds();
        body.width = body.width * 0.9f;

        return body;
    }

}
