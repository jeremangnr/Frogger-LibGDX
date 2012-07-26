package org.jn.frogger.core.elements;

import com.badlogic.gdx.math.Rectangle;
import org.jn.frogger.core.FroggerGame;
import org.jn.frogger.core.sprites.FroggerGameSprite;
import org.jn.frogger.core.sprites.FroggerTierSprite;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: jere
 * Date: 7/4/12
 * Time: 1:56 AM
 * To change this template use File | Settings | File Templates.
 */
public class FroggerTier extends FroggerGameSprite {

    public static final int TIER_TYPE_GROUND = 0;
    public static final int TIER_TYPE_WATER = 1;
    public static final int TIER_TYPE_GRASS = 2;

    //the y values the frog can be at
    public static final int[] TIER_Y = {418,388,357,328,300,272,244,216,182,149,117,84,52};
    //the y values for the elements in each tier
    public static final int[] TIER_ELEMENT_Y = {0,396,367,339,310,281,0,228,194,161,129,95,65};
    //which tier is GROUND, or WATER or GRASS
    public static final int[] TIER_TYPES = {0,0,0,0,0,0,0,1,1,1,1,1,2};
    //the speeds of each Tier
    public static final int[] TIER_SPEEDS = {0,-20,25,-20,40,-25,0,-30,20,40,-25,20,0};

    public int type;
    public float speed;

    protected List<FroggerTierSprite> _elements;
    protected int _index;

    public FroggerTier(FroggerGame game, int index) {
        super(game, 0, FroggerTier.TIER_Y[index]);

        _index = index;
        type = FroggerTier.TIER_TYPES[index];
        speed = FroggerTier.TIER_SPEEDS[index];

        _elements = new ArrayList<FroggerTierSprite>();
        skin = null;

        createElements();
    }

    //at the start of each new level, speeds will be updated
    public void refresh () {
        if (_index % 2 != 0) {
            speed = FroggerTier.TIER_SPEEDS[_index] * _game.gameData.tierSpeed1;
        } else {
            speed = FroggerTier.TIER_SPEEDS[_index] * _game.gameData.tierSpeed2;
        }
    }

    public FroggerTierSprite getElement (int index)  {
        if (_elements.size() <= index) return null;
        return _elements.get(index);
    }

    public boolean checkCollision (FroggerPlayer player) {

        FroggerTierSprite sprite;
        boolean collision = false;
        Rectangle player_rec = player.bounds();
        Rectangle spriteBounds;
        player.tierSpeed = 0;

        for (int i = 0; i < _elements.size(); i++ ) {
            sprite = _elements.get(i);
            spriteBounds = sprite.bounds();

            if (spriteBounds == null) continue;

            //check intersects
            if (spriteBounds.overlaps(player_rec)) {
                collision = true;
                break;
            }
        }

        //if on a tier with vehicles...
        if (type == FroggerTier.TIER_TYPE_GROUND) {
            //if collision, kill player
            if (collision) return true;
            //if on a tier with logs and turtles...
        } else if (type == FroggerTier.TIER_TYPE_WATER) {
            //if no collision drown player
            if (!collision) return true;
            //else, if collision, transfer tier speed to frog
            player.tierSpeed = speed;
        }

        return false;
    }

    //move elements back to original X position
    @Override
    public void reset() {

        float[] element_x = getElementsX();
        if (element_x != null) {
            for (int i = 0; i < _elements.size(); i++) {
                _elements.get(i).x = element_x[i];
                _elements.get(i).nextX = element_x[i];
            }
        }

    }


