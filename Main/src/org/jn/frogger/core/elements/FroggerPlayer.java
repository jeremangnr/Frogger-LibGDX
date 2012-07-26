package org.jn.frogger.core.elements;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import org.jn.frogger.core.FroggerGame;
import org.jn.frogger.core.animation.FroggerAnimation;
import org.jn.frogger.core.data.FroggerGameData;
import org.jn.frogger.core.data.FroggerImageCache;
import org.jn.frogger.core.data.FroggerSoundCache;
import org.jn.frogger.core.screens.FroggerGameScreen;
import org.jn.frogger.core.sprites.FroggerMovingSprite;

/**
 * Created with IntelliJ IDEA.
 * User: jere
 * Date: 7/3/12
 * Time: 10:53 PM
 * To change this template use File | Settings | File Templates.
 */
public class FroggerPlayer extends FroggerMovingSprite implements FroggerAnimation.AnimationEventListener {

    public static final int MOVE_UP = 0;
    public static final int MOVE_DOWN = 1;
    public static final int MOVE_LEFT = 2;
    public static final int MOVE_RIGHT = 3;

    public boolean hasBonus = false;
    public boolean dead = false;
    public boolean moving = false;

    public float tierSpeed = 0.0f;
    public int tierIndex = 0;

    // textures
    private TextureRegion _frogStand;
    private TextureRegion _frogSide;
    private TextureRegion _frogJump;
    private TextureRegion _frogSideJump;
    private TextureRegion _restFrame;

    private FroggerAnimation _deathAnimation;
    private float _animationTime;

    private int _sidestep = 22;
    private Vector3 _startPoint;
    private Sprite _sprite;
    private float _moveCnt = 0.0f;
    private int _moveInterval = 6;

    public FroggerPlayer(FroggerGame game, float x, float y) {
        super(game, x, y);

        tierSpeed = 0.0f;

        // load textures for the frog
        _frogStand = FroggerImageCache.getTexture("frog_stand");
        _frogJump = FroggerImageCache.getTexture("frog_jump");
        _frogSide = FroggerImageCache.getTexture("frog_side");
        _frogSideJump = FroggerImageCache.getTexture("frog_side_jump");
        _restFrame = _frogStand;

        // we add the last frame multiple times because there is no way to increase the duration of a specific frame
        _deathAnimation = new FroggerAnimation(0.1f, FroggerImageCache.getFrame("death_", 1),
                                                     FroggerImageCache.getFrame("death_", 2),
                                                     FroggerImageCache.getFrame("death_", 3),
                                                     FroggerImageCache.getFrame("death_", 4),
                                                     FroggerImageCache.getFrame("death_", 4),
                                                     FroggerImageCache.getFrame("death_", 4),
                                                     FroggerImageCache.getFrame("death_", 4),
                                                     _frogStand);
        // animation start time
        _animationTime = 0.0f;

        // we want to be notified when the animation stops
        _deathAnimation.addEventListener(this);

        // default skin
        setSkin(_frogStand);

        // null the skin so we use draw to render the sprite instead
        skin = null;
        _sprite = new Sprite(_frogStand);
        _sprite.setPosition(x, y);

        // set start point
        _startPoint = new Vector3(x - width * 0.5f, y - height * 0.5f, 0.0f);

        _game.screen.elements.add(this);
    }

    public void moveFrogUp() {
        if (!moving) {
            moving = true;
            // move up the screen
            tierIndex++;

            if (tierIndex >= FroggerTier.TIER_Y.length) tierIndex = FroggerTier.TIER_Y.length - 1;

            nextY = _game.screenHeight - FroggerTier.TIER_Y[tierIndex] - height;
            // increase score
            _game.gameData.score += FroggerGameData.POINTS_JUMP;

            FroggerSoundCache.play(FroggerSoundCache.jumpSound);
            showMoveFrame(FroggerMovingSprite.UP);
        }
    }

