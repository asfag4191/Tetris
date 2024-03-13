package no.uib.inf101.tetris.model.tetromino;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.GridCell;
import no.uib.inf101.grid.GridDimension;
import no.uib.inf101.tetris.model.TetrisBoard;

public class TestTetromino {
  @Test
  public void testHashCodeAndEquals() {
    Tetromino t1 = Tetromino.newTetromino('T');
    Tetromino t2 = Tetromino.newTetromino('T');
    Tetromino t3 = Tetromino.newTetromino('T').shiftedBy(1, 0);
    Tetromino s1 = Tetromino.newTetromino('S');
    Tetromino s2 = Tetromino.newTetromino('S').shiftedBy(0, 0);

    assertEquals(t1, t2);
    assertEquals(s1, s2);
    assertEquals(t1.hashCode(), t2.hashCode());
    assertEquals(s1.hashCode(), s2.hashCode());
    assertNotEquals(t1, t3);
    assertNotEquals(t1, s1);
  }

  @Test
  public void tetrominoIterationOfT() {
    // Create a standard 'T' tetromino placed at (10, 100) to test
    Tetromino tetro = Tetromino.newTetromino('T');
    tetro = tetro.shiftedBy(10, 100);

    // Collect which objects are iterated through
    List<GridCell<Character>> objs = new ArrayList<>();
    for (GridCell<Character> gc : tetro) {
      objs.add(gc);
    }

    // Check that we got the expected GridCell objects
    assertEquals(4, objs.size());
    assertTrue(objs.contains(new GridCell<>(new CellPosition(11, 100), 'T')));
    assertTrue(objs.contains(new GridCell<>(new CellPosition(11, 101), 'T')));
    assertTrue(objs.contains(new GridCell<>(new CellPosition(11, 102), 'T')));
    assertTrue(objs.contains(new GridCell<>(new CellPosition(12, 101), 'T')));
  }

  @Test
  public void tetrominoIterationOfS() {

    Tetromino tetro = Tetromino.newTetromino('S');
    tetro = tetro.shiftedBy(10, 100);

    // Collect which objects are iterated through
    List<GridCell<Character>> objs = new ArrayList<>();
    for (GridCell<Character> gc : tetro) {
      objs.add(gc);
    }

    // Check that we got the expected GridCell objects
    assertEquals(4, objs.size());
    assertTrue(objs.contains(new GridCell<>(new CellPosition(11, 101), 'S')));
    assertTrue(objs.contains(new GridCell<>(new CellPosition(11, 102), 'S')));
    assertTrue(objs.contains(new GridCell<>(new CellPosition(12, 101), 'S')));
    assertTrue(objs.contains(new GridCell<>(new CellPosition(12, 100), 'S')));
  }

  @Test
  public void tetrominoDoubleMovement() {
    Tetromino Tetromino1 = Tetromino.newTetromino('I');
    Tetromino Tetromino2 = Tetromino.newTetromino('I');

    Tetromino1.shiftedBy(1, 1).shiftedBy(1, 1);
    Tetromino2.shiftedBy(2, 2);
    assertEquals(Tetromino1, Tetromino2);

    Tetromino Tetromino3 = Tetromino.newTetromino('I');
    Tetromino Tetromino4 = Tetromino.newTetromino('I');

    Tetromino3.shiftedBy(2, 2);
    Tetromino4.shiftedBy(4, 4);
    assertEquals(Tetromino3, Tetromino4);

  }

  @Test
  public void tetrominoShiftedToTopCenterOfO() {
    Tetromino tetro = Tetromino.newTetromino('O');
    GridDimension grid = new TetrisBoard(20, 10);
    tetro = tetro.shiftedToTopCenterOf(grid); // Use shiftedToTopCenterOf(GridDimension gridDimension)

    // Collect which objects are iterated through
    List<GridCell<Character>> objs = new ArrayList<>();
    for (GridCell<Character> gc : tetro) {
      objs.add(gc);
    }

    // Check that we got the expected GridCell objects
    assertEquals(4, objs.size());
    assertTrue(objs.contains(new GridCell<>(new CellPosition(0, 4), 'O')));
    assertTrue(objs.contains(new GridCell<>(new CellPosition(0, 5), 'O')));
    assertTrue(objs.contains(new GridCell<>(new CellPosition(1, 4), 'O')));
    assertTrue(objs.contains(new GridCell<>(new CellPosition(1, 5), 'O')));
  }

  @Test
  public void tetrominoShiftedToTopCenterOfS() {
    Tetromino tetro = Tetromino.newTetromino('S');
    GridDimension grid = new TetrisBoard(20, 10);
    tetro = tetro.shiftedToTopCenterOf(grid); // Use shiftedToTopCenterOf(GridDimension gridDimension)

    // Collect which objects are iterated through
    List<GridCell<Character>> objs = new ArrayList<>();
    for (GridCell<Character> gc : tetro) {
      objs.add(gc);
    }

    // Check that we got the expected GridCell objects
    assertEquals(4, objs.size());
    assertTrue(objs.contains(new GridCell<>(new CellPosition(0, 4), 'S')));
    assertTrue(objs.contains(new GridCell<>(new CellPosition(0, 5), 'S')));
    assertTrue(objs.contains(new GridCell<>(new CellPosition(1, 3), 'S')));
    assertTrue(objs.contains(new GridCell<>(new CellPosition(1, 4), 'S')));
  }
}
