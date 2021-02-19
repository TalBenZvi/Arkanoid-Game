/**
 * @author Tal Ben-zvi 213420003 talbz03@gmail.com
 */
public class Line {
    private static final double EPSILON = 0.0000000001;
    private Point start;
    private Point end;

    /**
     * This is a constructor method that initiates a Line object using 2 points.
     *
     * @param start start point
     * @param end end point
     */
    public Line(Point start, Point end) {
        this.start = new Point(start.getX(), start.getY());
        this.end = new Point(end.getX(), end.getY());
    }

    /**
     * This is a constructor method that initiates a Line object using 4 coordinates.
     *
     * @param x1 start point x
     * @param y1 start point y
     * @param x2 end point x
     * @param y2 end point y
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
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
     * This is a method that returns true if a given number is between 2 other given numbers and false if not.
     *
     * @param num the given number
     * @param edge1 the start of the number range checked
     * @param edge2 the end of the number range checked
     * @return true if num is in the number range, false if not
     */
    private static boolean isBetween(double num, double edge1, double edge2) {
        return (edge1 <= num && num <= edge2) || (edge1 >= num && num >= edge2);
    }

    /**
     * This is a method that returns this line's start point.
     *
     * @return this line's start point
     */
    public Point start() {
        return new Point(this.start.getX(), this.start.getY());
    }

    /**
     * This is a method that returns this line's end point.
     *
     * @return this line's end point
     */
    public Point end() {
        return new Point(this.end.getX(), this.end.getY());
    }

    /**
     * This is a method that returns this line's middle point.
     *
     * @return this line's middle point
     */
    public Point middle() {
        return new Point((this.start().getX() + this.end().getX()) / 2, (this.start().getY() + this.end().getY()) / 2);
    }

    /**
     * This is a method that returns this line's length.
     *
     * @return this line's length
     */
    public double length() {
        return this.start().distance(this.end());
    }

    /**
     * This is a method that returns this line's slope.
     *
     * @return this line's slope
     */
    public Double slope() {
        return this.start().slopeWith(this.end());
    }

    /**
     * This is a method that returns true if a given point is on this line segment and false if not.
     * <p>
     * The method assumes that the given point does not equal null.
     * </p>
     * @param p the given point
     * @return true if p is on this line segment, false if not
     */
    public boolean contains(Point p) {
        if (this.start().equals(p)) {
            return true;
        }
        if (this.start().slopeWith(p) == null && this.slope() == null) {
            return doubleEquals(this.start().getX(), p.getX())
                    && isBetween(p.getY(), this.start().getY(), this.end().getY());
        }
        /*
        The method checks that p is on the same linear equation as the line by comparing the slope between p and the
        line's start point to the line's slope, it also checks if p is between the line's edges
         */
        return doubleEquals(this.start().slopeWith(p), (this.slope()))
                && isBetween(p.getX(), this.start().getX(), this.end().getX());
    }

    /**
     * This is a method that returns true if a given line is intersecting with this line segment and false if not.
     * <p>
     * The method assumes that the given line does not equal null.
     * </p>
     * @param other the given line
     * @return true if the other line is intersecting this line segment, false if not
     */
    public boolean isIntersecting(Line other) {
        double intersectionX;
        double intersectionY;
        double thisSlope;
        double otherSlope;
        //Extreme case: one of the lines is a dot contained within the other line
        if (this.start().equals(this.end()) && other.contains(this.start())) {
            return true;
        }
        if (other.start().equals(other.end()) && this.contains(other.start())) {
            return true;
        }
        /*
        Extreme case: both lines have the same slope. In this case the lines have a single intersection
        point if they share an edge and none of the other edges is on the other line.
         */
        if (doubleEquals(this.slope(), other.slope())) {
            return (this.start().equals(other.start()) && !this.contains(other.end()) && !other.contains(this.end()))
                  || (this.start().equals(other.end()) && !this.contains(other.start()) && !other.contains(this.end()))
                  || (this.end().equals(other.start()) && !this.contains(other.end()) && !other.contains(this.start()))
                  || (this.end().equals(other.end()) && !this.contains(other.start()) && !other.contains(this.start()));
        }
        /*
        Extreme case: one of the lines is parallel to the y axis. The method finds the linear equations' intersection
        point and checks whether it is on both line segments
         */
        if (this.slope() == null) {
            intersectionX = this.start().getX();
            otherSlope = other.slope();
            intersectionY = otherSlope * (intersectionX - other.start().getX()) + other.start().getY();
            Point intersection = new Point(intersectionX, intersectionY);
            return this.contains(intersection) && other.contains(intersection);
        }
        if (other.slope() == null) {
            intersectionX = other.start().getX();
            thisSlope = this.slope();
            intersectionY = thisSlope * (intersectionX - this.start().getX()) + this.start().getY();
            Point intersection = new Point(intersectionX, intersectionY);
            return this.contains(intersection) && other.contains(intersection);
        }
        //The method finds the linear equations' intersection point and checks whether it is on both line segments
        thisSlope = this.slope();
        otherSlope = other.slope();
        intersectionX = (thisSlope * this.start().getX() - otherSlope * other.start().getX() + other.start().getY()
                         - this.start().getY()) / (thisSlope - otherSlope);
        intersectionY = thisSlope * (intersectionX - this.start().getX()) + this.start().getY();
        Point intersection = new Point(intersectionX, intersectionY);
        return this.contains(intersection) && other.contains(intersection);
    }

