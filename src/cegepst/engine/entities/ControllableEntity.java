package cegepst.engine.entities;

import cegepst.engine.controls.Direction;
import cegepst.engine.controls.MovementController;
import cegepst.engine.entities.stateMachines.AttackState;

public abstract class ControllableEntity extends MovableEntity {

    private final MovementController controller;

    public ControllableEntity(MovementController controller) {
        this.controller = controller;
    }

    public void moveWithController() {
        if (isDashing()){
            move(this.getDirection());
            return;
        }
        Direction direction = controller.getMoveDirection();
        if (direction != null) {
            move(direction);
        }
    }
}
