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
import no.uib.inf101.tetris.model.tetromino.RandomTetrominoFactory;
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
  assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(0,5), 'O')));
  assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(1,4), 'O')));
  assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(1,5), 'O')));
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

////egne tester
    @Test
    void testSuccessfulMove() {
        TetrisBoard tetrisBoard = new TetrisBoard(20, 10);
        TetrominoFactory tetrominoFactory = new RandomTetrominoFactory();
        TetrisModel tetrisModel = new TetrisModel(tetrisBoard, tetrominoFactory);

        assertTrue(tetrisModel.moveTetromino(1, 0)); // Move down
        assertTrue(tetrisModel.moveTetromino(0, 1)); // Move right
    }

    @Test
    void testMoveChangesFallingTiles() {
        TetrisBoard tetrisBoard = new TetrisBoard(20, 10);
        TetrominoFactory tetrominoFactory = new RandomTetrominoFactory();
        TetrisModel tetrisModel = new TetrisModel(tetrisBoard, tetrominoFactory);

        GridCell<Character> initialTetromino = tetrisModel.getFallingTiles().iterator().next();
        tetrisModel.moveTetromino(0, 1);

        GridCell<Character> newTetromino = tetrisModel.getFallingTiles().iterator().next();
        assertNotEquals(initialTetromino, newTetromino);
    }
hh
    @Test
    void testCannotMoveOutOfBoard() {
        TetrisBoard tetrisBoard = new TetrisBoard(20, 10);
        TetrominoFactory tetrominoFactory = new RandomTetrominoFactory();
        TetrisModel tetrisModel = new TetrisModel(tetrisBoard, tetrominoFactory);

        assertFalse(tetrisModel.moveTetromino(0, -1)); 
    }

    @Test
    void testCannotMoveToOccupiedCell() {
        TetrisBoard tetrisBoard = new TetrisBoard(20, 10);
        TetrominoFactory tetrominoFactory = new RandomTetrominoFactory();
        TetrisModel tetrisModel = new TetrisModel(tetrisBoard, tetrominoFactory);

        // Occupy a cell on the board
        tetrisBoard.set(new CellPosition(0, 0), 'X');

        assertFalse(tetrisModel.moveTetromino(0, 0)); // Try to move to an occupied cell, should return false
    }
}

