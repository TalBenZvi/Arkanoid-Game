import biuoop.DrawSurface;
import biuoop.GUI;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Tal Ben-zvi 213420003 talbz03@gmail.com
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle rectangle;
    private Background background;
    private Color borderColor;
    private List<HitListener> hitListeners;

    private static final String WIDTH_KEY = "width";
    private static final String HEIGHT_KEY = "height";
    private static final String FILL_KEY = "fill";
    private static final String BORDER_COLOR_KEY = "stroke";

    /**
     * This is a constructor method that initiates a Block object using a rectangle and a color.
     *
     * @param rectangle the block's collision rectangle
     * @param background the block's background
     */
    public Block(Rectangle rectangle, Background background) {
        this.rectangle = new Rectangle(rectangle.getUpperLeft(), rectangle.getWidth(), rectangle.getHeight());
        this.background = background;
        this.borderColor = null;
        this.hitListeners = new ArrayList<>();
    }

    /**
     * This is a constructor method that initiates a Block object using a point, its width, its height and its color.
     *
     * @param upperLeft the block's upper left point
     * @param width the block's width
     * @param height the block's height
     * @param background the block's background
     */
    public Block(Point upperLeft, double width, double height, Background background) {
        this.rectangle = new Rectangle(upperLeft, width, height);
        this.background = background;
        this.borderColor = null;
        this.hitListeners = new ArrayList<>();
    }

    public Block(Point upperLeft, double width, double height, Color color) {
        this.rectangle = new Rectangle(upperLeft, width, height);
        String colorString = "color(RGB(" + color.getRed() + "," + color.getGreen() + "," + color.getBlue() + "))";
        this.background = new Background(colorString);
        this.borderColor = null;
        this.hitListeners = new ArrayList<>();
    }

    public Block(Rectangle rectangle, Color color) {
        this.rectangle = new Rectangle(rectangle.getUpperLeft(), rectangle.getWidth(), rectangle.getHeight());
        String colorString = "color(RGB(" + color.getRed() + "," + color.getGreen() + "," + color.getBlue() + "))";
        this.background = new Background(colorString);
        this.borderColor = null;
        this.hitListeners = new ArrayList<>();
    }

    public Block(int x, int y, Map<String, String> blockType) {
        this.rectangle = new Rectangle(new Point(x, y), Double.parseDouble(blockType.get(WIDTH_KEY)),
                Double.parseDouble(blockType.get(HEIGHT_KEY)));
        this.background = new Background(blockType.get(FILL_KEY));
        if (blockType.containsKey(BORDER_COLOR_KEY)) {
            String borderColorString = blockType.get(BORDER_COLOR_KEY);
            this.setBorderColor(Background.colorFromString(borderColorString.substring(borderColorString.indexOf('(')
                            + 1, borderColorString.indexOf(')'))));
        }
        this.hitListeners = new ArrayList<>();
    }

    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
    }

    /**
     * This is a method that returns this block's collision rectangle.
     *
     * @return this block's collision rectangle
     */
    public Rectangle getCollisionRectangle() {
        return new Rectangle(this.rectangle.getUpperLeft(), this.rectangle.getWidth(), this.rectangle.getHeight());
    }

    /**
     * This is a method that returns this block's fill color.
     *
     * @return this block's fill color
     */
    public Background getBackground() {
        return this.background;
    }

    /**
     * This is a method that returns this block's border color.
     *
     * @return this block's border color
     */
    public Color getBorderColor() {
        return this.borderColor;
    }

    /**
     * This is a method that returns this block's list of hit listeners.
     *
     * @return this block's list of hit listeners
     */
    public List<HitListener> getHitListeners() {
        return this.hitListeners;
    }

    /**
     * This is a method that notifies all of this block's listeners that it was hit by a given ball.
     *
     * @param hitter the given ball
     */
    private void notifyHit(Ball hitter) {
        List<HitListener> listeners = new ArrayList<>(this.getHitListeners());
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * This is a method that returns the velocity a given ball gets after colliding with this block
     * <p>
     * The method returns the velocity an object gets after colliding with this block at a given point with a given
     * velocity. If the object collided with this block's top or bottom edge its velocity is reversed vertically.
     * If the object collided with this block's left or right edge its velocity is reversed horizontally.
     * Additionally, this method notifies all of this block's listeners that it was hit by the given ball.
     * The method assumes that the given point and the given velocity do not equal null
     * </p>
     * @param hitter the given ball
     * @param collisionPoint the collision point
     * @param currentVelocity the given ball's velocity before the collision
     * @return the object's velocity after the collision
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        this.notifyHit(hitter);
        Velocity toReturn = new Velocity(currentVelocity.getDX(), currentVelocity.getDY());
        if (this.getCollisionRectangle().getLeftEdge().contains(collisionPoint)
                || this.getCollisionRectangle().getRightEdge().contains(collisionPoint)) {
            toReturn.reverseX();
        }
        if (this.getCollisionRectangle().getTopEdge().contains(collisionPoint)
                || this.getCollisionRectangle().getBottomEdge().contains(collisionPoint)) {
            toReturn.reverseY();
        }
        return toReturn;
    }

    /**
     * This is a method that draws this block on a given surface.
     * <p>
     * The method assumes that the given draw surface does not equal null
     * </p>
     * @param d the given surface
     */
    public void drawOn(DrawSurface d) {
        this.getBackground().drawOn(d, this.getCollisionRectangle());
        if (this.getBorderColor() != null) {
            this.getCollisionRectangle().drawFrameOn(d, this.getBorderColor());
        }
    }

    /**
     * This is a method that notifies this block that time has passed.
     */
    public void timePassed() { }

    /**
     * This is a method that adds this block to a given level.
     * <p>
     * The method assumes that the given level does not equal null
     * </p>
     * @param g the given level
     */
    public void addToLevel(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }

    /**
     * This is a method that removes this block from a given level.
     * <p>
     * The method assumes that the given level does not equal null
     * </p>
     * @param g the given level
     */
    public void removeFromLevel(GameLevel g) {
        g.removeCollidable(this);
        g.removeSprite(this);
    }

    /**
     * This is a method that adds a given hit listener as a listener to hit events.
     *
     * @param hl the given hit listener
     */
    public void addHitListener(HitListener hl) {
        this.getHitListeners().add(hl);
    }

    /**
     * This is a method that removes a given hit listener as a listener to hit events.
     *
     * @param hl the given hit listener
     */
    public void removeHitListener(HitListener hl) {
        this.getHitListeners().remove(hl);
    }

    public static void main(String[] args) {
        GUI gui = new GUI("Test", 800, 600);
        DrawSurface d = gui.getDrawSurface();
        Block block;
        List<Map<String, String>> blockTypes =
             BlockDefinitionsFileReader.getBlockTypes("resources/resources/definitions/standard_block_definitions.txt");
        int x = 0;
        for (Map<String, String> blockType : blockTypes) {
            block = new Block(x, 50, blockType);
            block.drawOn(d);
            x = x + 60;
        }
        gui.show(d);
    }
}
