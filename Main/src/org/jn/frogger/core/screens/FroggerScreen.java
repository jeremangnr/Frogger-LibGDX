package org.jn.frogger.core.screens;

import com.badlogic.gdx.Screen;

import java.util.ArrayList;
import java.util.List;

import org.jn.frogger.core.FroggerGame;
import org.jn.frogger.core.sprites.FroggerGameSprite;

/**
 * Created with IntelliJ IDEA.
 * User: jere
 * Date: 6/28/12
 * Time: 10:07 PM
 *
 * Represents a game screen
 */
public abstract class FroggerScreen implements Screen {

    public List<FroggerGameSprite> elements;
    protected FroggerGame _game;

    public FroggerScreen(FroggerGame game) {
        this._game = game;
        this.elements = new ArrayList<FroggerGameSprite>();
    }

    public void show() {}
    public void hide() {}
    public void pause() {}
    public void resume() {}
    public void dispose() {}
    public void destroy() {}

    public abstract void createScreen();
    public abstract void update (float dt);

    public void render(float v) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void resize(int i, int i1) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

}
