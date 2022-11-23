package cegepst.engine.entities.stateMachines;

public enum AttackState {
    Idle {
        @Override
        public AttackState nextState(boolean isAttacking) {
            if(isAttacking) {
                return Melee;
            }
            return this;
        }
    },
    Melee {
        @Override
        public AttackState nextState(boolean isAttacking) {
            if (isAttacking) {
                return this;
            }
            return Idle;
        }
    },
    Range {
        @Override
        public AttackState nextState(boolean isAttacking) {
            return Idle;
        }
    };

    public abstract AttackState nextState(boolean isAttacking);
}
