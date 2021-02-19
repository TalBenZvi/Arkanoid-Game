/**
 * @author Tal Ben-zvi 213420003 talbz03@gmail.com
 */
public class Velocity {
    private double dx;
    private double dy;

    /**
     * This is a constructor method that initiates a Velocity object using a dx value and a dy value.
     *
     * @param dx the velocity's dx value
     * @param dy the velocity's dy value
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * This is a method that returns a Velocity object initiated using an angle value and a speed value.
     * <p>
     * The given angle is the angle between the velocity's direction and the positive direction of the x axis.
     * The given speed is the absolute value (length) of the velocity vector
     * </p>
     * @param angle the velocity's angle
     * @param speed the velocity's speed
     * @return the initiated velocity
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = speed * Math.sin(Math.toRadians(angle));
        double dy = -speed * Math.cos(Math.toRadians(angle));
        return new Velocity(dx, dy);
    }

    /**
     * This is a method that sets this velocity's dx value to a given one.
     *
     * @param newDX the given dx value
     */
    public void setDX(double newDX) {
        this.dx = newDX;
    }

    /**
     * This is a method that sets this velocity's dy value to a given one.
     *
     * @param newDY the given dy value
     */
    public void setDY(double newDY) {
        this.dy = newDY;
    }

    /**
     * This is a method that returns this velocity's dx value.
     *
     * @return this velocity's dx value
     */
    public double getDX() {
        return this.dx;
    }

    /**
     * This is a method that returns this velocity's dy value.
     *
     * @return this velocity's dy value
     */
    public double getDY() {
        return this.dy;
    }

    /**
     * This is a method that returns this velocity's angle.
     *
     * @return this velocity's angle
     */
    public double getAngle() {
        return Math.toDegrees(Math.atan(-this.getDX() / this.getDY()));
    }

    /**
     * This is a method that returns this velocity's speed.
     *
     * @return this velocity's speed
     */
    public double getSpeed() {
        return Math.sqrt(this.getDX() * this.getDX() + this.getDY() * this.getDY());
    }

    /**
     * This is a method that reverses this velocity's dx value.
     */
    public void reverseX() {
        this.setDX(-this.getDX());
    }

    /**
     * This is a method that reverses this velocity's dy value.
     */
    public void reverseY() {
        this.setDY(-this.getDY());
    }

    /**
     * This is a method that adds this velocity's dx and dy value to a given point coordinates.
     *
     * @param p the given point
     * @return the point after the change
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + this.getDX(), p.getY() + this.getDY());
    }
}
