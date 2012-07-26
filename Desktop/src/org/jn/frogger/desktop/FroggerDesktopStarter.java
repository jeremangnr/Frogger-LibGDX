package org.jn.frogger.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import org.jn.frogger.core.FroggerGame;

/**
 * Created with IntelliJ IDEA.
 * User: jere
 * Date: 6/28/12
 * Time: 9:03 PM
 * To change this template use File | Settings | File Templates.
 */
public class FroggerDesktopStarter {

    public static void main(String[] args) {
        LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();

        cfg.title = "Frogger-Desktop";
        cfg.useGL20 = false;
        cfg.width = 320;
        cfg.height = 480;

        new LwjglApplication(new FroggerGame(), cfg);
    }

}
