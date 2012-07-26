package org.jn.frogger.core;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.GLCommon;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import org.jn.frogger.core.screens.FroggerScreen;
import org.jn.frogger.core.data.FroggerGameData;
import org.jn.frogger.core.data.FroggerImageCache;
import org.jn.frogger.core.data.FroggerSoundCache;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: jere
 * Date: 6/28/12
 * Time: 9:20 PM
 * To change this template use File | Settings | File Templates.
 */
public class FroggerGame implements ApplicationListener {

    // game states
    public static final int GAME_STATE_PLAY = 0;
    public static final int GAME_STATE_PAUSE = 1;
    public static final int GAME_STATE_ANIMATE = 2;

    // screen
    public FroggerScreen screen;

    // game data
    public FroggerGameData gameData;

    // sprite handling
    public SpriteBatch spriteBatch;

    // camera handling
    public OrthographicCamera camera;
    public int screenWidth = 0;
    public int screenHeight = 0;

    // holds all of the game's screens
    protected Map<String, FroggerScreen> _screens;

    public void create() {
        this._screens = new HashMap<String, FroggerScreen>();

        // init screen size
        this.screenWidth = 320;
        this.screenHeight = 480;

        // init camera
        this.camera = new OrthographicCamera(this.screenWidth, this.screenHeight);
        // center camera
        this.camera.position.set(this.screenWidth * 0.5f, this.screenHeight * 0.5f, 0);

        // load sprites and sounds into memory
        FroggerImageCache.load();
        FroggerSoundCache.load();

        // load game info
        this.gameData = new FroggerGameData(this);

        // init sprite batch
        this.spriteBatch = new SpriteBatch();

        this.setScreen("FroggerMenuScreen");
    }

    public void resize(int i, int i1) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void render() {
        if (this.screen != null) {
            this.screen.update(Gdx.graphics.getDeltaTime());
        } else {

            GLCommon gl = Gdx.gl;
            gl.glClearColor(0, 0, 0, 1);
            gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

        }
    }

    public void pause() {
        if (this.screen != null) this.screen.pause();
    }

    public void resume() {
        if (this.screen != null) this.screen.resume();
    }

    public void dispose() {
        if (this.screen != null) this.screen.dispose();
    }

    // screen setter (displays a specific screen given its name)
    public void setScreen(String screenClassName) {

        screenClassName = "org.jn.frogger.core.screens." + screenClassName;
        FroggerScreen newScreen = null;

        // check if we already loaded that screen and if we doesn't, try to load it
        if (!_screens.containsKey(screenClassName)) {

            try {
                // load it using reflection
                Class screenClass = Class.forName(screenClassName);
                Constructor constructor = screenClass.getConstructor(FroggerGame.class);

                // try to create new instance
                newScreen = (FroggerScreen) constructor.newInstance(this);

                // save it in screens map
                _screens.put(screenClassName, newScreen);

            } catch (InvocationTargetException ex) {

                System.err.println("Screen with wrong args in constructor" + ex);

            } catch (NoSuchMethodException ex) {
            } catch (ClassNotFoundException ex) {

                System.err.println("Screen class not found" + ex);

            } catch (InstantiationException ex) {

                System.err.println("Screen must be a concrete class" + ex);

            } catch (IllegalAccessException ex) {

                System.err.println("Screen with wrong number of args" + ex);

            }

        } else {
            // load the screen if we already have it
            newScreen = _screens.get(screenClassName);
        }

        // return if no matching screen found
        if (newScreen == null) return;

        // remove current screen if we have one
        if (screen != null) {
            screen.destroy();
        }

        // set screen
        this.screen = newScreen;

        // create
        this.screen.createScreen();
    }
}
