package org.jn.frogger.core.elements;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import org.jn.frogger.core.FroggerGame;
import org.jn.frogger.core.data.FroggerImageCache;
import org.jn.frogger.core.sprites.FroggerTierSprite;

/**
 * Created with IntelliJ IDEA.
 * User: jere
 * Date: 7/24/12
 * Time: 10:48 PM
 * To change this template use File | Settings | File Templates.
 */
public class Turtle extends FroggerTierSprite {

    public static TextureRegion TEXTURE_1;
    public static TextureRegion TEXTURE_2;
    public static TextureRegion TEXTURE_3;

    private boolean _animated;
    private float _animationCount;

    public Turtle(FroggerGame game, float x, float y, boolean animated) {

        super(game, x, y);
        _animated = animated;

        if (Turtle.TEXTURE_1 == null) {
            Turtle.TEXTURE_1 = FroggerImageCache.getFrame("turtle_", 1);
            Turtle.TEXTURE_2 = FroggerImageCache.getFrame("turtle_", 2);
            Turtle.TEXTURE_3 = FroggerImageCache.getFrame("turtle_", 3);
        }

        setSkin(TEXTURE_1);

        this._game.screen.elements.add(this);

        _animationCount = 0.0f;
    }

    @Override
    public void update (float dt) {

        super.update(dt);

        if (_animated) {

            if (_animationCount > 220) {
                skin = Turtle.TEXTURE_1;
                _animationCount = 0f;
            } else if (_animationCount > 180) {
                visible = true;
            } else if (_animationCount > 130) {
                visible = false;
                skin = Turtle.TEXTURE_2;
            } else if (_animationCount > 105) {
                skin = Turtle.TEXTURE_3;
            } else if (_animationCount > 80) {
                skin = Turtle.TEXTURE_2;
            }

            _animationCount += 50 * dt;
        }
    }

    @Override
    public Rectangle bounds () {
        if (!visible || skin == Turtle.TEXTURE_3) return null;

        return super.bounds();
    }

}
