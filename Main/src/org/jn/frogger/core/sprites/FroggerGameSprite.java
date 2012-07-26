package org.jn.frogger.core.sprites;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

import org.jn.frogger.core.FroggerGame;
import org.jn.frogger.core.data.FroggerImageCache;

/**
 * Created with IntelliJ IDEA.
 * User: jere
 * Date: 6/28/12
 * Time: 10:23 PM
 * To change this template use File | Settings | File Templates.
 */
public class FroggerGameSprite {

    // state
    public boolean active;
    public boolean visible;

    // position and size
    public float x = 0;
    public float y = 0;
    public int width = 0;
    public int height = 0;

    // texture and frame
    public TextureRegion skin;
    public Rectangle body;

    protected FroggerGame _game;

    public FroggerGameSprite(FroggerGame game, float x, float y) {
        this._game = game;
        this.x = x;
        this.y = y;
        this.active = true;
        this.visible = true;
        this.skin = null;
    }

    public FroggerGameSprite(FroggerGame game, float x, float y, String skinName) {
        this._game = game;
        this.x = x;
        this.y = y;
        this.active = true;
        this.visible = true;
        this.skin = null;

        setSkin(skinName);
    }

    public void setSkin(String skinName, int skinIndex) {
        // get specified skin from cache with name and index
        setSkin(FroggerImageCache.getFrame(skinName, skinIndex));
    }

    public void setSkin(String skinName) {
        // get skin with name only
        setSkin(FroggerImageCache.getTexture(skinName));
    }

    public void setSkin(TextureRegion texture) {
        this.skin = texture;

        // load size and frame after loading texture
        this.width = this.skin.getRegionWidth();
        this.height = this.skin.getRegionHeight();
        this.x = x - this.width * 0.5f;
        this.y = y - this.height * 0.5f;
    }

    public float rightEdge() {
        return this.x + this.width;
    }

    public float leftEdge() {
        return this.x;
    }

    public float topEdge() {
        return this.y + this.height;
    }

    public float bottomEdge() {
        return this.y;
    }

    public Rectangle bounds() {
        // we reduce the boundaries of each object so we can easily detect a collision threshold (note we're not using java.awt.Rectangle2D)
        return new Rectangle(this.x + this.width * 0.2f, this.y + this.height * 0.2f, this.width * 0.8f, this.height * 0.8f);
    }

    public void reset () {}
    public void update (float dt) {}
    public void show () {}
    public void hide () {}

    public void draw () {
        _game.spriteBatch.draw(skin, x, y);
    }
}