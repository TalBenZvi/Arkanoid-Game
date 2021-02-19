/**
 * @author Tal Ben-zvi 213420003 talbz03@gmail.com
 */
public interface Collidable {

    /**
     * This is a method that returns the collidable's collision rectangle.
     *
     * @return the collidable's collision rectangle
     */
    Rectangle getCollisionRectangle();

    /**
     * This is a method that returns the velocity a given ball gets after colliding with this collidable.
     * <p>
     * The method returns the velocity a given ball gets after colliding with this collidable at a given point with a
     * given velocity.
     * The method assumes that the given point and the given velocity do not equal null
     * </p>
     * @param hitter the given ball
     * @param collisionPoint the collision point
     * @param currentVelocity the object's velocity before the collision
     * @return the object's velocity after the collision
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
