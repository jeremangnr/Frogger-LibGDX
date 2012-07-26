package org.jn.frogger.core.data;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

/**
 * Created with IntelliJ IDEA.
 * User: jere
 * Date: 6/29/12
 * Time: 12:03 AM
 * To change this template use File | Settings | File Templates.
 */

public class FroggerSoundCache {

    public static Sound jumpSound;
    public static Sound hitSound;
    public static Sound pickupSound;
    public static Sound splashSound;
    public static Sound outOfBoundsSound;
    public static Sound targetSound;

    public static void load() {

        jumpSound = loadSound("jump.wav");
        hitSound = loadSound("hit.wav");
        pickupSound = loadSound("pickup.wav");
        splashSound = loadSound("splash2.wav");
        outOfBoundsSound = loadSound("outofbounds.wav");
        targetSound = loadSound("target.wav");

    }

    private static Sound loadSound(String filename) {
        return Gdx.audio.newSound(Gdx.files.internal("data/sounds/" + filename));
    }

    public static void play(Sound sound) {
        sound.play(1);
    }

}
