package cegepst.finalGame;

public class Score {

    private static Score instance;
    private int score;

    public static Score getInstance() {
        if (instance == null) {
            instance = new Score();
        }
        return instance;
    }

    private Score() {
        score = 0;
    }

    public int getScore() {
        return score;
    }

    public void increment(int increment) {
        setScore(score + increment);
    }

    public void setScore(int score) {
        this.score = score;
    }
}
