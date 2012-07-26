package org.jn.frogger.core.elements;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import org.jn.frogger.core.FroggerGame;
import org.jn.frogger.core.data.FroggerImageCache;
import org.jn.frogger.core.screens.FroggerGameScreen;
import org.jn.frogger.core.sprites.FroggerGameSprite;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created with IntelliJ IDEA.
 * User: jere
 * Date: 7/2/12
 * Time: 11:43 PM
 * To change this template use File | Settings | File Templates.
 */
public class FroggerTimeBar extends FroggerGameSprite {

    public int seconds;

    private Timer _timer;
    private int _timeWidth;
    private float _timeDecrement;
    private TextureRegion _bar;

    public FroggerTimeBar(FroggerGame game, float x, float y) {
        super(game, x ,y);

        // init stuff
        seconds = 0;
        skin = null;

        _bar = FroggerImageCache.getTexture("time_bar");
        width = _bar.getRegionWidth();
        height = _bar.getRegionHeight();

        _timeWidth = width;
        _timeDecrement = _timeWidth * 0.001f;

        _timer = new Timer();

        _game.screen.elements.add(this);

        // set off timer
        _timer.schedule(new TicTokTask(), 01, 1000);
    }

    @Override
    public void reset() {
        // reset timer
        _timeWidth = width;
        visible = true;
        seconds = 0;
    }

    @Override
    public void draw() {
        // the time bar gets smaller when time runs out
        _game.spriteBatch.draw(_bar, x, y, _timeWidth, height);
    }

    // private inner class definition for timer task
    class TicTokTask extends TimerTask {
        @Override
        public void run() {
            // if we are playing and showing the time bar
            if (_game.gameData.gameMode == FroggerGame.GAME_STATE_PLAY && visible) {
                // count!
                seconds++;
                // check if we reached zero
                if (_timeWidth - _timeDecrement <= 0) {
                    // hide time bar
                    visible = false;
                    _timer.cancel();

                    // show game over screen
                    FroggerGameScreen screen = (FroggerGameScreen) _game.screen;
                    //screen.gameOver();
                } else {
                    // else keep counting
                    _timeWidth -= _timeDecrement;

                }
            }
        }
    }

}
