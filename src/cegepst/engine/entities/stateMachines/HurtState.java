package cegepst.engine.entities.stateMachines;

import cegepst.engine.GameTime;

public enum HurtState {

    NotHurt {
        @Override
        public HurtState nextState() {
            return this;
        }
    },
    Hurt {
        @Override
        public HurtState nextState() {
            Invulnerable.setInvulnerabilityStart();
            return Invulnerable;
        }
    },
    Invulnerable {

        @Override
        public HurtState nextState() {
            if(isInvulnerable()) {
                return this;
            }
            return NotHurt;
        }

        private boolean isInvulnerable() {
            return GameTime.getCurrentTime() - Invulnerable.invulnerabilityStart < Invulnerable.invulnerabilityLenght;
        }
    },
    Dead {
        @Override
        public HurtState nextState() {
            return this;
        }
    };

    private long invulnerabilityLenght = 90;
    private long invulnerabilityStart = 0L;

    public abstract HurtState nextState();

    public void setInvulnerabilityStart() {
        this.invulnerabilityStart = GameTime.getCurrentTime();
    }
}
