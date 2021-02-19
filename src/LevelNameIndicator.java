import biuoop.DrawSurface;

import java.awt.*;

/**
 * @author Tal Ben-zvi 213420003 talbz03@gmail.com
 */
public class LevelNameIndicator implements Sprite {
    private String levelName;

    private static final int TEXT_X = 550;
    private static final int TEXT_Y = 20;
    private static final int TEXT_SIZE = 20;

    /**
     * This is a constructor method that initiates a LevelNameIndicator object using a given level name.
     *
     * @param levelName the given level name
     */
    public LevelNameIndicator(String levelName) {
        this.levelName = levelName;
    }

    /**
     * This is a method that returns this level name indicator's level name.
     *
     * @return this level name indicator's level name
     */
    public String getLevelName() {
        return this.levelName;
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
        d.drawText(TEXT_X, TEXT_Y, "Level Name: " + this.getLevelName(), TEXT_SIZE);
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
