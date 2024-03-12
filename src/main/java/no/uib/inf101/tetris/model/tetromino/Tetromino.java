package no.uib.inf101.tetris.model.tetromino;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.GridCell;
import no.uib.inf101.grid.GridDimension;

/**
 * Represents a single Tetromino piece in the game.
 * A Tetromino is defined by its shape, poisition and symbol.
 * This class provides methods to manipulate the Tetromino's position and
 * orientation within the game grid.
 */
public final class Tetromino implements Iterable<GridCell<Character>> {

    private final char symbol;
    private final boolean[][] shape;
    private final CellPosition position;

    /**
     * Constructs a new Tetromino with the given symbol, shape and position.
     * 
     * @param symbol   The character symbol representing the Tetromino type.
     * @param shape    A 2D boolean array representing the shape of the Tetromino,
     *                 true indicates a block.
     * @param position The starting position of the tetromino on the grid.
     */
    public Tetromino(char symbol, boolean[][] shape, CellPosition position) {
        this.symbol = symbol;
        this.shape = shape;
        this.position = position;
    }

    /**
     * Method to create a new Tetromino based on the given symbol.
     * The shape of the Tetromino is determined by the symbol.
     * 
     * @param symbol Representing the Tetromino type.
     * @return A new Tetromino object with the given symbol.
     */
    public static Tetromino newTetromino(char symbol) {
        boolean[][] shape = switch (symbol) {
            case 'I' -> new boolean[][] {
                    { false, false, false, false },
                    { true, true, true, true },
                    { false, false, false, false },
                    { false, false, false, false }
            };
            case 'O' -> new boolean[][] {

                    { false, false, false, false },
                    { false, true, true, false },
                    { false, true, true, false },
                    { false, false, false, false }
            };
            case 'T' -> new boolean[][] {

                    { false, false, false },
                    { true, true, true },
                    { false, true, false }
            };
            case 'S' -> new boolean[][] {

                    { false, false, false },
                    { false, true, true },
                    { true, true, false },
            };
            case 'Z' -> new boolean[][] {

                    { false, false, false },
                    { true, true, false },
                    { false, true, true }
            };
            case 'J' -> new boolean[][] {

                    { false, false, false },
                    { true, true, true },
                    { false, false, true }
            };
            case 'L' -> new boolean[][] {

                    { false, false, false },
                    { true, true, true },
                    { true, false, false }
            };
            default -> throw new IllegalArgumentException("Invalid symbol");
        };
        CellPosition defaultPosition = new CellPosition(0, 0);
        Tetromino tetromino = switch (symbol) {
            case 'I' -> new Tetromino(symbol, shape, defaultPosition);
            case 'O' -> new Tetromino(symbol, shape, defaultPosition);
            case 'T' -> new Tetromino(symbol, shape, defaultPosition);
            case 'S' -> new Tetromino(symbol, shape, defaultPosition);
            case 'Z' -> new Tetromino(symbol, shape, defaultPosition);
            case 'J' -> new Tetromino(symbol, shape, defaultPosition);
            case 'L' -> new Tetromino(symbol, shape, defaultPosition);
            default -> throw new IllegalArgumentException("Invalid symbol");
        };
        return tetromino;
    }

    /**
     * Shifts the Tetromino down by specified number of rows and columns.
     * 
     * @param deltaRow The number of rows to shift the Tetromino down by.
     * @param deltaCol The number of cols to shift the Tetromino right by.
     * @return A new Tetromino object with the new position.
     */
    public Tetromino shiftedBy(int deltaRow, int deltaCol) {
        CellPosition newPosition = new CellPosition(position.row() + deltaRow, position.col() + deltaCol);

        return new Tetromino(this.symbol, this.shape, newPosition);
    }

    /**
     * Shifts the Tetromino to the top center of the grid.
     * 
     * @param gridDimension The dimensions of the grid.
     * @return A new Tetromino object with the new position.
     */
    public Tetromino shiftedToTopCenterOf(GridDimension gridDimension) {
        int deltaRow = -1;
        int adjustedCol = (gridDimension.cols() - shape[0].length) / 2;

        CellPosition newPosition = new CellPosition(deltaRow, adjustedCol);
        return new Tetromino(symbol, shape, newPosition);
    }

    /**
     * Rotates the Tetromino 90 degrees clockwise.
     * 
     * @return A new Tetromino object with the new shape.
     */
    public Tetromino rotatedTetromino() {
        boolean[][] originalShape = shape;
        int rows = originalShape.length;
        int cols = originalShape[0].length;

        boolean[][] newShape = new boolean[cols][rows];

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                int rotatedRow = col;
                int rotatedCol = rows - 1 - row;
                newShape[rotatedRow][rotatedCol] = originalShape[row][col];
            }
        }
        return new Tetromino(symbol, newShape, position);
    }

    @Override
    public Iterator<GridCell<Character>> iterator() {
        ArrayList<GridCell<Character>> listCell = new ArrayList<>();
        for (int row = 0; row < shape.length; row++) {
            for (int col = 0; col < shape[row].length; col++) {
                // utført handlinger hvis True
                if (shape[row][col]) {
                    int realRow = position.row() + row;
                    int realCol = position.col() + col;
                    CellPosition realPosition = new CellPosition(realRow, realCol);
                    GridCell<Character> gridCell = new GridCell<>(realPosition, symbol);
                    listCell.add(gridCell);
                }
            }
        }
        return listCell.iterator();
    }

    @Override
    public int hashCode() {
        return Objects.hash(symbol, Arrays.deepHashCode(shape), position);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object)
            return true;
        if (object == null || getClass() != object.getClass())
            return false;
        Tetromino tetromino = (Tetromino) object;
        return symbol == tetromino.symbol &&
                Arrays.deepEquals(shape, tetromino.shape) &&
                Objects.equals(position, tetromino.position);
    }

}
