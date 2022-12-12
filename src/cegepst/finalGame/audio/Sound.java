package cegepst.finalGame.audio;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public enum Sound {

    FIRE("fire.wav"),
    FIREBALL("fireball.wav"),
    FIREBALL_HIT("fireball-hit.wav"),
    ICE_BURST("ice-burst.wav"),
    PLAYER_ATTACK1("player-attack1.wav"),
    PLAYER_ATTACK2("player-attack2.wav"),
    PLAYER_ATTACK3("player-attack3.wav"),
    PLAYER_DEATH("player-death.wav"),
    DASH("dash.wav"),
    PLAYER_HURT("player-hurt.wav"),
    ZOMBIE_DEATH("murlock.wav"),
    LICH_HURT("lich-hurt.wav"),
    LICH_DEATH("lich-death.wav");

    private final String audioResourceName;

    Sound(String audioResourceName) {
        this.audioResourceName = audioResourceName;
    }

    public void play() {
        try {
            Clip clip = AudioSystem.getClip();
            AudioInputStream stream = AudioSystem.getAudioInputStream(
                    this.getClass().getClassLoader().getResourceAsStream("audios/" + this.audioResourceName));
            clip.open(stream);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
