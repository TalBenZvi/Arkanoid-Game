import biuoop.DrawSurface;

/**
 * @author Tal Ben-zvi 213420003 talbz03@gmail.com
 */
public interface Sprite {

    /**
     * This is a method that draws a sprite on a given surface.
     * <p>
     * The method assumes that the given draw surface does not equal null
     * </p>
     * @param d the given surface
     */
    void drawOn(DrawSurface d);

    /**
     * This is a method that notifies this sprite that time has passed.
     */
    void timePassed();

    /**
     * This is a method that adds this sprite to a given level.
     * <p>
     * The method assumes that the given level does not equal null
     * </p>
     * @param g the given level
     */
    void addToLevel(GameLevel g);
}
