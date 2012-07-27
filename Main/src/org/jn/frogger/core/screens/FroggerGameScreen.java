package org.jn.frogger.core.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.GLCommon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import org.jn.frogger.core.FroggerGame;
import org.jn.frogger.core.data.FroggerSoundCache;
import org.jn.frogger.core.elements.*;
import org.jn.frogger.core.sprites.FroggerGameSprite;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created with IntelliJ IDEA.
 * User: jere
 * Date: 7/25/12
 * Time: 12:55 AM
 * To change this template use File | Settings | File Templates.
 */
public class FroggerGameScreen extends FroggerScreen {

    private FroggerPlayer _player;
    //private BonusFrog _bonusFrog;
    private FroggerControls _controls;
    private FroggerTimeBar _timeBar;
    private FroggerGameSprite _gameOverMsg;
    private FroggerGameSprite _newLevelMsg;
    private FroggerTimeMsg _levelTimeMsg;
    private FroggerScore _score;
    private FroggerLevel _level;
    private FroggerLives _lives;
    private Vector3 _touchPoint;
    private Rectangle _controlBounds;

    private List<FroggerTier> _tiers;

    public FroggerGameScreen(FroggerGame game) {
        super(game);

        _tiers = new ArrayList<FroggerTier>();
        _touchPoint = new Vector3();
    }

    @Override
    public void createScreen() {

        if (elements.size() == 0) {

            // background
            FroggerGameSprite bg = new FroggerGameSprite(_game, _game.screenWidth * 0.5f, _game.screenHeight * 0.5f, "bg");
            elements.add(bg);

            // add tiers (cars, crocodiles, trees, turtles)
            for (int i = 0; i < 12; i++) {
                _tiers.add(new FroggerTier(this._game, i));
            }

            _tiers.add(new FroggerFinalTier(_game, 12));

            // add grass
            FroggerGameSprite grass = new FroggerGameSprite(_game, _game.screenWidth * 0.5f, _game.screenHeight - (_game.screenHeight * 0.12f), "grass");

            elements.add(grass);

            _player = new FroggerPlayer (_game, _game.screenWidth * 0.5f, _game.screenHeight - _game.screenHeight * 0.89f);
            //_bonusFrog = new BonusFrog (_game, -100, -100, _player);
            //_bonusFrog.log = _tiers.get(8).getElement(0);

            // time label
            FroggerGameSprite timeLabel = new FroggerGameSprite(_game, _game.screenWidth * 0.1f, _game.screenHeight * 0.04f, "label_time");
            elements.add(timeLabel);

            _timeBar = new FroggerTimeBar(_game, _game.screenWidth * 0.18f, _game.screenHeight * 0.03f);

            _score = new FroggerScore(_game, _game.screenWidth * 0.2f, _game.screenHeight - _game.screenHeight * 0.05f, "number_score_");
            _level = new FroggerLevel(_game, _game.screenWidth * 0.04f, _game.screenHeight - _game.screenHeight * 0.05f, "number_level_");
            _lives = new FroggerLives(_game, _game.screenWidth * 0.68f, _game.screenHeight - _game.screenHeight * 0.06f);

            _controls = new FroggerControls(_game, _game.screenWidth * 0.82f, _game.screenHeight - _game.screenHeight * 0.88f);
            _controlBounds = _controls.bounds();

            _gameOverMsg = new FroggerGameSprite(_game, _game.screenWidth * 0.5f, _game.screenHeight - _game.screenHeight * 0.53f, "game_over_box");
            _gameOverMsg.visible = false;
            elements.add(_gameOverMsg);

            _newLevelMsg = new FroggerGameSprite(_game, _game.screenWidth * 0.5f, _game.screenHeight - _game.screenHeight * 0.53f, "new_level_box");
            _newLevelMsg.visible = false;
            elements.add(_newLevelMsg);

            _levelTimeMsg = new FroggerTimeMsg(_game, _game.screenWidth * 0.5f, _game.screenHeight - _game.screenHeight * 0.53f);
            _levelTimeMsg.visible = false;

        } else {

            _timeBar.reset();
            _player.reset();
            //_bonusFrog.reset();
            _score.reset();
            _level.reset();
            _game.gameData.reset();
            _lives.show();

            for (int i = 0; i < _tiers.size(); i++) {
                _tiers.get(i).reset();
            }
        }

        _game.gameData.gameMode = FroggerGame.GAME_STATE_PLAY;
    }

