import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Tal Ben-zvi 213420003 talbz03@gmail.com
 */
public class SpriteCollection {
    private List<Sprite> sprites;

    /**
     * This is a constructor method that initiates a SpriteCollection object.
     */
    public SpriteCollection() {
        this.sprites = new ArrayList<Sprite>();
    }

    /**
     * This is a method that returns this sprite collection's sprites.
     *
     * @return this sprite collection's sprites
     */
    public List<Sprite> getSprites() {
        return this.sprites;
    }

    /**
     * This is a method that adds a given sprite to this sprite collection's sprites.
     * <p>
     * The method assumes that the given sprite does not equal null
     * </p>
     * @param s the given sprite
     */
    public void addSprite(Sprite s) {
        this.getSprites().add(s);
    }

    /**
     * This is a method that removes a given sprite from this sprite collection's sprites.
     * <p>
     * The method assumes that the given sprite does not equal null
     * </p>
     * @param s the given sprite
     */
    public void removeSprite(Sprite s) {
        this.getSprites().remove(s);
    }

    /**
     * This is a method that notifies all sprites in this sprite collection that time has passed.
     */
    public void notifyAllTimePassed() {
        List<Sprite> spritesInCollection = new ArrayList<>(this.getSprites());
        for (Sprite sprite : spritesInCollection) {
            sprite.timePassed();
        }
    }

    /**
     * This is a method that draws all the sprites in this sprite collection on a given surface.
     * <p>
     * The method assumes that the given draw surface does not equal null
     * </p>
     * @param d the given surface
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite sprite : this.getSprites()) {
            sprite.drawOn(d);
        }
    }
}
