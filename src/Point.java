/**
 * @author Tal Ben-zvi 213420003 talbz03@gmail.com
 */
public class Point {
    private static final double EPSILON = 0.0000000001;
    private double x;
    private double y;

    /**
     * This is a constructor method that initiates a Point object using 2 coordinates.
     *
     * @param x point x
     * @param y point y
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * This is a method that receives two doubles and returns true if they are equal.
     *
     * Two doubles are equal if the difference between them is smaller than EPSILON = 10^-10, or if they are both null;
     *
     * @param d1 the first double
     * @param d2 the second double
     * @return true if the doubles are equal, false if not
     */
    private static boolean doubleEquals(Double d1, Double d2) {
        if (d1 == null && d2 == null) {
            return true;
        }
        if (d1 == null || d2 == null) {
            return false;
        }
        return Math.abs(d1 - d2) < EPSILON;
    }

    /**
     * This is a method that returns this point's distance from a given point.
     * <p>
     * The method assumes that the given point does not equal null.
     * </p>
     * @param other the given point
     * @return the distance from the given point
     */
    public double distance(Point other) {
        return Math.sqrt((this.getX() - other.getX()) * (this.getX() - other.getX())
                + (this.getY() - other.getY()) * (this.getY() - other.getY()));
    }

    /**
     * This is a method that returns true if a given point is equal to this point.
     * <p>
     * The method assumes that the given point does not equal null.
     * Two points are equal if they have the same x and y coordinates
     * </p>
     * @param other the given point
     * @return true if the points are equal, false if not
     */
    public boolean equals(Point other) {
        return doubleEquals(this.getX(), other.getX()) && doubleEquals(this.getY(), other.getY());
    }

    /**
     * This is a method that returns this point's x coordinate.
     *
     * @return this point's x coordinate
     */
    public double getX() {
        return this.x;
    }

    /**
     * This is a method that returns this point's y coordinate.
     *
     * @return this point's y coordinate
     */
    public double getY() {
        return this.y;
    }

    /**
     * This is a method that returns the slope of the line between this point and a given point.
     * <p>
     * The method assumes that the given point does not equal null.
     * If the line is vertical, or if the points are equal, the method returns null.
     * </p>
     * @param other the given point
     * @return the slope of the line between the two points
     */
    public Double slopeWith(Point other) {
        if (doubleEquals(this.getX(), other.getX())) {
            return null;
        }
        return ((this.getY() - other.getY()) / (this.getX() - other.getX()));
    }
}
