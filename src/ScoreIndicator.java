import biuoop.DrawSurface;

import java.awt.*;

/**
 * @author Tal Ben-zvi 213420003 talbz03@gmail.com
 */
public class ScoreIndicator implements Sprite {
    private Counter currentScore;

    private static final int TEXT_X = 300;
    private static final int TEXT_Y = 20;
    private static final int TEXT_SIZE = 20;

    /**
     * This is a constructor method that initiates a ScoreIndicator object using a given counter.
     *
     * @param currentScore the given counter
     */
    public ScoreIndicator(Counter currentScore) {
        this.currentScore = currentScore;
    }

    /**
     * This is a method that returns this score indicator's current score counter.
     *
     * @return this score indicator's current score counter
     */
    public Counter getCurrentScore() {
        return this.currentScore;
    }

    /**
     * This is a method that prints this score indicator's score on a given surface.
     * <p>
     * The method assumes that the given draw surface does not equal null
     * </p>
     * @param d the given surface
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawText(TEXT_X, TEXT_Y, "Score: " + this.getCurrentScore().getValue(), TEXT_SIZE);
    }

    /**
     * This is a method that notifies this score indicator that time has passed.
     */
    public void timePassed() { }

    /**
     * This is a method that adds this score indicator to a given level.
     * <p>
     * The method assumes that the given level does not equal null
     * </p>
     * @param g the given level
     */
    public void addToLevel(GameLevel g) {
        g.addSprite(this);
    }
}
