package game;

import java.util.Comparator;
import java.util.List;

public abstract class Shape {
  public abstract boolean containsPoint(Point p);

  public abstract List<Edge> getEdges();

  public Edge closestWall(Point position) {
    return getEdges().stream().min(
        Comparator.comparingDouble(edge -> GeomUtil.minimumDistanceSq(position, edge)))
        .orElseThrow(() -> new IllegalStateException("Shape doesn't have any edges"));
  }
}
