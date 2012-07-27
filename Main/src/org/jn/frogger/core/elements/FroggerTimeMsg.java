package org.jn.frogger.core.elements;

import org.jn.frogger.core.FroggerGame;
import org.jn.frogger.core.data.FroggerImageCache;
import org.jn.frogger.core.sprites.FroggerGameSprite;
import org.jn.frogger.core.sprites.FroggerNumberSprite;

/**
 * Created with IntelliJ IDEA.
 * User: jere
 * Date: 7/26/12
 * Time: 11:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class FroggerTimeMsg extends FroggerGameSprite {

    public FroggerNumberSprite timeLabel;

    public FroggerTimeMsg(FroggerGame game, float x, float y) {
        super(game, x, y);

        setSkin(FroggerImageCache.getTexture("time_box"));
        _game.screen.elements.add(this);

        timeLabel = new FroggerNumberSprite(_game, x + width * 0.1f, y - height*0.3f, "number_time_");

        // init invisible
        timeLabel.visible = false;
    }

    @Override
    public void show() {
        visible = true;
        timeLabel.visible = true;
    }

    @Override
    public void hide() {
        visible = false;
        timeLabel.visible = false;
    }
}
