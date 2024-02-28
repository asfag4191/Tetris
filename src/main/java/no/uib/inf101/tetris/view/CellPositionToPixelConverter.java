package no.uib.inf101.tetris.view;

import java.awt.geom.Rectangle2D;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.GridDimension;

public class CellPositionToPixelConverter {
    private Rectangle2D box;
    private GridDimension gd;
    private double margin;

//
//konstruktør, instansvariabler som er private,
    public CellPositionToPixelConverter(Rectangle2D box, GridDimension gd, double margin) {
        this.box = box;
        this.gd = gd;
        this.margin = margin;
    }

  //metode, som hver klasse har, definerer klassens funksjonalitet
  //denne metoden bruker informasjon lagret i instansvariablene til å regne ut og returnere et Rectangle2D objekt
  //denne returnerer et Rectangle2D objekt. Vi oppretter dette objektet når vi regner ut de forskjellige verdiene
  //som skal til for å tegne en celle i en grid
    public Rectangle2D getBoundsForCell(CellPosition cp) {
  
    int gridRows=this.gd.rows();
    int gridCols=this.gd.cols();

    double boxX= this.box.getX();
    double boxY=this.box.getY();

    double boxWidth=this.box.getWidth();
    double boxHeight=this.box.getHeight();

    double cellWidth= (boxWidth -( gridCols+1) *this.margin)/gridCols;
    double cellHeight=(boxHeight-(gridRows+1)*this.margin)/gridRows;

    double cellX=boxX+margin+margin*cp.col() + cp.col()*cellWidth;
    double cellY=boxY+margin+margin*cp.row() + cp.row()*cellHeight;

      return new Rectangle2D.Double(cellX, cellY, cellWidth, cellHeight);
  }
}