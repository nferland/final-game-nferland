package cegepst.engine.graphics;

import cegepst.engine.controls.Direction;
import cegepst.engine.entities.StaticEntity;

import java.awt.*;

public class Animator {

    public static Image draw(Direction direction, Animations animations, int currentAnimationFrame) {
        switch (direction){
            case RIGHT:
                return animations.getRightFrames()[currentAnimationFrame];
            case DOWN:
                return animations.getDownFrames()[currentAnimationFrame];
            case LEFT:
                return animations.getLeftFrames()[currentAnimationFrame];
            case UP:
                return animations.getUpFrames()[currentAnimationFrame];
        }
        return animations.getDownFrames()[1];
    }

    public static void animate(boolean moved, Animations animations, int defaultFrame) {
        int nextFrame = animations.getNextFrame();
        if (moved) {
            --nextFrame;
            if (nextFrame == 0) {
                animations.setCurrentAnimationFrame(animations.getCurrentAnimationFrame() + 1);
                if ( animations.getCurrentAnimationFrame() >= animations.getAnimationLength()) {
                    animations.setCurrentAnimationFrame(0);
                }
                nextFrame = StaticEntity.ANIMATION_SPEED;
            }
        } else {
            animations.setCurrentAnimationFrame(defaultFrame);
        }
        animations.setNextFrame(nextFrame);
    }
}
