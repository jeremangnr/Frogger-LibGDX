package org.jn.frogger.core.elements;

import com.badlogic.gdx.math.Rectangle;
import org.jn.frogger.core.FroggerGame;
import org.jn.frogger.core.data.FroggerGameData;
import org.jn.frogger.core.data.FroggerSoundCache;
import org.jn.frogger.core.screens.FroggerGameScreen;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: jere
 * Date: 7/26/12
 * Time: 11:51 PM
 * To change this template use File | Settings | File Templates.
 */
public class FroggerFinalTier extends FroggerTier {

    private List<FroggerTarget> _targets;
    private List<FroggerTarget> _flies;
    private List<FroggerTarget> _crocs;
    private List<FroggerTarget> _bonus200;
    private List<FroggerTarget> _bonus400;

    private int _bonusCnt;
    private Random _random;
    private int _selIndex;
    private FroggerGameScreen _screen;

    public FroggerFinalTier(FroggerGame game, int index) {

        super(game, index);

        _bonusCnt = 0;
        _random = new Random();
        _screen = (FroggerGameScreen)_game.screen;

    }

    @Override
    public boolean checkCollision(FroggerPlayer player) {

        FroggerTarget sprite;
        boolean collision = false;
        Rectangle playerRect = player.bounds();
        Rectangle spriteRect;
        player.tierSpeed = 0;

        for (int i = 0; i < _targets.size(); i++) {
            sprite = _targets.get(i);
            spriteRect = sprite.bounds();
            if (spriteRect == null) continue;

            //check intersects
            if (spriteRect.overlaps(playerRect)) {
                collision = true;
                _selIndex = i;
                break;
            }
        }

        FroggerTarget fly = _flies.get(_selIndex);
        FroggerTarget croc = _crocs.get(_selIndex);
        FroggerTarget target = _targets.get(_selIndex);
        FroggerTarget bonus200 = _bonus200.get(_selIndex);
        FroggerTarget bonus400 = _bonus400.get(_selIndex);

        if (collision) {
            //if this target has been reached already...
            if (target.visible) {
                //send player back to beginning
                player.reset();
                return false;
            } else {
                player.active = false;
                player.visible = false;
                //check if croc head is in the slot
                if (croc.visible) {
                    //kill player!!!
                    return true;
                } else {

                    int bonus = 0;
                    //check if there are flies in this slot
                    if (fly.visible) {
                        fly.visible = false;
                        bonus += FroggerGameData.POINTS_FLY;
                    }

                    if (player.hasBonus) bonus += FroggerGameData.POINTS_BONUS;

                    //show bonus points!!!
                    if (bonus > 0) {
                        if (bonus > FroggerGameData.POINTS_BONUS) {
                            bonus400.visible = true;
                        } else {
                            bonus200.visible = true;
                        }
                        _game.gameData.score += bonus;
                        //show target reached icon after displaying bonus for some time
                        final Timer timer = new Timer();

                        timer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                _bonus400.get(_selIndex).visible = false;
                                _bonus200.get(_selIndex).visible = false;
                                _targets.get(_selIndex).visible = true;

                                timer.cancel();
                            }
                        }, 300, 1);

                    } else {
                        target.visible = true;
                    }

                    _game.gameData.targetsReached++;
                    FroggerSoundCache.play(FroggerSoundCache.pickupSound);

                    final Timer timer2 = new Timer();

                    timer2.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            _screen.targetReached();

                            if (_game.gameData.targetsReached == 5)  {
                                FroggerSoundCache.play(FroggerSoundCache.targetSound);
                                //start new level
                                _screen.newLevel();
                                hide();
                            }

                            timer2.cancel();
                        }
                    }, 1000, 1);


                    //add points for reaching a target
                    _game.gameData.score += FroggerGameData.POINTS_TARGET;
                    player.hide();

                }
                return false;
            }
        }

        return true;
    }

    @Override
    public void update(float dt) {

        //show fly or croc head
        FroggerTarget croc;
        FroggerTarget target;

        for (int i = 0; i < _crocs.size(); i++) {

            croc = _crocs.get(i);

            if (croc.visible) {
                if (_targets.get(i).visible) {
                    croc.visible = false;
                } else {
                    target = _targets.get(i);
                    if (croc.x < target.x) {
                        croc.x += 0.4;
                    }
                }
            }

        }

        if (_bonusCnt > 80) {
            _bonusCnt = 0;
            if (_random.nextInt(10) > 6) {
                //pick an index
                final int index = _random.nextInt(_targets.size());

                if (!_targets.get(index).visible &&
                        !_flies.get(index).visible && !_crocs.get(index).visible) {

                    if (_random.nextInt(10) > 6) {
                        _crocs.get(index).x -= _crocs.get(index).width;
                        _crocs.get(index).visible = true;
                    } else {
                        _flies.get(index).visible = true;
                    }

                    final Timer timer = new Timer();

                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            _crocs.get(index).visible = false;
                            _flies.get(index).visible = false;
                            timer.cancel();
                        }
                    }, 4000, 1);

                }
            }

        }
        _bonusCnt++;
    }

    @Override
    public void reset () {
        hide();
        _bonusCnt = 0;
    }

    @Override
    public void hide() {

        for (int i = 0; i < _targets.size(); i++) {
            _targets.get(i).visible = false;
            _flies.get(i).visible = false;
            _crocs.get(i).visible = false;
            _bonus200.get(i).visible = false;
            _bonus400.get(i).visible = false;

        }

    }

    @Override
    protected void createElements () {

        _targets = new ArrayList<FroggerTarget>();
        _flies = new ArrayList<FroggerTarget>();
        _crocs = new ArrayList<FroggerTarget>();
        _bonus200 = new ArrayList<FroggerTarget>();
        _bonus400 = new ArrayList<FroggerTarget>();

        FroggerTarget sprite;

        float[] element_x = {
                _game.screenWidth*0.07f,
                _game.screenWidth*0.29f,
                _game.screenWidth*0.5f,
                _game.screenWidth*0.715f,
                _game.screenWidth*0.93f
        };

        for (int i = 0; i < element_x.length; i++) {

            sprite = new FroggerTarget(_game, element_x[i], _game.screenHeight - TIER_ELEMENT_Y[_index], FroggerTarget.FLY);
            _flies.add(sprite);

            sprite = new FroggerTarget(_game, element_x[i], _game.screenHeight - TIER_ELEMENT_Y[_index], FroggerTarget.CROC);
            _crocs.add(sprite);

            sprite = new FroggerTarget(_game, element_x[i], _game.screenHeight - TIER_ELEMENT_Y[_index], FroggerTarget.TARGET);
            _targets.add(sprite);

            sprite = new FroggerTarget(_game, element_x[i], _game.screenHeight - TIER_ELEMENT_Y[_index], FroggerTarget.BONUS_200);
            _bonus200.add(sprite);

            sprite = new FroggerTarget(_game, element_x[i], _game.screenHeight - TIER_ELEMENT_Y[_index], FroggerTarget.BONUS_400);
            _bonus400.add(sprite);

        }
    }

}
