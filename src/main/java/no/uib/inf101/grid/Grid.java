package no.uib.inf101.grid;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//Grid har en genererisk typeparameter E, og implementerer IGrid
//Grid har to feltvariabler, rows og cols, og en ArrayList av ArrayList av E
public class Grid<E> implements IGrid<E> {

    //felt variabler
    private int rows;
    private int cols;
    private ArrayList<ArrayList<E>> grid;
    private E value;


    /**
     * @param rows
     * @param cols
     * @param value
     */

    public Grid(int rows, int cols, E value){
        this.rows=rows;
        this.cols=cols;
        this.value=value;
        this.grid=new ArrayList<>();
        
        for (int i = 0; i < rows; i++) {
            ArrayList<E> rowList = new ArrayList<>();
            for (int j = 0; j < cols; j++) {
                rowList.add(value);
            }
            grid.add(rowList);
    }
}


    public Grid(int rows, int cols) { 
        this(rows, cols, null);
    }

    //Metoder som implementerer IGrid interfacet
    @Override
    public void set(CellPosition pos, E value) {
        int row = pos.row();
        int col = pos.col();
        grid.get(row).set(col, value);
    }

    @Override
    public E get(CellPosition pos) {
        int row = pos.row();
        int col = pos.col();
        return grid.get(row).get(col);
    }

    @Override
    public boolean positionIsOnGrid(CellPosition pos) {
        return pos.row() >= 0 && pos.row() < rows && pos.col() >= 0 && pos.col() < cols;
    }
    
    @Override
    public int rows() {
        return this.rows;
    }

    @Override
    public int cols() {
        return this.cols;
    }

    @Override
    public Iterator<GridCell<E>> iterator() {
        List<GridCell<E>> cellList = new ArrayList<>();
        for (int i = 0; i < grid.size(); i++) {
            for (int j = 0; j < grid.get(0).size(); j++) {
                CellPosition pos = new CellPosition(i, j);
                E value = grid.get(i).get(j);
                GridCell<E> gridCell = new GridCell<>(pos, value);
                cellList.add(gridCell);
  }
}
return cellList.iterator();

}
}


    


