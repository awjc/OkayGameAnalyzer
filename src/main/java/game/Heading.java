package game;

import static game.Constants.EAST_RAD;
import static game.Constants.NORTH_RAD;
import static game.Constants.SOUTH_RAD;
import static game.Constants.WEST_RAD;

public class Heading {
  public static final Heading NORTH = Heading.rad(NORTH_RAD);
  public static final Heading EAST = Heading.rad(EAST_RAD);
  public static final Heading SOUTH = Heading.rad(SOUTH_RAD);
  public static final Heading WEST = Heading.rad(WEST_RAD);

  public final double rad;
  public final double deg;

  private Heading(double headingDeg) {
    this.rad = Math.toRadians(headingDeg);
    this.deg = headingDeg;
  }

  public static Heading rad(double headingRad) {
    return new Heading(normalizeDeg(Math.toDegrees(headingRad)));
  }

  public static Heading deg(double headingDeg) {
    return new Heading(normalizeDeg(headingDeg));
  }

  public Heading reflect(Edge surface) {
    double surfaceThetaRad = Math.toDegrees(Math.atan2(surface.end.y - surface.start.y, surface.end.x - surface.start.x));
    double reflectingAngleRad = surfaceThetaRad + 90;
    double newHeadingRad = (reflectingAngleRad - deg) + reflectingAngleRad + 180;
    return Heading.deg(newHeadingRad);
  }

  public Heading addDeg(double degToAdd) {
    return deg(deg + degToAdd);
  }

  public static double normalizeDeg(double deg) {
    // reduce the angle
    deg =  deg % 360;
    // force it to be the positive remainder, so that 0 <= angle < 360
    deg = (deg + 360) % 360;
    // force into the minimum absolute value residue class, so that -180 < angle <= 180
    if (deg > 180) {
      deg -= 360;
    }
    return deg;
  }
}
