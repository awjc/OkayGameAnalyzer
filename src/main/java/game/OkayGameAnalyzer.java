package game;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.WindowConstants;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.Arrays;
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

    Timer timer = new Timer(15, e -> {
      gb.update();
      gamePanel.paintImmediately(0, 0, gamePanel.getWidth(), gamePanel.getHeight());
    });
    timer.start();
  }

  private static List<Block> makeBoard() {
    return Lists.newArrayList(
        Block.of(0.5, 0.3, 0.15, 0.85),
        Block.of(0.4, 0.4, 0.03, 0.80)
    );
  }
}