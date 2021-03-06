package game;

import com.google.common.collect.ImmutableList;

import java.util.List;

public class Block extends Shape {
  public final double x;
  public final double y;
  public final double w;
  public final double h;
  public final ImmutableList<Edge> edges;

  private Block(double x, double y, double w, double h) {
    this.x = x;
    this.y = y;
    this.w = w;
    this.h = h;
    edges = ImmutableList.of(
        Edge.of(Point.of(x, y), Point.of(x, y + h)),
        Edge.of(Point.of(x, y + h), Point.of(x + w, y + h)),
        Edge.of(Point.of(x + w, y + h), Point.of(x + w, y)),
        Edge.of(Point.of(x + w, h), Point.of(x + w, y)));
  }

  public static Block of(double x, double y, double w, double h) {
    return new Block(x, y, w, h);
  }

  @Override
  public List<Edge> getEdges() {
    return edges;
  }

  @Override
  public boolean containsPoint(Point p) {
    return p.x >= x && p.x <= x + w &&
        p.y >= y && p.y <= y + h;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o == null) {
      return false;
    }
    if (!(o.getClass() == this.getClass())) {
      return false;
    }
    Block ob = (Block) o;
    double tolerance = 1e-9;
    return Math.abs(ob.x - x) < tolerance &&
        Math.abs(ob.y - y) < tolerance &&
        Math.abs(ob.w - w) < tolerance &&
        Math.abs(ob.h - h) < tolerance;
  }
}
