package cegepst.engine.entities;

public enum MovableEntityState {
    Idle {
        @Override
        public MovableEntityState nextState() {
            return this;
        }
    },
    Moving {
        @Override
        public MovableEntityState nextState() {
            return Idle;
        }
    },
    Hurt {
        @Override
        public MovableEntityState nextState() {
            return Invulnerable;
        }
    },
    Invulnerable {
        @Override
        public MovableEntityState nextState() {
          return Idle;
        }
    },
    Attacking {
        @Override
        public MovableEntityState nextState() {
            return Idle;
        }
    },
    Dashing {
        @Override
        public MovableEntityState nextState() {
            return Idle;
        }
    };

    public abstract MovableEntityState nextState();
}
