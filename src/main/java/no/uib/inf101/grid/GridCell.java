package no.uib.inf101.grid;

/**
 * A cell in a grid.
 *
 * @param <E> The type of the value in the cell
 * @param pos The position of the cell
 */


public record GridCell<E>(CellPosition pos, E value) {

    
}
