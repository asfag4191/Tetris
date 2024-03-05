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



public class TetrisView extends JPanel{
    private ViewableTetrisModel tetrisModel; 
    private ColorTheme colorTheme;
    private static final int INNERMARGIN = 2;
    private static final int OUTERMARGIN = 2;


    public TetrisView(ViewableTetrisModel tetrisModel) {
        this.setPreferredSize(new Dimension(300,400));
        this.tetrisModel=tetrisModel;
        this.colorTheme =  new DefaultColorTheme();
        this.setBackground(colorTheme.getBackgroundColor());

}


@Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g;

    drawGame(g2);

    if (tetrisModel.getGameState() == GameState.GAME_OVER) {
      drawGameOverOverlay(g2);
      drawScore(g2);
  }
}


private void drawGame(Graphics2D g2) {
    double margin = 2.0;  // rammetykkelse
    double x= INNERMARGIN;
    double y= OUTERMARGIN;
    double width = getWidth() - 2*OUTERMARGIN;
    double height = getHeight() - 2*INNERMARGIN;
    Rectangle2D frameRectangle = new Rectangle2D.Double(x, y, width, height);



    


    g2.setColor(colorTheme.getFrameColor());
    g2.fill(frameRectangle);
    
    
    CellPositionToPixelConverter converter = new CellPositionToPixelConverter(frameRectangle, tetrisModel.getDimension(), margin);

    drawCells(g2, tetrisModel.getTilesOnBoard(), converter, colorTheme);
    drawCells(g2, tetrisModel.getFallingTiles(), converter, colorTheme);

}

private static void drawCells(Graphics2D g2, Iterable<GridCell<Character>> cells,CellPositionToPixelConverter converter, ColorTheme colorTheme) {
        for (GridCell<Character> cell : cells) {

            Rectangle2D cellBounds = converter.getBoundsForCell(cell.pos());

            g2.setColor(colorTheme.getCellColor(cell.value()));
            //g2.setColor(colorTheme.getCellColor('r'));

            g2.fill(cellBounds);
        }
    }
    private void drawGameOverOverlay(Graphics2D g2) {
      // Tegner en gjennomsiktig svart bakgrunn
      g2.setColor(new Color(0, 0, 0, 128));
      g2.fillRect(0, 0, getWidth(), getHeight());

      // Setter farge og font for "Game Over" teksten
      g2.setColor(Color.WHITE);
      g2.setFont(new Font("Arial", Font.BOLD, 30));

      // Sentrerer teksten i vinduet
      String text = "Game Over";
      FontMetrics fm = g2.getFontMetrics();
      int x = (getWidth() - fm.stringWidth(text)) / 2;
      int y = (getHeight() - fm.getHeight()) / 2 + fm.getAscent();

      // Tegner teksten
      g2.drawString(text, x, y);
  }

  private void drawScore(Graphics2D g2) {
      g2.setColor(Color.RED);
      g2.setFont(new Font("Arial", Font.BOLD, 40));

      FontMetrics fm = g2.getFontMetrics();
      String text="Score: " + tetrisModel.getscore();
     int x = (getWidth() - fm.stringWidth(text)) / 2;
     int y=fm.getHeight();
      g2.drawString(text,x,y);
  }
}