    public void moveFrogDown() {
        if (!moving) {
            moving = true;
            // move up the screen
            tierIndex--;

            if (tierIndex < 0) tierIndex = 0;

            nextY = _game.screenHeight - FroggerTier.TIER_Y[tierIndex] - height;
            // increase score
            _game.gameData.score += FroggerGameData.POINTS_JUMP;

            FroggerSoundCache.play(FroggerSoundCache.jumpSound);
            showMoveFrame(FroggerMovingSprite.DOWN);
        }
    }

    public void moveFrogLeft() {
        if (!moving) {
            moving = true;
            nextX -= _sidestep;

            FroggerSoundCache.play(FroggerSoundCache.jumpSound);
            showMoveFrame(FroggerMovingSprite.LEFT);
        }
    }

    public void moveFrogRight() {
        if (!moving) {
            moving = true;
            nextX += _sidestep;

            FroggerSoundCache.play(FroggerSoundCache.jumpSound);
            showMoveFrame(FroggerMovingSprite.RIGHT);
        }
    }

    public void kill() {
        tierSpeed = 0;
        _game.gameData.gameMode = FroggerGame.GAME_STATE_ANIMATE;
        active = false;
        dead = true;

        // set to "straight" position
        _sprite.setScale(1.0f, 1.0f);
    }

    public void showMoveFrame(int dir) {
        switch (dir) {
            case FroggerMovingSprite.LEFT:
                _sprite.setScale(-1, 1);
                _sprite.setRegion(_frogSideJump);
                _restFrame = _frogSide;
                break;
            case FroggerMovingSprite.RIGHT:
                _sprite.setScale(1, 1);
                _sprite.setRegion(_frogSideJump);
                _restFrame = _frogSide;
                break;
            case FroggerMovingSprite.UP:
                _sprite.setScale(1, 1);
                _sprite.setRegion(_frogJump);
                _restFrame = _frogStand;
                break;
            case FroggerMovingSprite.DOWN:
                _sprite.setScale(1, -1);
                _sprite.setRegion(_frogJump);
                _restFrame = _frogStand;
                break;
        }
    }

    @Override
    public void draw() {
        if (dead) {
            _sprite.setRegion(_deathAnimation.getKeyFrame(_animationTime, false));
        }

        _sprite.draw(_game.spriteBatch);
    }

    @Override
    public void update(float dt) {
        if (dead) {
            _animationTime += dt;
            return;
        }

        if (moving) {
            // if we didn't move enough to actually move (lol), show rest frame
            if (_moveCnt > _moveInterval) {
                moving = false;
                _sprite.setRegion(_restFrame);

                _moveCnt = 0.0f;
            }

            _moveCnt += 20 * dt;
        }

        // add tier speed if player is on top of moving object
        nextX += tierSpeed * dt;
    }

    @Override
    public void reset() {
        visible = true;
        dead = false;
        _animationTime = 0.0f;

        // reset position
        x = nextX = _startPoint.x;
        y = nextY = _startPoint.y;
        _sprite.setPosition(x, y);

        tierIndex = 0;
        active = true;
        hasBonus = false;
        moving = false;
    }

    @Override
    public void place() {
        // limit movement if player is not on water element so it doesn't go off the screen
        if (tierIndex > 7) {

            if (nextX < 0) {
                nextX = 0;
            }

            if (nextX > _game.screenWidth - width) {
                nextX = _game.screenWidth - width;
            }

        } else {
            // make player go back to start if frog leaves screen while on water tier
            if (nextX < 0 || nextX > _game.screenWidth - width) {
                FroggerSoundCache.play(FroggerSoundCache.outOfBoundsSound);
                reset();
            }
        }

        // call super
        super.place();
        _sprite.setPosition(x, y);
    }

    // when death animation finishes
    public void onAnimationEnded(FroggerAnimation.AnimationEvent e) {
        visible = false;
        dead = false;
        //reset frog
        _sprite.setRegion(_frogStand);
        _restFrame = _frogStand;

        if (_game.gameData.lives > 0) {
            reset();
            _game.gameData.gameMode = FroggerGame.GAME_STATE_PLAY;
        } else {
            FroggerGameScreen screen = (FroggerGameScreen) _game.screen;
            screen.gameOver();
        }
    }
}