    protected void createElements () {

        float[] element_x = getElementsX();

        boolean[] element_type;
        int i;
        FroggerTierSprite sprite;

        switch (_index) {

            //VEHICLES!!!!
            case 1:
                for (i = 0; i < element_x.length; i++) {
                    sprite = new Vehicle("car_", 1, _game, element_x[i], _game.screenHeight - TIER_ELEMENT_Y[_index]);
                    _elements.add(sprite);
                }
                break;

            case 2:
                for (i = 0; i < element_x.length; i++) {
                    sprite = new Vehicle("car_", 3, _game, element_x[i], _game.screenHeight - TIER_ELEMENT_Y[_index]);
                    _elements.add(sprite);
                }

                break;
            case 3:
                for (i = 0; i < element_x.length; i++) {
                    sprite = new Vehicle("car_", 4, _game, element_x[i], _game.screenHeight - TIER_ELEMENT_Y[_index]);
                    _elements.add(sprite);
                }


                break;
            case 4:
                for (i = 0; i < element_x.length; i++) {
                    sprite = new Vehicle("car_", 2, _game, element_x[i], _game.screenHeight - TIER_ELEMENT_Y[_index]);
                    _elements.add(sprite);
                }

                break;
            case 5:
                for (i = 0; i < element_x.length; i++) {
                    sprite = new Vehicle("car_", 5, _game, element_x[i], _game.screenHeight - TIER_ELEMENT_Y[_index]);
                    _elements.add(sprite);
                }
                break;


            //LOGS AND TURTLES!!!!
            case 7:
                element_type = new boolean[9];
                element_type[0] = false;
                element_type[1] = false;
                element_type[2] = false;
                element_type[3] = true;
                element_type[4] = true;
                element_type[5] = true;
                element_type[6] = false;
                element_type[7] = false;
                element_type[8] = false;

                for (i = 0; i < element_x.length; i++) {
                    sprite = new Turtle(_game, element_x[i], _game.screenHeight - TIER_ELEMENT_Y[_index], element_type[i]);
                    _elements.add(sprite);
                }

                break;
            case 8:
                for (i = 0; i < element_x.length; i++) {
                    sprite = new TreeLog("log_small", _game, element_x[i], _game.screenHeight - TIER_ELEMENT_Y[_index]);
                    _elements.add(sprite);
                }
                break;
            case 9:
                for (i = 0; i < element_x.length; i++) {
                    sprite = new TreeLog("log_large", _game, element_x[i], _game.screenHeight - TIER_ELEMENT_Y[_index]);
                    _elements.add(sprite);
                }
                break;
            case 10:
                element_type = new boolean[8];
                element_type[0] = true;
                element_type[1] = true;
                element_type[2] = false;
                element_type[3] = false;
                element_type[4] = true;
                element_type[5] = true;
                element_type[6] = false;
                element_type[7] = false;

                for (i = 0; i < element_x.length; i++) {
                    sprite = new Turtle(_game, element_x[i], _game.screenHeight - TIER_ELEMENT_Y[_index], element_type[i]);
                    _elements.add(sprite);
                }
                break;
            case 11:
                for (i = 0; i < element_x.length; i++) {
                    if (i == 1) {
                        sprite = new Crocodile(_game, element_x[i], _game.screenHeight - TIER_ELEMENT_Y[_index]);
                    } else {
                        sprite = new TreeLog("log_medium", _game, element_x[i], _game.screenHeight - TIER_ELEMENT_Y[_index]);
                    }
                    _elements.add(sprite);
                }
                break;
        }

        speed = FroggerTier.TIER_SPEEDS[_index];

        //calculate distance between sprites (for smoother screen wrapping)
        int len = _elements.size();
        for (i = 0; i < len; i++) {
            sprite  = _elements.get(i);
            //if moving to the left
            if (FroggerTier.TIER_SPEEDS[_index] < 0) {
                //if not the first element, distance is between this element and previous element
                if (i != 0) {
                    _elements.get(i).distance = _elements.get(i).x - _elements.get(i-1).x;
                    //else, distance is between this one and the last element
                } else {
                    _elements.get(i).distance = _elements.get(i).x + (_game.screenWidth - _elements.get(_elements.size()-1).x) + sprite.width;
                }
                //if moving to the right
            } else if (FroggerTier.TIER_SPEEDS[_index] > 0) {
                //if not the last element, distance is between next element and this element
                if (i != _elements.size()-1) {
                    _elements.get(i).distance = _elements.get(i+1).x - _elements.get(i).x;
                } else {
                    _elements.get(i).distance = (_game.screenWidth - _elements.get(i).x) + _elements.get(0).x + sprite.width;
                }
            }
        }
    }


