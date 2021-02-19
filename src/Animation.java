import biuoop.DrawSurface;

/**
 * @author Tal Ben-zvi 213420003 talbz03@gmail.com
 */
public interface Animation {

    /**
     * This is a method that draws one frame of the animation on a given draw surface.
     *
     * @param d the given draw surface
     */
    void doOneFrame(DrawSurface d);

    /**
     * This is a method that return whether or not the animation should stop.
     *
     * @return true if the animation should stop
     */
    boolean shouldStop();
}