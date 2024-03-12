package no.uib.inf101.grid;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * A grid of cells.
 *
 * @param <E> The type of the value in the cells
 */
public class Grid<E> implements IGrid<E> {
    private int rows;
    private int cols;
    private ArrayList<ArrayList<E>> grid;
    private E value;

    /**
     * Specified number of rows and columns
     * All grid elements are set to the provided value
     * 
     * @param rows  The number of rows in the grid
     * @param cols  The number of cols in the grid
     * @param value The initial value for all cells in the grid
     */
    public Grid(int rows, int cols, E value) {
        this.rows = rows;
        this.cols = cols;
        this.value = value;
        this.grid = new ArrayList<>();

        for (int i = 0; i < rows; i++) {
            ArrayList<E> rowList = new ArrayList<>();
            for (int j = 0; j < cols; j++) {
                rowList.add(value);
            }
            grid.add(rowList);
        }
    }

    /**
     * Specified number of rows and columns
     * All grid elements are set to null
     * 
     * @param rows The number of rows in the grid
     * @param cols The number of cols in the grid
     */
    public Grid(int rows, int cols) {
        this(rows, cols, null);
    }

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
        for (int rows = 0; rows < grid.size(); rows++) {
            for (int cols = 0; cols < grid.get(0).size(); cols++) {
                CellPosition pos = new CellPosition(rows, cols);
                E value = grid.get(rows).get(cols);
                GridCell<E> gridCell = new GridCell<>(pos, value);
                cellList.add(gridCell);
            }
        }
        return cellList.iterator();

    }
}
