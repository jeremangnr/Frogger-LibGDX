package org.jn.frogger.android;

import android.os.Bundle;
import com.badlogic.gdx.backends.android.AndroidApplication;
import org.jn.frogger.core.FroggerGame;

/**
 * Created with IntelliJ IDEA.
 * User: jere
 * Date: 6/28/12
 * Time: 8:59 PM
 * To change this template use File | Settings | File Templates.
 */
public class FroggerActivity extends AndroidApplication {

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        initialize(new FroggerGame(), false);

    }

}
