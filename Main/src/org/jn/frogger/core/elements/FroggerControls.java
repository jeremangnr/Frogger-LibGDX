package org.jn.frogger.core.elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector3;
import org.jn.frogger.core.FroggerGame;
import org.jn.frogger.core.sprites.FroggerGameSprite;

/**
 * Created with IntelliJ IDEA.
 * User: jere
 * Date: 7/25/12
 * Time: 1:45 AM
 * To change this template use File | Settings | File Templates.
 */
public class FroggerControls extends FroggerGameSprite {

    private Vector3 _center;

    public FroggerControls(FroggerGame game, float x, float y) {

        super(game, x, y);
        setSkin("control");

        _game.screen.elements.add(this);
        _center = new Vector3(x, y, 0.0f);

    }

    public int getDirection (Vector3 p) {

        double diffX = p.x - _center.x;
        double diffY = p.y - _center.y;

        double rad = Math.atan2(diffY, diffX);

        int angle = (int) (180 * rad / Math.PI);
        if (angle < 360) angle += 360;
        if (angle > 360) angle -=  360;

        if (angle > 315 || angle < 45) {
            Gdx.app.log("CONTROL CLICK:", "RIGHT");
            return FroggerPlayer.MOVE_RIGHT;
        } else if (angle >= 45 && angle <= 135) {
            Gdx.app.log("CONTROL CLICK:", "TOP");
            return FroggerPlayer.MOVE_UP;
        } else if (angle > 135 && angle < 225) {
            Gdx.app.log("CONTROL CLICK:", "LEFT");
            return FroggerPlayer.MOVE_LEFT;
        } else {
            Gdx.app.log("CONTROL CLICK:", "DOWN");
            return FroggerPlayer.MOVE_DOWN;
        }

    }

}