    /**
     * This is a method that returns a given line's intersection point with this line.
     * <p>
     * The method assumes that the given line does not equal null.
     * If the lines do intersect the method returns their intersection point and if not it returns null.
     * </p>
     * @param other the given line
     * @return the lines' intersection point
     */
    public Point intersectionWith(Line other) {
        double intersectionX;
        double intersectionY;
        double thisSlope;
        double otherSlope;
        if (!this.isIntersecting(other)) {
            return null;
        }
        //Extreme case: one of the lines is a dot contained within the other line
        if (this.start().equals(this.end())) {
            return this.start();
        }
        if (other.start().equals(other.end())) {
            return other.start();
        }
        //Extreme case: the lines share an edge
        if (this.start().equals(other.start()) || this.start().equals(other.end())) {
            return this.start();
        }
        if (this.end().equals(other.start()) || this.end().equals(other.end())) {
            return this.end();
        }

        /*
        Extreme case: one of the lines is parallel to the y axis. The method finds the linear equations' intersection
        point
        */
        if (this.slope() == null) {
            intersectionX = this.start().getX();
            otherSlope = other.slope();
            intersectionY = otherSlope * (intersectionX - other.start().getX()) + other.start().getY();
            return new Point(intersectionX, intersectionY);
        }
        if (other.slope() == null) {
            intersectionX = other.start().getX();
            thisSlope = this.slope();
            intersectionY = thisSlope * (intersectionX - this.start().getX()) + this.start().getY();
            return new Point(intersectionX, intersectionY);
        }
        //The method finds the linear equations' intersection point
        thisSlope = this.slope();
        otherSlope = other.slope();
        intersectionX = (thisSlope * this.start().getX() - otherSlope * other.start().getX() + other.start().getY()
                - this.start().getY()) / (thisSlope - otherSlope);
        intersectionY = thisSlope * (intersectionX - this.start().getX()) + this.start().getY();
        return new Point(intersectionX, intersectionY);
    }

    /**
     * This is a method that returns true if a given line is equal to this line and false if not.
     * <p>
     * The method assumes that the given line does not equal null.
     * Two lines are equal if they have the same start point and the same end point, if they have the same edges
     * but they are reversed the lines are not equal.
     * </p>
     * @param other the given line
     * @return true if the lines are equal, false if not
     */
    public boolean equals(Line other) {
        return this.start().equals(other.start()) && this.end().equals(other.end());
    }

    /**
     * This is a method that returns a given rectangle's intersection with this line
     * <p>
     * The method assumes that the given rectangle does not equal null.
     * The method returns the closest intersection to the start of the line
     * </p>
     * @param rect the given rectangle
     * @return the closest intersection
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        java.util.List<Point> intersectionPoints = rect.intersectionPoints(this);
        Point intersection = null;
        for (Point point : intersectionPoints) {
            if (intersection == null || new Line(this.start(), intersection).contains(point)) {
                intersection = new Point(point.getX(), point.getY());
            }
        }
        return intersection;
    }
}
