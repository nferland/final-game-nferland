package cegepst.engine.entities.stateMachines;

public enum MovementState {
    Idle {
        @Override
        public MovementState nextState() {
            return this;
        }
    },
    Moving {
        @Override
        public MovementState nextState() {
            return this;
        }
    },
    Dashing {
        @Override
        public MovementState nextState() {
            return this;
        }
    };

    public abstract MovementState nextState();
}
