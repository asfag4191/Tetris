package no.uib.inf101.grid;

/**
 * A cell in a grid.
 *
 * @param <E> The type of the value in the cell
 */

//genererisk typeparameter E, og to feltvariabler

public record GridCell<E>(CellPosition pos, E value) {

    
}
