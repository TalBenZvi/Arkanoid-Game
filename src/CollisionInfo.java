/**
 * @author Tal Ben-zvi 213420003 talbz03@gmail.com
 */
public class CollisionInfo {
    private Point collisionPoint;
    private Collidable collisionObject;

    /**
     * This is a constructor method that initiates a CollisionInfo object using a collision point and a coliidable.
     *
     * @param collisionPoint the collision point
     * @param collisionObject the coliidable object
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = new Point(collisionPoint.getX(), collisionPoint.getY());
        this.collisionObject = collisionObject;
    }

    /**
     * This is a method that returns this CollisionInfo's collision point.
     *
     * @return this CollisionInfo's collision point
     */
    public Point collisionPoint() {
        return new Point(this.collisionPoint.getX(), this.collisionPoint.getY());
    }

    /**
     * This is a method that returns this CollisionInfo's collision object.
     *
     * @return this CollisionInfo's collision object
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }
}
