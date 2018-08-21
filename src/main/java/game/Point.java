package game;

public class Point {
  public final double x;
  public final double y;

  private Point(final double x, final double y) {
    this.x = x;
    this.y = y;
  }

  public static Point of(final double x, final double y) {
    return new Point(x, y);
  }

  public Point followHeading(Heading heading, double moveAmount) {
    return offset(Math.cos(heading.rad) * moveAmount, Math.sin(heading.rad) * moveAmount);
  }

  public Point offset(double deltaX, double deltaY) {
    return new Point(x + deltaX, y + deltaY);
  }

  public Heading headingTo(Point other) {
    return Heading.rad(Math.atan2(other.y - y, other.x - x));
  }

  public double distanceSq(Point other) {
    return (other.y - y) * (other.y - y) + (other.x - x) * (other.x - x);
  }

  public double distance(Point other) {
    return Math.sqrt(distanceSq(other));
  }

  /**
   * Check if the point is valid or not. Right now just means inside the board bounds. But could
   * involve black holes or something.
   */
  public boolean isValid() {
    return 0 <= x && x <= 1 &&
        0 <= y && y <= 1;
  }

  @Override
  public String toString() {
    return String.format("[%.2f, %.2f]", x, y);
  }
}
