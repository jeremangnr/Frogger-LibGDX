package org.jn.frogger.core.animation;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: jere
 * Date: 7/3/12
 * Time: 12:30 AM
 * To change this template use File | Settings | File Templates.
 */
public class FroggerAnimation {

    final TextureRegion[] keyFrames;
    final float frameDuration;

    private List<AnimationEventListener> _listeners;

    // ------------- event definition -------------
    public class AnimationEvent extends EventObject {

        public AnimationEvent(Object source){
            super(source);
        }

    }

    public interface AnimationEventListener {
        public void onAnimationEnded(AnimationEvent e);
    }
    // ------------- end event definition -------------

    public FroggerAnimation(float frameDuration, TextureRegion... keyFrames) {
        this.frameDuration = frameDuration;
        this.keyFrames = keyFrames;

        this._listeners = new ArrayList<AnimationEventListener>();
    }

    public TextureRegion getKeyFrame(float stateTime, boolean loop) {
        // get frame according to elapsed time
        int frameNumber = (int) (stateTime / frameDuration);

        if (!loop) {
            // keep updating frame number if we are looping
            frameNumber = Math.min(keyFrames.length - 1, frameNumber);

            if (frameNumber == keyFrames.length - 1) {
                // notify when reaching last frame
                sendEvent();
            }
        } else {
            frameNumber = frameNumber % keyFrames.length;
        }

        return keyFrames[frameNumber];
    }

    public void addEventListener(AnimationEventListener listener) {
        _listeners.add(listener);
    }

    public void removeEventListener(AnimationEventListener listener) {
        _listeners.remove(listener);
    }

    private void sendEvent() {
        AnimationEvent event = new AnimationEvent(this);

        for (int i = 0; i < _listeners.size(); i++) {
            _listeners.get(i).onAnimationEnded(event);
        }
    }

}