    @Override
    public void update(float dt) {

        //
        //
        // check for input
        //
        //
        //
        if (Gdx.input.justTouched()) {
            if (_gameOverMsg.visible) {
                _gameOverMsg.visible = false;
                _game.setScreen("FroggerMenuScreen");
            } else {

                if (_game.gameData.gameMode == FroggerGame.GAME_STATE_PAUSE) return;

                //test for touch on Controls
                if (!_player.moving && _player.visible && _game.gameData.gameMode == FroggerGame.GAME_STATE_PLAY) {

                    _game.camera.unproject(_touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));

                    if (_controlBounds.contains(_touchPoint.x, _touchPoint.y)) {
                        switch (_controls.getDirection(_touchPoint)) {
                            case FroggerPlayer.MOVE_UP:
                                _player.moveFrogUp();
                                break;
                            case FroggerPlayer.MOVE_DOWN:
                                _player.moveFrogDown();
                                break;
                            case FroggerPlayer.MOVE_LEFT:
                                _player.moveFrogLeft();
                                break;
                            case FroggerPlayer.MOVE_RIGHT:
                                _player.moveFrogRight();
                                break;
                        }
                    }
                }
            }
        }

        //
        //
        // update elements!
        //
        //
        //
        _player.update(dt);
        _player.place();
        //_bonusFrog.update(dt);
        //_bonusFrog.place();


        // update all tiers
        for (int i = 0; i < _tiers.size(); i++) {
            _tiers.get(i).update(dt);
        }

        //
        //
        // check for collisions!
        //
        //
        //
        if (_player.active) {
            //check collision of frog and tier sprites
            if (_tiers.get(_player.tierIndex).checkCollision(_player)) {
                //if tiers with vehicles, and colliding with vehicle
                if (_player.tierIndex < 6) {
                    FroggerSoundCache.play(FroggerSoundCache.hitSound);
                    //if not colliding with anything in the water tiers, drown frog
                } else {
                    FroggerSoundCache.play(FroggerSoundCache.splashSound);
                }
                //kill player
                _player.kill();
                _game.gameData.lives--;
            }
            //check collision of frog and bonus frog
            //if bonus frog is visible and not on frog
            //if (_bonusFrog.visible) {
            //    if (_bonusFrog.bounds().overlaps(_player.bounds())) {
            //        _player.hasBonus = true;
            //    }
            //}
        } else {
            if (_player.hasBonus) {
                //_bonusFrog.visible = false;
                _player.hasBonus = false;
            }
        }

        //
        //
        // render all elements
        //
        //
        //
        GLCommon gl = Gdx.gl;
        gl.glClearColor(0, 0, 0, 1);
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

        _game.camera.update();

        _game.spriteBatch.setProjectionMatrix(_game.camera.combined);
        _game.spriteBatch.enableBlending();

        _game.spriteBatch.begin();

        for (int i = 0; i < elements.size(); i++) {
            FroggerGameSprite element = elements.get(i);

            if (!element.visible) continue;

            if (element.skin == null) {
                element.draw();
            } else {
                _game.spriteBatch.draw(element.skin, element.x, element.y);
            }
        }

        _game.spriteBatch.end();

    }

    public void gameOver () {
        _gameOverMsg.visible  = true;
        _game.gameData.gameMode = FroggerGame.GAME_STATE_PAUSE;
    }

    public void targetReached() {

        //show the time needed to reach this target
        _levelTimeMsg.timeLabel.value = _timeBar.seconds;
        _levelTimeMsg.show();

        final Timer timer = new Timer();
        // show msg for a while then hide
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                _levelTimeMsg.hide();
                timer.cancel();
            }
        }, 3000, 1000);

        _player.reset();
        _timeBar.seconds = 0;

    }

    public void newLevel() {

        // pause so we don't update screen
        _game.gameData.gameMode = FroggerGame.GAME_STATE_PAUSE;

        // increase tier speed for next level
        _game.gameData.tierSpeed1 += 0.1f;
        _game.gameData.tierSpeed2 += 0.2f;

        // reset tiers
        for (int i = 0; i < _tiers.size(); i++) {
            _tiers.get(i).refresh();
        }

        // restart timebar
        _timeBar.reset();

        // go!
        _game.gameData.gameMode = FroggerGame.GAME_STATE_PLAY;
    }

}
