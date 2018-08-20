package game;

import com.google.common.collect.ImmutableList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.WindowConstants;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.List;

public class OkayGameAnalyzer {
  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
      JFrame f = new JFrame("My Game");
      f.setSize(1080, 1080);
      f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
      Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
      f.setLocation((screenSize.width - f.getWidth()) / 2, (screenSize.height - f.getHeight()) / 2);
      OkayGameAnalyzer okg = new OkayGameAnalyzer(f);
      f.setVisible(true);

      okg.runGame();
    });
  }

  private JFrame frame;
  private JPanel gamePanel;

  private OkayGameAnalyzer(JFrame frame) {
    this.frame = frame;
    this.gamePanel = new JPanel();
    frame.add(gamePanel);
  }

  private void runGame() {
    GameBoard gb = new GameBoard(makeBoard());
    gamePanel.removeAll();
    gamePanel.setLayout(new GridLayout(0, 1));
    gamePanel.add(gb);

    Point startingPoint = Point.of(0.2, 0.1);
    Heading startingHeading = Heading.deg(45);
    gb.beginRun(new BallState(startingPoint, startingHeading));

    Timer timer = new Timer(15, e -> {
      gb.update();
      gamePanel.paintImmediately(0, 0, gamePanel.getWidth(), gamePanel.getHeight());
    });
    timer.start();
  }

  private static List<Block> makeBoard() {
    return ImmutableList.of(
        Block.of(0.5, 0.3, 0.15, 0.45),
        Block.of(0.4, 0.4, 0.03, 0.40)
    );
  }
}