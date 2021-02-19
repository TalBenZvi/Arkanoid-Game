import biuoop.DrawSurface;

/**
 * @author Tal Ben-zvi 213420003 talbz03@gmail.com
 */
public class Rectangle {
    private Point upperLeft;
    private double width;
    private double height;

    /**
     * This is a constructor method that initiates a Rectangle object using an upper left point, a width and a height.
     *
     * @param upperLeft the Rectangle's upper left point
     * @param width the Rectangle's width
     * @param height the Rectangle's height
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = new Point(upperLeft.getX(), upperLeft.getY());
        this.width = width;
        this.height = height;
    }

    /**
     * This is a method that returns this rectangle's width.
     *
     * @return this rectangle's width
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * This is a method that returns this rectangle's height.
     *
     * @return this rectangle's height
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * This is a method that returns this rectangle's upper left point.
     *
     * @return this rectangle's upper left point
     */
    public Point getUpperLeft() {
        return new Point(this.upperLeft.getX(), this.upperLeft.getY());
    }

    /**
     * This is a method that returns this rectangle's upper right point.
     *
     * @return this rectangle's upper right point
     */
    public Point getUpperRight() {
        return new Point(this.upperLeft.getX() + this.getWidth(), this.upperLeft.getY());
    }

    /**
     * This is a method that returns this rectangle's lower left point.
     *
     * @return this rectangle's lower left point
     */
    public Point getLowerLeft() {
        return new Point(this.upperLeft.getX(), this.upperLeft.getY() + this.getHeight());
    }

    /**
     * This is a method that returns this rectangle's lower right point.
     *
     * @return this rectangle's lower right point
     */
    public Point getLowerRight() {
        return new Point(this.upperLeft.getX() + this.getWidth(), this.upperLeft.getY() + this.getHeight());
    }

    /**
     * This is a method that returns this rectangle's top edge.
     *
     * @return this rectangle's top edge
     */
    public Line getTopEdge() {
        return new Line(this.getUpperLeft(), this.getUpperRight());
    }

    /**
     * This is a method that returns this rectangle's bottom edge.
     *
     * @return this rectangle's bottom edge
     */
    public Line getBottomEdge() {
        return new Line(this.getLowerLeft(), this.getLowerRight());
    }

    /**
     * This is a method that returns this rectangle's left edge.
     *
     * @return this rectangle's left edge
     */
    public Line getLeftEdge() {
        return new Line(this.getUpperLeft(), this.getLowerLeft());
    }

    /**
     * This is a method that returns this rectangle's right edge.
     *
     * @return this rectangle's right edge
     */
    public Line getRightEdge() {
        return new Line(this.getUpperRight(), this.getLowerRight());
    }

    /**
     * This is a method that returns this rectangle's intersection points with a given line.
     * <p>
     * The method assumes that the given line does not equal null
     * </p>
     * @param line the given line
     * @return this rectangle's intersection points with the given line
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        Line[] edges = new Line[4];
        edges[0] = this.getTopEdge();
        edges[1] = this.getBottomEdge();
        edges[2] = this.getLeftEdge();
        edges[3] = this.getRightEdge();
        java.util.List<Point> intersectionPoints = new java.util.ArrayList<Point>();
        boolean isInTheList = false;
        for (Line edge : edges) {
            if (line.isIntersecting(edge)) {
                for (Point point : intersectionPoints) {
                    if (line.intersectionWith(edge).equals(point)) {
                        isInTheList = true;
                    }
                }
                if (!isInTheList) {
                    intersectionPoints.add(line.intersectionWith(edge));
                }
            }
            isInTheList = false;
        }
        return intersectionPoints;
    }

    /**
     * This is a method that returns true if a given point is in this rectangle and false if not.
     * <p>
     * The method assumes that the given point does not equal null.
     * </p>
     * @param point the given point
     * @return true if the given point is in this rectangle, false if not
     */
    public boolean contains(Point point) {
        return this.getUpperLeft().getX() <= point.getX() && point.getX() <= this.getUpperRight().getX()
                && this.getUpperLeft().getY() <= point.getY() && point.getY() <= this.getLowerLeft().getY();
    }

    /**
     * This is a method that returns true if a given point is on one of this rectangle's edges and false if not.
     * <p>
     * The method assumes that the given point does not equal null.
     * </p>
     * @param point the given point
     * @return true if the given point is on one of this rectangle's edges, false if not
     */
    public boolean frameContains(Point point) {
        return this.getTopEdge().contains(point) || this.getBottomEdge().contains(point)
                || this.getLeftEdge().contains(point) || this.getRightEdge().contains(point);
    }

    /**
     * This is a method that draws this rectangle's frame on a given surface with a given color.
     * <p>
     * The method assumes that the given draw surface and the given color do not equal null
     * </p>
     * @param d the given surface
     * @param color the given color
     */
    public void drawFrameOn(DrawSurface d, java.awt.Color color) {
        d.setColor(color);
        Line[] edges = new Line[4];
        edges[0] = this.getTopEdge();
        edges[1] = this.getBottomEdge();
        edges[2] = this.getLeftEdge();
        edges[3] = this.getRightEdge();
        for (Line edge : edges) {
            d.drawLine((int) edge.start().getX(), (int) edge.start().getY(), (int) edge.end().getX(),
                    (int) edge.end().getY());
        }
    }

    /**
     * This is a method that draws this rectangle on a given surface with a given color.
     * <p>
     * The method assumes that the given draw surface and the given color do not equal null
     * </p>
     * @param d the given surface
     * @param color the given color
     */
    public void drawOn(DrawSurface d, java.awt.Color color) {
        d.setColor(color);
        d.fillRectangle((int) this.getUpperLeft().getX(), (int) this.getUpperLeft().getY(),
                (int) this.getWidth(), (int) this.getHeight());
    }
}
