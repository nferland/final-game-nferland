package cegepst.engine.graphics;

import cegepst.engine.controls.Direction;
import cegepst.engine.entities.StaticEntity;

import java.awt.*;

public class Animator {

    public static Image draw(Direction direction, MovementAnimations movementAnimations, int currentAnimationFrame) {
        if (direction == Direction.RIGHT) {
            return movementAnimations.getRightFrames()[currentAnimationFrame];
        } else if (direction == Direction.LEFT) {
            return movementAnimations.getLeftFrames()[currentAnimationFrame];
        } else if (direction == Direction.UP) {
            return movementAnimations.getUpFrames()[currentAnimationFrame];
        } else if (direction == Direction.DOWN) {
            return movementAnimations.getDownFrames()[currentAnimationFrame];
        }
        return movementAnimations.getDownFrames()[1];
    }

    public static void animate(boolean moved, Animations animations) {
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
            animations.setCurrentAnimationFrame(1);
        }
        animations.setNextFrame(nextFrame);
    }
}
