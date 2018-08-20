package game;

import javax.swing.JPanel;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class GameBoard extends JPanel {
  private static final int BALL_DIAMETER_PIXELS = 20;
  private static final Color BG_COLOR = Color.decode("#e6f2ff");
  private static final Color BLOCK_COLOR = Color.decode("#595959");
  private static final Color BALL_COLOR = Color.decode("#004d99");

  private List<Block> blocks;
  private BallState ballState;

  public GameBoard(List<Block> blocks) {
    this.blocks = blocks;

    setBackground(BG_COLOR);
    setLayout(new BorderLayout());

    addMouseListener(new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent e) {
        System.out.println(e.getX() + " : " + e.getY());
        Point newPos = Point.of(e.getX() * 1.0 / getSize().width, e.getY() * 1.0 / getSize().height);
        ballState = new BallState(newPos, ballState.heading);
      }
    });
  }

  public void beginRun(BallState originState) {
    this.ballState = originState;
  }

  public void update() {
    Heading curHeading = ballState.heading;
    Heading newHeading = ballState.heading;
    Point curPosition = ballState.position;
    Point newPosition = ballState.nextPosition();

    for (Block block : blocks) {
      if (block.containsPoint(newPosition)) {
        Edge reflector = block.closestWall(newPosition);
        double distToWall = Math.sqrt(GeomUtil.minimumDistanceSq(curPosition, reflector));
        double overrunDist = Math.max(0, BallState.SPEED - distToWall);

        Point intersectPoint = curPosition.followHeading(curHeading, distToWall);
        newHeading = curHeading.reflect(reflector);
        newPosition = intersectPoint.followHeading(newHeading, overrunDist);
        break;
      }
    }
    ballState = new BallState(newPosition, newHeading);
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    ((Graphics2D) g).setStroke(new BasicStroke(3));

    for (Block b : blocks) {
      drawBlock(g, getSize(), b);
    }
    drawBall(g, getSize(), ballState);
  }

  private static void drawBlock(Graphics g, Dimension size, Block b) {
    g.setColor(BLOCK_COLOR);
    g.fillRect(w(b.x, size), h(b.y, size), w(b.w, size), h(b.h, size));
  }

  private static void drawBall(Graphics g, Dimension size, BallState b) {
    g.setColor(BALL_COLOR);
    g.fillOval(w(b.position.x, size) - BALL_DIAMETER_PIXELS / 2, h(b.position.y, size) - BALL_DIAMETER_PIXELS / 2,
        BALL_DIAMETER_PIXELS, BALL_DIAMETER_PIXELS);

    // Make an arrow from where we are to where we will be in the near future
    Point endOfArrow = b.position.followHeading(b.heading, BallState.SPEED * 10);
    drawLine(g, size, b.position, endOfArrow);
    double arrowTipLength = 0.005;
    Point arrowTip1 = endOfArrow.followHeading(b.heading.addDeg(135), arrowTipLength);
    drawLine(g, size, endOfArrow, arrowTip1);
    Point arrowTip2 = endOfArrow.followHeading(b.heading.addDeg(-135), arrowTipLength);
    drawLine(g, size, endOfArrow, arrowTip2);
  }

  private static void drawLine(Graphics g, Dimension size, Point p1, Point p2) {
    g.drawLine(w(p1.x, size), h(p1.y, size), w(p2.x, size), h(p2.y, size));
  }

  private static int w(double x, Dimension screenSize) {
    return (int) (x * screenSize.width);
  }

  private static int h(double y, Dimension screenSize) {
    return (int) (y * screenSize.height);
  }
}
