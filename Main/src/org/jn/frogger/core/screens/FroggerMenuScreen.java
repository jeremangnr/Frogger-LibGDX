package org.jn.frogger.core.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.GLCommon;
import com.badlogic.gdx.graphics.g2d.SpriteCache;
import org.jn.frogger.core.FroggerGame;
import org.jn.frogger.core.sprites.FroggerGameSprite;

/**
 * Created with IntelliJ IDEA.
 * User: jere
 * Date: 6/29/12
 * Time: 2:11 AM
 * To change this template use File | Settings | File Templates.
 */
public class FroggerMenuScreen extends FroggerScreen {

    private SpriteCache _spriteCache;
    private int _spriteCacheIndex;

    public FroggerMenuScreen(FroggerGame game) {
        super(game);
    }

    @Override
    public void createScreen() {

        // if sprites are not loaded yet, load them
        if (this.elements.size() == 0) {

            // we only pass the name here because we are using the sprite sheet data file
            FroggerGameSprite logo = new FroggerGameSprite( _game, _game.screenWidth * 0.5f, _game.screenHeight * 0.7f, "logo");
            FroggerGameSprite howToLabel = new FroggerGameSprite( _game, _game.screenWidth * 0.5f, _game.screenHeight * 0.53f, "label_how_to");
            FroggerGameSprite instructionsLabel = new FroggerGameSprite( _game, _game.screenWidth * 0.5f, _game.screenHeight * 0.2f, "label_instructions");
            FroggerGameSprite tapLabel = new FroggerGameSprite( _game, _game.screenWidth * 0.5f, _game.screenHeight * 0.02f, "label_tap");
            FroggerGameSprite control = new FroggerGameSprite( _game, _game.screenWidth * 0.5f, _game.screenHeight * 0.4f, "control");

            // load them into the sprite cache
           /*
            * NOTE: we could use SpriteBatch as well here, but SpriteCache is optimized for sprites that will not change over time (this being
            * the most clear example). Using this we can store all the "static" sprites and their vertex data so we can easily draw them on any
            * iteration.
            *
            */
            _spriteCache = new SpriteCache();

            _spriteCache.beginCache();

            _spriteCache.add(logo.skin, logo.x, logo.y);
            _spriteCache.add(howToLabel.skin, howToLabel.x, howToLabel.y);
            _spriteCache.add(instructionsLabel.skin, instructionsLabel.x, instructionsLabel.y);
            _spriteCache.add(tapLabel.skin, tapLabel.x, tapLabel.y);
            _spriteCache.add(control.skin, control.x, control.y);

            _spriteCache.endCache();

        }

    }

    @Override
    public void update(float dt) {

        // check for touches
        if (Gdx.input.justTouched()) {
            Gdx.app.log("A HIT!", "We got a live one people!");
            _game.setScreen("FroggerGameScreen");
        } else {
            GLCommon gl = Gdx.gl;
            gl.glClearColor(0.4f, 0.4f, 0.4f, 1);
            gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

            _game.camera.update();

            // set blend mode
            gl.glEnable(GL10.GL_BLEND);
            gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);

            _spriteCache.setProjectionMatrix(_game.camera.combined);

            // draw sprites
            _spriteCache.begin();
            _spriteCache.draw(_spriteCacheIndex);
            _spriteCache.end();
        }

    }
}
