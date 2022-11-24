package cegepst.engine.entities.stateMachines;

public enum SpellState {
    Idle {
        @Override
        public SpellState nextState(boolean isActive) {
            return (isActive)? this : Expired;
        }
    },
    Traveling {
        @Override
        public SpellState nextState(boolean isActive) {
            return (isActive)? Traveling : Expired;
        }
    },
    Expired {
        @Override
        public SpellState nextState(boolean isActive) {
            return (isActive)? this : Expired;
        }
    };

    public abstract SpellState nextState(boolean isActive);
}
