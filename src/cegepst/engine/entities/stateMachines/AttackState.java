package cegepst.engine.entities.stateMachines;

public enum AttackState {
    Idle {
        @Override
        public AttackState nextState() {
            return this;
        }
    },
    Melee {
        @Override
        public AttackState nextState() {
            return Idle;
        }
    },
    Range {
        @Override
        public AttackState nextState() {
            return Idle;
        }
    };

    public abstract AttackState nextState();
}
