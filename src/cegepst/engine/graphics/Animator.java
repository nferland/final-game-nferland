package cegepst.engine.graphics;

import cegepst.engine.controls.Direction;
import cegepst.engine.entities.StaticEntity;

import java.awt.*;

public class Animator {

    public static Image draw(Direction direction, AnimationFrames animationFrames, int currentAnimationFrame) {
        if (direction == Direction.RIGHT) {
            return animationFrames.getRightFrames()[currentAnimationFrame];
        } else if (direction == Direction.LEFT) {
            return animationFrames.getLeftFrames()[currentAnimationFrame];
        } else if (direction == Direction.UP) {
            return animationFrames.getUpFrames()[currentAnimationFrame];
        } else if (direction == Direction.DOWN) {
            return animationFrames.getDownFrames()[currentAnimationFrame];
        }
        return animationFrames.getDownFrames()[1];
    }

    public static void animate(boolean moved, AnimationFrames animationFrames) {
        int nextFrame = animationFrames.getNextFrame();
        if (moved) {
            --nextFrame;
            if (nextFrame == 0) {
                animationFrames.setCurrentAnimationFrame(animationFrames.getCurrentAnimationFrame() + 1);
                if ( animationFrames.getCurrentAnimationFrame() >= animationFrames.getAnimationLength()) {
                    animationFrames.setCurrentAnimationFrame(0);
                }
                animationFrames.setNextFrame(StaticEntity.ANIMATION_SPEED);
            }
        } else {
            animationFrames.setCurrentAnimationFrame(1);
        }
    }
}
