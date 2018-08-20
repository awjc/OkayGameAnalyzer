package game;

public class GeomUtil {
  // Mostly copied from StackOverflow [https://stackoverflow.com/questions/849211/shortest-distance-between-a-point-and-a-line-segment]
  public static double minimumDistanceSq(Point p, Edge e) {
    if (e.lengthSq == 0.0) {
      // The case when the edge is degenerate
      return p.distance(e.start);
    }
    // Consider the line extending the segment, parameterized as v + t (w - v).
    // We find projection of point p onto the line.
    // It falls where t = [(p-v) . (w-v)] / |w-v|^2
    // We clamp t from [0,1] to handle points outside the segment vw.

    double t = Math.max(0, Math.min(1,
        ((p.x - e.start.x) * (e.end.x - e.start.x) + (p.y - e.start.y) * (e.end.y - e.start.y)) / e.lengthSq));
    Point projection = e.start.offset(t * (e.end.x - e.start.x), t * (e.end.y - e.start.y));
    return p.distanceSq(projection);
  }
}
