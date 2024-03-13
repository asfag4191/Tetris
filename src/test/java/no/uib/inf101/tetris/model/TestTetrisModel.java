package no.uib.inf101.tetris.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.GridCell;
import no.uib.inf101.tetris.model.tetromino.PatternedTetrominoFactory;
import no.uib.inf101.tetris.model.tetromino.Tetromino;
import no.uib.inf101.tetris.model.tetromino.TetrominoFactory;
import no.uib.inf101.tetris.view.ViewableTetrisModel;

public class TestTetrisModel {
    @Test
    public void initialPositionOfO() {
        TetrisBoard board = new TetrisBoard(20, 10);
        TetrominoFactory factory = new PatternedTetrominoFactory("O");
        ViewableTetrisModel model = new TetrisModel(board, factory);

        List<GridCell<Character>> tetroCells = new ArrayList<>();
        for (GridCell<Character> gc : model.getFallingTiles()) {
            tetroCells.add(gc);
        }

        assertEquals(4, tetroCells.size());
        assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(0, 4), 'O')));
        assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(0, 5), 'O')));
        assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(1, 4), 'O')));
        assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(1, 5), 'O')));
    }

    @Test
    public void initialPositionOfI() {
        TetrisBoard board = new TetrisBoard(20, 10);
        TetrominoFactory factory = new PatternedTetrominoFactory("I");
        ViewableTetrisModel model = new TetrisModel(board, factory);

        List<GridCell<Character>> tetroCells = new ArrayList<>();
        for (GridCell<Character> gc : model.getFallingTiles()) {
            tetroCells.add(gc);
        }

        assertEquals(4, tetroCells.size());
        assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(0, 6), 'I')));
        assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(0, 4), 'I')));
        assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(0, 5), 'I')));
        assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(0, 3), 'I')));
    }



    @Test
    void testMoveReturnsTrueWhenSuccessful() {
        TetrisBoard tetrisBoard = new TetrisBoard(20, 10);
        tetrisBoard.set(new CellPosition(0, 0), 'g');
        TetrominoFactory tetrominoFactory = new PatternedTetrominoFactory("I");
        TetrisModel tetrisModel = new TetrisModel(tetrisBoard, tetrominoFactory);

        assertTrue(tetrisModel.moveTetromino(0, 1), "Moves collumn to right");
        assertTrue(tetrisModel.moveTetromino(0, -1), "Moves collumn to left");
        assertTrue(!tetrisModel.moveTetromino(30, 0), "Moves row out of grid");
        assertTrue(!tetrisModel.moveTetromino(0, -4), "Moves row out of grid");
    }

    @Test
    void testMoveChangesFallingTiles() {
        TetrisBoard tetrisBoard = new TetrisBoard(20, 10);
        TetrominoFactory tetrominoFactory = new PatternedTetrominoFactory("O");
        TetrisModel tetrisModel = new TetrisModel(tetrisBoard, tetrominoFactory);

        ArrayList<GridCell<Character>> cell = new ArrayList<>();
        for (GridCell<Character> move : tetrisModel.getFallingTiles()) {
            cell.add(move);
        }
        assertTrue(cell.contains(new GridCell<>(new CellPosition(0, 4), 'O')));
        assertTrue(cell.contains(new GridCell<>(new CellPosition(0, 5), 'O')));
        assertTrue(cell.contains(new GridCell<>(new CellPosition(1, 4), 'O')));
        assertTrue(cell.contains(new GridCell<>(new CellPosition(1, 5), 'O')));

        tetrisModel.moveTetromino(0, 1);

        ArrayList<GridCell<Character>> cell2 = new ArrayList<>();
        for (GridCell<Character> move : tetrisModel.getFallingTiles()) {
            cell2.add(move);
        }

        assertTrue(cell2.contains(new GridCell<>(new CellPosition(0, 5), 'O')));
        assertTrue(cell2.contains(new GridCell<>(new CellPosition(0, 6), 'O')));
        assertTrue(cell2.contains(new GridCell<>(new CellPosition(1, 5), 'O')));
        assertTrue(cell2.contains(new GridCell<>(new CellPosition(1, 6), 'O')));

    }

    @Test
    public void testMoveTetrominoToOccupiedSpace() {
        TetrisBoard board = new TetrisBoard(20, 10);
        //Set the cells (0,7) to be occupied.
        board.set(new CellPosition(0, 7), 'X');
        TetrominoFactory factory = new PatternedTetrominoFactory("I");
        TetrisModel model = new TetrisModel(board, factory);

        ArrayList<GridCell<Character>> cell = new ArrayList<>();
        for (GridCell<Character> move : model.getFallingTiles()) {
            cell.add(move);
        }

        assertTrue(cell.contains(new GridCell<>(new CellPosition(0, 3), 'I')));
        assertTrue(cell.contains(new GridCell<>(new CellPosition(0, 4), 'I')));
        assertTrue(cell.contains(new GridCell<>(new CellPosition(0, 5), 'I')));
        assertTrue(cell.contains(new GridCell<>(new CellPosition(0, 6), 'I')));

        model.moveTetromino(0, 1);

        ArrayList<GridCell<Character>> cell2 = new ArrayList<>();
        for (GridCell<Character> move : model.getFallingTiles()) {
            cell2.add(move);
        }

        assertTrue(cell2.contains(new GridCell<>(new CellPosition(0, 4), 'I')));
        assertTrue(cell2.contains(new GridCell<>(new CellPosition(0, 5), 'I')));
        assertTrue(cell2.contains(new GridCell<>(new CellPosition(0, 6), 'I')));
        assertFalse(cell2.contains(new GridCell<>(new CellPosition(0, 7), 'I')));

    }

    @Test
    public void testDroptetromino() {
        // Create a new TetrisModel object with a TetrisBoard of 20x10 cells
        TetrisBoard board = new TetrisBoard(20, 10);
        // Create a TetrominoFactory object that generates 'I' shaped Tetrominos
        TetrominoFactory factory = new PatternedTetrominoFactory("I");
        TetrisModel model = new TetrisModel(board, factory);

        // Drop the Tetromino onto the board
        model.dropTetromino();

        // Verify that the Tetromino has landed in the correct position
        assertEquals(board.get(new CellPosition(19, 3)), 'I');
        assertEquals(board.get(new CellPosition(19, 4)), 'I');
        assertEquals(board.get(new CellPosition(19, 5)), 'I');
        assertEquals(board.get(new CellPosition(19, 6)), 'I');
    }

    @Test
    public void testClockTick() {
        // Create the TetrisBoard, TetrominoFactory, and TetrisModel
        TetrisBoard board = new TetrisBoard(20, 10);
        TetrominoFactory factory = new PatternedTetrominoFactory("I");
        TetrisModel model = new TetrisModel(board, factory);

        // Get the current state of the falling tetromino
        List<GridCell<Character>> tetroCells = new ArrayList<>();
        for (GridCell<Character> gc : model.getFallingTiles()) {
            tetroCells.add(gc);
        }

        // Update the tetromino state by one tick
        model.clockTick();

        // Get the updated state of the falling tetromino
        List<GridCell<Character>> tetroCellsClockTick = new ArrayList<>();
        for (GridCell<Character> gc : model.getFallingTiles()) {
            tetroCellsClockTick.add(gc);
        }

        // Verify that the tetromino has been updated
        assertNotEquals(tetroCells, tetroCellsClockTick);

        assertEquals(4, tetroCellsClockTick.size());
        // Verify that the tetromino has moved down one row
        assertTrue(tetroCellsClockTick.contains(new GridCell<>((new CellPosition(1, 3)), 'I')));
        assertTrue(tetroCellsClockTick.contains(new GridCell<>((new CellPosition(1, 4)), 'I')));
        assertTrue(tetroCellsClockTick.contains(new GridCell<>((new CellPosition(1, 5)), 'I')));
        assertTrue(tetroCellsClockTick.contains(new GridCell<>((new CellPosition(1, 6)), 'I')));
    }

@Test
public void testRotateTetromino() {
    //create a O and T-tetromino
    Tetromino tetromino1= Tetromino.newTetromino('T');
    Tetromino tetromino2= Tetromino.newTetromino('O');

    //Chech if its changed when rotated
    assertNotEquals(tetromino1, tetromino1.rotatedTetromino());

    //chech if it changes when rotated 4 times
    assertEquals(tetromino1, tetromino1.rotatedTetromino().rotatedTetromino().rotatedTetromino().rotatedTetromino());

   // Chech if O-tetromino changes when rotated
   assertEquals(tetromino2, tetromino2.rotatedTetromino());
}

}
