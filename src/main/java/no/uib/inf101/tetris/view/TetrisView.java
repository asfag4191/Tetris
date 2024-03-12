package no.uib.inf101.tetris.view;

import javax.swing.JPanel;

import no.uib.inf101.grid.GridCell;
import no.uib.inf101.tetris.model.GameState;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

/**
 * The class extends JPanel and is responsible for drawing the game graphics to
 * the board.
 * 
 * Uses ViewableTetrisModel to get information about the game state and the
 * current game board.
 * 
 * It draws the game over overlay if the game is over.
 */
public class TetrisView extends JPanel {
  private ViewableTetrisModel tetrisModel;
  private ColorTheme colorTheme;
  private static final int INNERMARGIN = 2;
  private static final int OUTERMARGIN = 2;

  /**
   * Constructs a new TetrisView with the given model.
   * 
   * @param tetrisModel The model of the game that provdides information about
   *                    game state
   */
  public TetrisView(ViewableTetrisModel tetrisModel) {
    this.setPreferredSize(new Dimension(500, 600));
    this.tetrisModel = tetrisModel;
    this.colorTheme = new DefaultColorTheme();
    this.setBackground(colorTheme.getBackgroundColor());

  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g;

    //drawGame(g2);
    if (tetrisModel.getGameState()==GameState.WELCOME_SCREEN){
      drawWelcomeScreen(g2);
    } else{
      drawGame(g2);
    }

    if (tetrisModel.getGameState() == GameState.GAME_OVER) {
      drawGameOverOverlay(g2);
      drawScore(g2);
      drawReplayText(g2);
    }
  }

  /**
   * Draws the current game state, the game board and the falling tetromino.
   * 
   * @param g2 Graphics 2D context used for drawing the game components
   */
  private void drawGame(Graphics2D g2) {
    double margin = 2.0; // rammetykkelse
    double x = INNERMARGIN;
    double y = OUTERMARGIN;
    double width = getWidth() - 2 * OUTERMARGIN;
    double height = getHeight() - 2 * INNERMARGIN;
    Rectangle2D frameRectangle = new Rectangle2D.Double(x, y, width, height);

    g2.setColor(colorTheme.getFrameColor());
    g2.fill(frameRectangle);

    CellPositionToPixelConverter converter = new CellPositionToPixelConverter(frameRectangle,
        tetrisModel.getDimension(), margin);

    drawCells(g2, tetrisModel.getTilesOnBoard(), converter, colorTheme);
    drawCells(g2, tetrisModel.getFallingTiles(), converter, colorTheme);

  }

  /**
   * Draws the invidual cells on the board.
   *
   * @param g2         Graphics 2D context used for drawing the cells.
   * @param cells      Iterable collection of Gridcell elements to be drawn.
   * @param converter  Converts cell positions to pixel positions.
   * @param colorTheme The color theme used for drawing the cells.
   */
  private static void drawCells(Graphics2D g2, Iterable<GridCell<Character>> cells,
      CellPositionToPixelConverter converter, ColorTheme colorTheme) {
    for (GridCell<Character> cell : cells) {

      Rectangle2D cellBounds = converter.getBoundsForCell(cell.pos());

      g2.setColor(colorTheme.getCellColor(cell.value()));
      g2.fill(cellBounds);
    }
  }

  /**
   * Draws the game over overlay, when the Game is over.
   * 
   * @param g2 Graphics 2D context used for drawing the game over overlay.
   */
  private void drawGameOverOverlay(Graphics2D g2) {
    g2.setColor(new Color(0, 0, 0, 128));
    g2.fillRect(0, 0, getWidth(), getHeight());

    g2.setColor(Color.WHITE);
    g2.setFont(new Font("Arial", Font.BOLD, 30));

    String text = "Game Over";
    FontMetrics fm = g2.getFontMetrics();
    int x = (getWidth() - fm.stringWidth(text)) / 2;
    int y = (getHeight() - fm.getHeight()) / 2 + fm.getAscent();

    g2.drawString(text, x, y);
  }

  private void drawReplayText(Graphics2D g2){
    // Gjenta noen av de samme oppsettene som i drawGameOverOverlay
    g2.setFont(new Font("Arial", Font.BOLD, 24));
    g2.setColor(Color.PINK);
    
    // Definer teksten for replay-instruks
    String text = "PRESS ENTER TO PLAY AGAIN";
    FontMetrics fm = g2.getFontMetrics();
    
    // Beregn x-posisjonen for å sentrere teksten horisontalt
    int x = (getWidth() - fm.stringWidth(text)) / 2;
    
    // Beregn y-posisjon for game over tekst for å bruke det som basis
    String gameOverText = "Game Over";
    FontMetrics gameOverFm = g2.getFontMetrics(new Font("Arial", Font.BOLD, 30));
    int gameOverY = (getHeight() - gameOverFm.getHeight()) / 2 + gameOverFm.getAscent();
    
    // Sette y-posisjonen for replay-teksten til et bestemt stykke under "Game Over"-teksten
    int spacing = 50; // Avstanden du vil ha mellom "Game Over" og replay-teksten
    int y = gameOverY + gameOverFm.getHeight() + spacing;
    
    // Tegn strengen
    g2.drawString(text, x, y);
  }

  /**
   * Draws the score when the game is over.
   * 
   * @param g2 Graphics2D context for drawing the score, when game over.
   */
  private void drawScore(Graphics2D g2) {
    g2.setColor(Color.RED);
    g2.setFont(new Font("Arial", Font.BOLD, 40));

    String text = "Score: " + tetrisModel.getscore();
    FontMetrics fm = g2.getFontMetrics();
    int x = (getWidth() - fm.stringWidth(text)) / 2;
    int y = fm.getHeight();

    g2.drawString(text, x, y);
  }

  private void drawWelcomeScreen(Graphics2D g2) {
    g2.setColor(new Color(128,128,128,128));
    g2.fillRect(0, 0, getWidth(), getHeight());

    g2.setColor(Color.ORANGE);
    g2.setFont(new Font("Arial", Font.BOLD, 24));

    String text = "PRESS SPACE TO PLAY";
    FontMetrics fm = g2.getFontMetrics();
    int x = (getWidth() - fm.stringWidth(text)) / 2;
    int y = (getHeight() - fm.getHeight()) / 2 + fm.getAscent();

    g2.drawString(text, x, y);
}
}
