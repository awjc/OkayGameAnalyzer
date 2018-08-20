package game;

public class Edge {
  public final Point start;
  public final Point end;
  public final double lengthSq;

  private Edge(Point start, Point end) {
    this.start = start;
    this.end = end;
    lengthSq = (end.y - start.y) * (end.y - start.y) + (end.x - start.x) * (end.x - start.x);
  }

  public static Edge of(Point start, Point end) {
    return new Edge(start, end);
  }

  public double length() {
    return Math.sqrt(lengthSq);
  }
}
