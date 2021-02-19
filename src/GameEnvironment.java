import java.util.ArrayList;
import java.util.List;

/**
 * @author Tal Ben-zvi 213420003 talbz03@gmail.com
 */
public class GameEnvironment {
    private List<Collidable> collidables;

    /**
     * This is a constructor method that initiates a GameEnvironment object.
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<Collidable>();
    }

    /**
     * This is a method that returns this game environment's collidables.
     *
     * @return this game environment's collidables
     */
    public List<Collidable> getCollidables() {
        return this.collidables;
    }

    /**
     * This is a method that adds a given collidable to this game environment's collidables.
     * <p>
     * The method assumes that the given collidable does not equal null
     * </p>
     * @param c the given collidable
     */
    public void addCollidable(Collidable c) {
        this.getCollidables().add(c);
    }

    /**
     * This is a method that removes a given collidable from this game environment's collidables.
     * <p>
     * The method assumes that the given collidable does not equal null
     * </p>
     * @param c the given collidable
     */
    public void removeCollidable(Collidable c) {
        this.getCollidables().remove(c);
    }

    /**
     * This is a method that returns the closest collision of a given trajectory with a collidable in this environment.
     * <p>
     * The method assumes that the given trajectory does not equal null
     * </p>
     * @param trajectory the given trajectory
     * @return collision info about the closest collision
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        Point collisionPoint = null;
        Collidable collisionObject = null;
        List<Collidable> gameCollidables = new ArrayList<>(this.getCollidables());
        for (Collidable c : gameCollidables) {
            Point p = trajectory.closestIntersectionToStartOfLine(c.getCollisionRectangle());
            if (p != null && (collisionPoint == null || new Line(trajectory.start(), collisionPoint).contains(p))) {
                collisionPoint = p;
                collisionObject = c;
            }
        }
        if (collisionPoint == null) {
            return null;
        }
        return new CollisionInfo(collisionPoint, collisionObject);
    }
}
