package org.jn.frogger.core.sprites;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import org.jn.frogger.core.FroggerGame;

/**
 * Created with IntelliJ IDEA.
 * User: jere
 * Date: 7/3/12
 * Time: 10:33 PM
 * To change this template use File | Settings | File Templates.
 */
public class FroggerMovingSprite extends FroggerGameSprite {

    // direction values
    public static final int UP = 0;
    public static final int DOWN = 1;
    public static final int LEFT = 2;
    public static final int RIGHT = 3;

    // position and speed vars
    public float vx = 0;
    public float vy = 0;
    public float nextX = 0;
    public float nextY = 0;

    public float speed;

    public FroggerMovingSprite(FroggerGame game, float x, float y) {
        super(game, x, y);

        nextX = x;
        nextY = y;
    }

    @Override
    public void setSkin(TextureRegion texture) {
        super.setSkin(texture);

        this.x = nextX = x;
        this.y = nextY = y;
    }

    @Override
    public void update(float dt) {
        nextX += speed * dt;
    }

    public void place() {
        x = nextX;
        y = nextY;
    }

    public float nextRight() {
        return nextX + width;
    }

    public float nextLeft() {
        return nextX;
    }

    public float nextTop() {
        return nextY + height;
    }

    public float nextBottom() {
        return nextY;
    }

    public Rectangle nextBounds() {
        return new Rectangle(nextLeft(), nextBottom(), width, height);
    }
}