    @Override
    public void update (float dt) {

        int len = _elements.size();
        int i;
        FroggerTierSprite sprite;

        switch (_index) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
                //move sprites and wrap them on screen
                FroggerTierSprite nextSprite;

                for (i = 0; i < len; i++) {
                    sprite = _elements.get(i);
                    sprite.speed = speed;
                    sprite.update(dt);

                    if (speed < 0) {
                        if (sprite.nextRight() <= 0) {
                            if (i != 0) {
                                nextSprite = _elements.get(i-1);
                            } else {
                                nextSprite = _elements.get(len-1);
                            }
                            sprite.nextX = nextSprite.nextX + sprite.distance;
                        }
                    } else {
                        if (sprite.nextLeft() >= _game.screenWidth) {
                            if (i != len - 1) {
                                nextSprite = _elements.get(i+1);
                            } else {
                                nextSprite = _elements.get(0);
                            }
                            sprite.nextX = nextSprite.nextX - sprite.distance;
                        }
                    }
                    sprite.place();
                }
                break;
        }
    }

    protected float[] getElementsX () {

        float[] element_x;

        switch (_index) {

            //VEHICLES!!!!
            case 1:
                element_x = new float[4];
                element_x[0] = _game.screenWidth*0.1f;
                element_x[1] = _game.screenWidth*0.4f;
                element_x[2] = _game.screenWidth*0.6f;
                element_x[3] = _game.screenWidth*0.9f;
                return element_x;
            case 2:
                element_x = new float[3];
                element_x[0] = _game.screenWidth*0.2f;
                element_x[1] = _game.screenWidth*0.45f;
                element_x[2] = _game.screenWidth*0.7f;
                return element_x;
            case 3:
                element_x = new float[3];
                element_x[0] = _game.screenWidth*0.3f;
                element_x[1] = _game.screenWidth*0.6f;
                element_x[2] = _game.screenWidth*0.9f;
                return element_x;
            case 4:
                element_x = new float[2];
                element_x[0] = _game.screenWidth*0.5f;
                element_x[1] = _game.screenWidth*0.35f;
                return element_x;
            case 5:
                element_x = new float[3];
                element_x[0] = _game.screenWidth*0.2f;
                element_x[1] = _game.screenWidth*0.5f;
                element_x[2] = _game.screenWidth*0.8f;
                return element_x;
            //LOGS AND TURTLES!!!!
            case 7:
                element_x = new float[9];
                element_x[0] = _game.screenWidth*0.1f;
                element_x[1] = _game.screenWidth*0.18f;
                element_x[2] = _game.screenWidth*0.26f;
                element_x[3] = _game.screenWidth*0.45f;
                element_x[4] = _game.screenWidth*0.53f;
                element_x[5] = _game.screenWidth*0.61f;
                element_x[6] = _game.screenWidth*0.8f;
                element_x[7] = _game.screenWidth*0.88f;
                element_x[8] = _game.screenWidth*0.96f;
                return element_x;
            case 8:
                element_x = new float[3];
                element_x[0] = _game.screenWidth*0.2f;
                element_x[1] = _game.screenWidth*0.5f;
                element_x[2] = _game.screenWidth*0.8f;
                return element_x;
            case 9:
                element_x = new float[2];
                element_x[0] = _game.screenWidth*0.2f;
                element_x[1] = _game.screenWidth*0.8f;
                return element_x;
            case 10:
                element_x = new float[8];
                element_x[0] = _game.screenWidth*0.05f;
                element_x[1] = _game.screenWidth*0.13f;
                element_x[2] = _game.screenWidth*0.35f;
                element_x[3] = _game.screenWidth*0.43f;
                element_x[4] = _game.screenWidth*0.62f;
                element_x[5] = _game.screenWidth*0.7f;
                element_x[6] = _game.screenWidth*0.9f;
                element_x[7] = _game.screenWidth*0.98f;
                return element_x;
            case 11:
                element_x = new float[3];
                element_x[0] = _game.screenWidth*0.15f;
                element_x[1] = _game.screenWidth*0.5f;
                element_x[2] = _game.screenWidth*0.85f;
                return element_x;
        }
        return null;
    }


}
