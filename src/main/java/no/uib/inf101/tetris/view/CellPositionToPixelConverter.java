package no.uib.inf101.tetris.view;

import java.awt.geom.Rectangle2D;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.GridDimension;

/**
 * This class is responsible to convert a cell position within a grid dimension
 * to a pixel coordinates.
 */
public class CellPositionToPixelConverter {
  private Rectangle2D box;
  private GridDimension gd;
  private double margin;

  /**
   * Constructs a new CellPositionToPixelConverter.
   * Maps cell positions to pixel coordinates.
   * 
   * @param box    The rectangle that will contain the cells.
   * @param gd     The grid dimension.
   * @param margin The margin around each cells.
   */
  public CellPositionToPixelConverter(Rectangle2D box, GridDimension gd, double margin) {
    this.box = box;
    this.gd = gd;
    this.margin = margin;
  }

  /**
   * Converts the given cell position to pixel coordinates and return
   * the corresponding bounding rectangle.
   * 
   * @param cp The cell position to convert.
   * @return The rectangle representing the bounds of the cell in pixel
   *         coordinates.
   */
  public Rectangle2D getBoundsForCell(CellPosition cp) {

    int gridRows = this.gd.rows();
    int gridCols = this.gd.cols();

    double boxX = this.box.getX();
    double boxY = this.box.getY();
    double boxWidth = this.box.getWidth();
    double boxHeight = this.box.getHeight();

    double cellWidth = (boxWidth - (gridCols + 1) * this.margin) / gridCols;
    double cellHeight = (boxHeight - (gridRows + 1) * this.margin) / gridRows;

    double cellX = boxX + margin + margin * cp.col() + cp.col() * cellWidth;
    double cellY = boxY + margin + margin * cp.row() + cp.row() * cellHeight;

    return new Rectangle2D.Double(cellX, cellY, cellWidth, cellHeight);
  }
}
