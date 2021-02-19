import biuoop.DrawSurface;

import java.util.Random;

/**
 * @author Tal Ben-zvi 213420003 talbz03@gmail.com
 */
public class Ball implements Sprite {
    private Point point;
    private int size;
    private java.awt.Color color;
    private Velocity velocity;
    private GameEnvironment gameEnvironment;

    /**
     * This is a constructor method that initiates a Ball object using a point, a radius and a color.
     *
     * @param center the ball's center point
     * @param r the ball's radius
     * @param color the ball's color
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.point = new Point(center.getX(), center.getY());
        this.size =  r;
        this.color = color;
        this.velocity = new Velocity(0, 0);
    }

    /**
     * This is a constructor method that initiates a Ball object using 2 coordinates, a radius and a color.
     *
     * @param x the ball's center point x
     * @param y the ball's center point y
     * @param r the ball's radius
     * @param color the ball's color
     */
    public Ball(double x, double y, int r, java.awt.Color color) {
        this.point = new Point(x, y);
        this.size =  r;
        this.color = color;
        this.velocity = new Velocity(0, 0);
    }

    /**
     * This is a method that sets this ball's center point to one made using given coordinates.
     *
     * @param x the given x
     * @param y the given y
     */
    public void setCenter(double x, double y) {
        this.point = new Point(x, y);
    }

    /**
     * This is a method that sets this ball's center point to a given one.
     *
     * @param center the given point
     */
    public void setCenter(Point center) {
        this.point = new Point(center.getX(), center.getY());
    }

    /**
     * This is a method that sets this ball's velocity to a given one.
     *
     * @param v the given velocity
     */
    public void setVelocity(Velocity v) {
        this.velocity = new Velocity(v.getDX(), v.getDY());
    }

    /**
     * This is a method that sets this ball's velocity to one made using given dx and dy.
     *
     * @param dx the given dx
     * @param dy the given dy
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * This is a method that sets this ball's velocity to one chosen according to its size.
     * <p>
     * Balls sized 1-50 receive a speed between 1-10.8, the larger the ball is, the slower it will be.
     * Balls sized above 50 receive a speed of 1 (same as size 50).
     * The velocity's angle is chosen at random.
     * </p>
     */
    public void setBallVelocityBySize() {
        Random rand = new Random();
        if (this.getSize() >= 50) {
            this.setVelocity(Velocity.fromAngleAndSpeed(rand.nextInt(360), 1));
        } else {
            //The velocity's speed is set using the formula: speed = 11 - 0.2 * size
            this.setVelocity(Velocity.fromAngleAndSpeed(rand.nextInt(360), 11 - 0.2 * this.getSize()));
        }
    }

    /**
     * This is a method that sets this ball's game environment to a given one.
     *
     * @param environment the given environment
     */
    public void setGameEnvironment(GameEnvironment environment) {
        this.gameEnvironment = environment;
    }

    /**
     * This is a method that returns this ball's center point.
     *
     * @return this ball's center point
     */
    public Point getCenter() {
        return new Point(this.point.getX(), this.point.getY());
    }

    /**
     * This is a method that returns this ball's center point's x coordinate.
     *
     * @return this ball's center point's x coordinate
     */
    public int getX() {
        return (int) this.getCenter().getX();
    }

    /**
     * This is a method that returns this ball's center point's x coordinate.
     *
     * @return this ball's center point's x coordinate
     */
    public int getY() {
        return (int) this.getCenter().getY();
    }

    /**
     * This is a method that returns this ball's size.
     *
     * @return this ball's size
     */
    public int getSize() {
        return this.size;
    }

    /**
     * This is a method that returns this ball's color.
     *
     * @return this ball's color
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * This is a method that returns this ball's velocity.
     *
     * @return this ball's velocity
     */
    public Velocity getVelocity() {
        return new Velocity(this.velocity.getDX(), this.velocity.getDY());
    }

    /**
     * This is a method that returns this ball's game environment.
     *
     * @return this ball's game environment
     */
    public GameEnvironment getGameEnvironment() {
        return this.gameEnvironment;
    }

    /**
     * This is a method that moves this ball based on its velocity and collidable objects in its game environment.
     * <p>
     * If there are no collidable objects in this ball's trajectory, the method applies this ball's velocity to its
     * center point. If there are, the method moves the ball until it almost hits the closest collidable object
     * and then updates its velocity according to the object hit and the collision point
     * </p>
     */
    public void moveOneStep() {
        //The method calculates this ball's trajectory by applying its velocity to its center point
        Line trajectory = new Line(this.getCenter(), this.getVelocity().applyToPoint(this.getCenter()));
        CollisionInfo collisionInfo = this.getGameEnvironment().getClosestCollision(trajectory);
        if (collisionInfo == null) {
            this.setCenter(this.getVelocity().applyToPoint(this.getCenter()));
        } else {
            /*
            The method uses the Line class' 'middle' method twice in order to bring this ball to 3/4 of the way towards
            the collision point
             */
            this.setCenter(new Line(new Line(this.getCenter(), collisionInfo.collisionPoint()).middle(),
                    collisionInfo.collisionPoint()).middle());
            this.setVelocity(collisionInfo.collisionObject().hit(this, collisionInfo.collisionPoint(),
                    this.getVelocity()));
        }
    }

    /**
     * This is a method that draws this ball on a given surface.
     * <p>
     * The method assumes that the given draw surface does not equal null
     * </p>
     * @param d the given surface
     */
    public void drawOn(DrawSurface d) {
        d.setColor(this.getColor());
        d.fillCircle(this.getX(), this.getY(), this.getSize());
        d.setColor(java.awt.Color.BLACK);
        d.drawCircle(this.getX(), this.getY(), this.getSize());
    }

    /**
     * This is a method that notifies this ball that time has passed.
     */
    public void timePassed() {
        this.moveOneStep();
    }

    /**
     * This is a method that adds this ball to a given level.
     * <p>
     * The method assumes that the given level does not equal null
     * </p>
     * @param g the given level
     */
    public void addToLevel(GameLevel g) {
        g.addSprite(this);
        g.addBall(this);
        this.setGameEnvironment(g.getEnvironment());
    }

    /**
     * This is a method that removes this ball from a given level.
     * <p>
     * The method assumes that the given level does not equal null
     * </p>
     * @param g the given level
     */
    public void removeFromLevel(GameLevel g) {
        g.removeSprite(this);
        g.removeBall(this);
    }
}
