package org.jn.frogger.core.elements;

import org.jn.frogger.core.FroggerGame;
import org.jn.frogger.core.sprites.FroggerTierSprite;

/**
 * Created with IntelliJ IDEA.
 * User: jere
 * Date: 7/24/12
 * Time: 10:12 PM
 * To change this template use File | Settings | File Templates.
 */
public class Vehicle extends FroggerTierSprite {

    public Vehicle(String skinName, int skinIndex, FroggerGame game, float x, float y) {

        super(game, x, y);

        this._game = game;
        active = true;
        visible = true;

        setSkin(skinName, skinIndex);

        this._game.screen.elements.add(this);
    }

}
