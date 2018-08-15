package game;

import javax.swing.*;
import java.awt.*;

public class OkayGameAnalyzer {
  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
      JFrame f = new JFrame("My Game");
      f.setSize(1080, 800);
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

    JTextArea jta = new JTextArea("Heyyy");
    jta.setEditable(false);
    jta.setBackground(null);
    gamePanel.add(jta);
  }

  private void runGame() {

  }
}