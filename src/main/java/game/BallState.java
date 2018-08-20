package game;

public class BallState {
  public static final double SPEED = 0.01;

  public final Point position;
  public final Heading heading;

  public BallState(Point position, Heading heading) {
    this.position = position;
    this.heading = heading;
  }

  public Point nextPosition() {
    return position.followHeading(heading, SPEED);
  }
}
