package no.uib.inf101.tetris.view;

import javax.swing.JPanel;

import no.uib.inf101.grid.GridCell;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;



public class TetrisView extends JPanel{
    private ViewableTetrisModel tetrisModel; 
    private ColorTheme colorTheme;
    private static final int INNERMARGIN = 2;
    private static final int OUTERMARGIN = 2;


    public TetrisView(ViewableTetrisModel tetrisModel) {
        this.tetrisModel=tetrisModel;
        this.colorTheme =  new DefaultColorTheme();
        this.setBackground(colorTheme.getBackgroundColor());
        
}



@Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g;

    drawGame(g2);
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
}
