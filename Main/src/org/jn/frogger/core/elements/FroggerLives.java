package org.jn.frogger.core.elements;

import com.badlogic.gdx.graphics.g2d.Sprite;
import org.jn.frogger.core.FroggerGame;
import org.jn.frogger.core.data.FroggerImageCache;
import org.jn.frogger.core.sprites.FroggerGameSprite;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: jere
 * Date: 7/3/12
 * Time: 12:10 AM
 * To change this template use File | Settings | File Templates.
 */
public class FroggerLives extends FroggerGameSprite {

    private List<Sprite> _lives;

    public FroggerLives(FroggerGame game, float x, float y) {

        super(game, x, y);

        skin = null;
        _lives = new ArrayList<Sprite>();

        Sprite sprite;
        for (int i = 0; i < _game.gameData.lives; i++) {
            sprite = new Sprite(FroggerImageCache.getTexture("frog_stand"));
            sprite.setPosition(x + i * sprite.getRegionWidth() + 5, y);
            _lives.add(sprite);
        }

        _game.screen.elements.add(this);
    }

    @Override
    public void draw() {
        Sprite sprite;
        for (int i = 0; i < _game.gameData.lives; i++) {
            _lives.get(i).draw(_game.spriteBatch);
        }
    }

}
