package org.jn.frogger.core.elements;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import org.jn.frogger.core.FroggerGame;
import org.jn.frogger.core.data.FroggerImageCache;
import org.jn.frogger.core.sprites.FroggerTierSprite;

/**
 * Created with IntelliJ IDEA.
 * User: jere
 * Date: 7/25/12
 * Time: 12:23 AM
 * To change this template use File | Settings | File Templates.
 */
public class Crocodile extends FroggerTierSprite {

    public static TextureRegion TEXTURE_1;
    public static TextureRegion TEXTURE_2;

    private float _animationCount;

    public Crocodile(FroggerGame game, float x, float y) {

        super(game, x, y);

        _animationCount = 0.0f;

        if (Crocodile.TEXTURE_1 == null) {
            TEXTURE_1 = FroggerImageCache.getFrame("croc_", 1);
            TEXTURE_2 = FroggerImageCache.getFrame("croc_", 2);
        }

        setSkin(TEXTURE_1);

        body = new Rectangle(0, 0, width, height);

        this._game.screen.elements.add(this);

    }

    @Override
    public void update (float dt) {

        super.update(dt);

        if (_animationCount > 120) {
            skin = Crocodile.TEXTURE_1;
            body.width = width;
            _animationCount = 0f;
        } else if (_animationCount > 60) {
            skin = Crocodile.TEXTURE_2;
            body.width = width*0.4f;
        }
        _animationCount += 20 * dt;

    }

    @Override
    public Rectangle bounds () {
        body.x = x - body.width*0.5f;
        body.y = y - body.height*0.5f;

        return body;
    }
}
