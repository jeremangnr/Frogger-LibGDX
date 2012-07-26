package org.jn.frogger.core.sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import org.jn.frogger.core.FroggerGame;
import org.jn.frogger.core.data.FroggerImageCache;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: jere
 * Date: 7/2/12
 * Time: 11:03 PM
 * To change this template use File | Settings | File Templates.
 */
public class FroggerNumberSprite extends FroggerGameSprite {

    // the value the sprite will show
    public int value;
    private List<TextureRegion> _textures;
    private List<Sprite> _numbers;

    public FroggerNumberSprite(FroggerGame game, float x, float y, String nameRoot) {
        super(game, x, y);

        // init stuff
        this.skin = null;
        this.value = 0;
        this._textures = new ArrayList<TextureRegion>();
        this._numbers = new ArrayList<Sprite>();

        int i;
        // load up number textures
        for (i = 0; i < 10; i++) {
            this._textures.add(FroggerImageCache.getFrame(nameRoot, i));
        }

        // we'll "wrap" each texture region in a sprite
        Sprite sprite;
        for (i = 0; i < 10; i++) {
            // load number zero texture on all sprites
            sprite = new Sprite(this._textures.get(0));
            sprite.setPosition(x + i * (sprite.getRegionWidth() + 2), y);

            this._numbers.add(sprite);
        }

        // add the sprite to the screen's element array so it gets rendered in the main loop
        this._game.screen.elements.add(this);
    }

    @Override
    public void draw() {

        String string = value + "";
        int length = string.length();

        // check for invalid values
        if (length > 10) return;

        Sprite sprite;
        for (int i = 0; i < length; i++) {
            // form number sprite by adding each of its chars
            sprite = _numbers.get(i);
            sprite.setRegion(this._textures.get(Character.getNumericValue(string.charAt(i))));

            // draw
            sprite.draw(this._game.spriteBatch);
        }

    }
}
