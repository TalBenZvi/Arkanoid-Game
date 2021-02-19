import biuoop.DrawSurface;

import java.awt.*;

/**
 * @author Tal Ben-zvi 213420003 talbz03@gmail.com
 */
public class LivesIndicator implements Sprite {
    private Counter currentLives;

    private static final int TEXT_X = 5;
    private static final int TEXT_Y = 20;
    private static final int TEXT_SIZE = 20;

    /**
     * This is a constructor method that initiates a LivesIndicator object using a given counter.
     *
     * @param currentLives the given counter
     */
    public LivesIndicator(Counter currentLives) {
        this.currentLives = currentLives;
    }

    /**
     * This is a method that returns this lives indicator's current lives counter.
     *
     * @return this lives indicator's current lives counter
     */
    public Counter getCurrentLives() {
        return this.currentLives;
    }

    /**
     * This is a method that draws a sprite on a given surface.
     * <p>
     * The method assumes that the given draw surface does not equal null
     * </p>
     * @param d the given surface
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawText(TEXT_X, TEXT_Y, "Lives: " + this.getCurrentLives().getValue(), TEXT_SIZE);
    }

    /**
     * This is a method that notifies this sprite that time has passed.
     */
    public void timePassed() { }

    /**
     * This is a method that adds this sprite to a given level.
     * <p>
     * The method assumes that the given level does not equal null
     * </p>
     * @param g the given level
     */
    public void addToLevel(GameLevel g) {
        g.addSprite(this);
    }
}
