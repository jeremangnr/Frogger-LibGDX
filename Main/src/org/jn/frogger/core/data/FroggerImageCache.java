package org.jn.frogger.core.data;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created with IntelliJ IDEA.
 * User: jere
 * Date: 6/28/12
 * Time: 11:01 PM
 *
 * Class that handles and cache the game's textures
 */
public class FroggerImageCache {

    public static Texture sheet;
    public static TextureAtlas atlas;

    public static void load() {
        String textureFile = "data/frogger.txt";

        atlas = new TextureAtlas(Gdx.files.internal(textureFile), Gdx.files.internal("data"));
    }

    public static TextureRegion getTexture(String name) {
        return atlas.findRegion(name);
    }

    public static TextureRegion getFrame(String name, int index) {
        return atlas.findRegion(name, index);
    }

}

// sprite sheet positions and sizes from TexturePacker (use it, buy it, LOVE IT)

/*
findRegion(java.lang.String name, int index)
frogger.png
label_time
rotate: false
xy: 295, 621
size: 37, 13
orig: 37, 13
offset: 0, 0
index: -1
death_
rotate: false
xy: 272, 484
size: 17, 22
orig: 17, 22
offset: 0, 0
index: 3
car_
rotate: false
xy: 421, 2
size: 22, 19
orig: 22, 19
offset: 0, 0
index: 4
number_level_
rotate: false
xy: 491, 660
size: 10, 16
orig: 10, 16
offset: 0, 0
index: 7
number_time_
rotate: false
xy: 227, 706
size: 6, 10
orig: 6, 10
offset: 0, 0
index: 9
frog_bonus_side
rotate: false
xy: 329, 484
size: 16, 21
orig: 16, 21
offset: 0, 0
index: -1
number_time_
rotate: false
xy: 187, 706
size: 6, 10
orig: 6, 10
offset: 0, 0
index: 4
number_level_
rotate: false
xy: 395, 660
size: 10, 16
orig: 10, 16
offset: 0, 0
index: -1
logo
rotate: false
xy: 150, 660
size: 155, 44
orig: 155, 44
offset: 0, 0
index: -1
fly
rotate: false
xy: 310, 484
size: 17, 16
orig: 17, 16
offset: 0, 0
index: -1
turtle_
rotate: false
xy: 48, 724
size: 21, 21
orig: 21, 21
offset: 0, 0
index: 3
number_score_
rotate: false
xy: 81, 706
size: 9, 16
orig: 9, 16
offset: 0, 0
index: 4
croc_
rotate: false
xy: 96, 484
size: 67, 29
orig: 67, 29
offset: 0, 0
index: 1
label_tap
rotate: false
xy: 207, 621
size: 86, 14
orig: 86, 14
offset: 0, 0
index: -1
death_
rotate: false
xy: 253, 484
size: 17, 22
orig: 17, 22
offset: 0, 0
index: 2
number_score_
rotate: false
xy: 114, 706
size: 9, 16
orig: 9, 16
offset: 0, 0
index: 7
car_
rotate: false
xy: 348, 2
size: 23, 25
orig: 23, 25
offset: 0, 0
index: 1
number_level_
rotate: false
xy: 479, 660
size: 10, 16
orig: 10, 16
offset: 0, 0
index: 6
frog_bonus_stand
rotate: false
xy: 347, 484
size: 16, 21
orig: 16, 21
offset: 0, 0
index: -1
game_over_box
rotate: false
xy: 2, 577
size: 86, 16
orig: 86, 16
offset: 0, 0
index: -1
number_time_
rotate: false
xy: 163, 706
size: 6, 10
orig: 6, 10
offset: 0, 0
index: 1
number_score_
rotate: false
xy: 37, 706
size: 9, 16
orig: 9, 16
offset: 0, 0
index: 0
label_
rotate: false
xy: 434, 577
size: 20, 10
orig: 20, 10
offset: 0, 0
index: 400
number_level_
rotate: false
xy: 467, 660
size: 10, 16
orig: 10, 16
offset: 0, 0
index: 5
car_
rotate: false
xy: 445, 2
size: 39, 18
orig: 39, 18
offset: 0, 0
index: 5
turtle_
rotate: false
xy: 25, 724
size: 21, 21
orig: 21, 21
offset: 0, 0
index: 2
number_time_
rotate: false
xy: 203, 706
size: 6, 10
orig: 6, 10
offset: 0, 0
index: 6
number_time_
rotate: false
xy: 195, 706
size: 6, 10
orig: 6, 10
offset: 0, 0
index: 5
log_medium
rotate: false
xy: 2, 660
size: 84, 18
orig: 84, 18
offset: 0, 0
index: -1
number_level_
rotate: false
xy: 419, 660
size: 10, 16
orig: 10, 16
offset: 0, 0
index: 1
time_box
rotate: false
xy: 403, 706
size: 86, 16
orig: 86, 16
offset: 0, 0
index: -1
frog_stand
rotate: false
xy: 437, 484
size: 22, 22
orig: 22, 22
offset: 0, 0
index: -1
number_score_
rotate: false
xy: 92, 706
size: 9, 16
orig: 9, 16
offset: 0, 0
index: 5
log_large
rotate: false
xy: 334, 621
size: 129, 18
orig: 129, 18
offset: 0, 0
index: -1
number_score_
rotate: false
xy: 125, 706
size: 9, 16
orig: 9, 16
offset: 0, 0
index: 8
number_level_
rotate: false
xy: 455, 660
size: 10, 16
orig: 10, 16
offset: 0, 0
index: 4
log_small
rotate: false
xy: 88, 660
size: 60, 18
orig: 60, 18
offset: 0, 0
index: -1
car_
rotate: false
xy: 373, 2
size: 23, 25
orig: 23, 25
offset: 0, 0
index: 2
frog_target
rotate: false
xy: 461, 484
size: 23, 29
orig: 23, 29
offset: 0, 0
index: -1
control
rotate: false
xy: 2, 484
size: 92, 91
orig: 92, 91
offset: 0, 0
index: -1
number_score_
rotate: false
xy: 26, 706
size: 9, 16
orig: 9, 16
offset: 0, 0
index: -1
new_level_box
rotate: false
xy: 307, 660
size: 86, 15
orig: 86, 15
offset: 0, 0
index: -1
number_score_
rotate: false
xy: 48, 706
size: 9, 16
orig: 9, 16
offset: 0, 0
index: 1
number_level_
rotate: false
xy: 407, 660
size: 10, 16
orig: 10, 16
offset: 0, 0
index: 0
label_
rotate: false
xy: 412, 577
size: 20, 10
orig: 20, 10
offset: 0, 0
index: 200
turtle_
rotate: false
xy: 2, 724
size: 21, 21
orig: 21, 21
offset: 0, 0
index: 1
number_level_
rotate: false
xy: 14, 706
size: 10, 16
orig: 10, 16
offset: 0, 0
index: 9
bg
rotate: false
xy: 26, 2
size: 320, 480
orig: 320, 480
offset: 0, 0
index: -1
number_time_
rotate: false
xy: 211, 706
size: 6, 10
orig: 6, 10
offset: 0, 0
index: 7
label_instructions
rotate: false
xy: 104, 621
size: 101, 37
orig: 101, 37
offset: 0, 0
index: -1
number_score_
rotate: false
xy: 59, 706
size: 9, 16
orig: 9, 16
offset: 0, 0
index: 2
number_time_
rotate: false
xy: 171, 706
size: 6, 10
orig: 6, 10
offset: 0, 0
index: 2
number_time_
rotate: false
xy: 147, 706
size: 6, 10
orig: 6, 10
offset: 0, 0
index: -1
time_bar
rotate: false
xy: 235, 706
size: 166, 9
orig: 166, 9
offset: 0, 0
index: -1
grass
rotate: false
xy: 90, 577
size: 320, 42
orig: 320, 42
offset: 0, 0
index: -1
death_
rotate: false
xy: 234, 484
size: 17, 22
orig: 17, 22
offset: 0, 0
index: 1
croc_
rotate: false
xy: 165, 484
size: 67, 29
orig: 67, 29
offset: 0, 0
index: 2
frog_jump
rotate: false
xy: 365, 484
size: 22, 22
orig: 22, 22
offset: 0, 0
index: -1
alligator
rotate: false
xy: 2, 2
size: 22, 29
orig: 22, 29
offset: 0, 0
index: -1
frog_side
rotate: false
xy: 389, 484
size: 22, 22
orig: 22, 22
offset: 0, 0
index: -1
number_score_
rotate: false
xy: 136, 706
size: 9, 16
orig: 9, 16
offset: 0, 0
index: 9
death_
rotate: false
xy: 291, 484
size: 17, 22
orig: 17, 22
offset: 0, 0
index: 4
number_level_
rotate: false
xy: 443, 660
size: 10, 16
orig: 10, 16
offset: 0, 0
index: 3
frog_side_jump
rotate: false
xy: 413, 484
size: 22, 22
orig: 22, 22
offset: 0, 0
index: -1
car_
rotate: false
xy: 398, 2
size: 21, 22
orig: 21, 22
offset: 0, 0
index: 3
number_level_
rotate: false
xy: 2, 706
size: 10, 16
orig: 10, 16
offset: 0, 0
index: 8
number_time_
rotate: false
xy: 219, 706
size: 6, 10
orig: 6, 10
offset: 0, 0
index: 8
label_how_to
rotate: false
xy: 2, 621
size: 100, 17
orig: 100, 17
offset: 0, 0
index: -1
number_score_
rotate: false
xy: 70, 706
size: 9, 16
orig: 9, 16
offset: 0, 0
index: 3
number_time_
rotate: false
xy: 179, 706
size: 6, 10
orig: 6, 10
offset: 0, 0
index: 3
number_level_
rotate: false
xy: 431, 660
size: 10, 16
orig: 10, 16
offset: 0, 0
index: 2
number_score_
rotate: false
xy: 103, 706
size: 9, 16
orig: 9, 16
offset: 0, 0
index: 6
number_time_
rotate: false
xy: 155, 706
size: 6, 10
orig: 6, 10
offset: 0, 0
index: 0
 */