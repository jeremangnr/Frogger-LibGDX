package org.jn.frogger.core.elements;

import org.jn.frogger.core.FroggerGame;
import org.jn.frogger.core.sprites.FroggerTierSprite;

/**
 * Created with IntelliJ IDEA.
 * User: jere
 * Date: 7/27/12
 * Time: 1:22 AM
 * To change this template use File | Settings | File Templates.
 */
public class FroggerTarget extends FroggerTierSprite {

    public static final int TARGET = 0;
    public static final int FLY = 1;
    public static final int CROC = 2;
    public static final int BONUS_200 = 3;
    public static final int BONUS_400 = 4;

    public int type;

    public FroggerTarget(FroggerGame game, float x, float y, int type) {

        super(game, x, y);
        this.type = type;

        switch(type) {
            case TARGET: {
                setSkin("frog_target");
                break;
            }
            case FLY: {
                setSkin("fly");
                break;
            }
            case CROC: {
                setSkin("alligator");
                break;
            }
            case BONUS_200: {
                setSkin("label_", 200);
                break;
            }
            case BONUS_400: {
                setSkin("label_", 400);
                break;
            }
        }

        visible = false;

        _game.screen.elements.add(this);
    }

}