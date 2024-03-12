package no.uib.inf101.tetris.model;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.Grid;

/**
 * Representation of the tetris game board. It support standard operations for
 * manupulating
 * the board state, including setting rows, copying rows and removing full rows.
 */
public class TetrisBoard extends Grid<Character> {

    /**
     * Constructs a new TetrisBoard with the given number of rows and columns.
     * The board is filled with the character '-', representing an empty
     * cell.
     * 
     * @param rows The number of rows in the board
     * @param cols The number of columns in the board
     */

    public TetrisBoard(int rows, int cols) {
        super(rows, cols, '-');
    }

    /**
     * Generates a string representation of the board.
     * Each row is separated by its character, each line is on a new row.
     * 
     * @return a string representation of the board
     */

    public String prettyString() {
        StringBuilder result = new StringBuilder();

        for (int row = 0; row < rows(); row++) {
            for (int col = 0; col < cols(); col++) {
                result.append(get(new CellPosition(row, col)));
            }
            result.append("\n");
        }
        return result.toString().trim();
    }

    /**
     * Chech if a specific element exist in a row.
     *
     * @param row     The row to check.
     * @param element The element to check for.
     * @return True if the elements exists in the row, false otherwise.
     */
    public boolean rowContains(int row, char element) {
        for (int col = 0; col < cols(); col++) {
            if (get(new CellPosition(row, col)) == element) {
                return true;
            }
        }
        return false;
    }

    /**
     * Set all the cells in a row to a specific value.
     * 
     * @param row   The row to set.
     * @param value The value to set the row to.
     */
    public void setRow(int row, char value) {
        for (int col = 0; col < cols(); col++) {
            set(new CellPosition(row, col), value);
        }
    }

    /**
     * Copy the contents of one row into another row.
     * 
     * @param sourceRow      The row to copy from.
     * @param destinationRow The row to copy to.
     */
    public void copyRowInto(int sourceRow, int destinationRow) {
        for (int col = 0; col < cols(); col++) {
            set(new CellPosition(destinationRow, col), get(new CellPosition(sourceRow, col)));
        }
    }

    /**
     * Remove all full rows from the board.
     * A row is considered full if there are no ('-'), empty cells in the row.
     * 
     * @return The number of rows removed.
     */
    public int removeFullRows() {
        int removedRowsCount = 0;
        int a = rows() - 1;
        int b = rows() - 1;

        while (a >= 0) {
            while (b >= 0 && !rowContains(b, '-')) {
                removedRowsCount++;
                b--;
            }
            if (b >= 0 && b < rows()) {
                copyRowInto(b, a);
            } else {
                setRow(a, '-');
            }
            a--;
            b--;
        }
        return removedRowsCount;
    }

    /**
     * Create a new TetrisBoard with the given contents, defined by an array of
     * strings.
     * Each string in the array represents a row in the board.
     * 
     * @param contents An array of strings representing the board contents.
     * @return A new TetrisBoard with the given contents.
     */
    public static TetrisBoard getTetrisBoardWithContents(String[] contents) {
        int rows = contents.length;
        int cols = contents[0].length();
        TetrisBoard board = new TetrisBoard(rows, cols);
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                board.set(new CellPosition(row, col), contents[row].charAt(col));
            }
        }
        return board;
    }

}
