package no.uib.inf101.tetris.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.tetris.model.TetrisBoard;

public class TestTetrisBoard {
    
    @Test
public void prettyStringTest() {
  TetrisBoard board = new TetrisBoard(3, 4);
  board.set(new CellPosition(0, 0), 'g');
  board.set(new CellPosition(0, 3), 'y');
  board.set(new CellPosition(2, 0), 'r');
  board.set(new CellPosition(2, 3), 'b');
  String expected = String.join("\n", new String[] {
      "g--y",
      "----",
      "r--b"
  });
  assertEquals(expected, board.prettyString());
}
@Test
public void testRemoveFullRows1() {
  // Tester at fulle rader fjernes i brettet:
  // -T
  // TT
  // LT
  // L-
  // LL

  TetrisBoard board = new TetrisBoard(5, 2);
  board.set(new CellPosition(0, 1), 'T');
  board.set(new CellPosition(1, 0), 'T');
  board.set(new CellPosition(1, 1), 'T');
  board.set(new CellPosition(2, 1), 'T');
  board.set(new CellPosition(4, 0), 'L');
  board.set(new CellPosition(4, 1), 'L');
  board.set(new CellPosition(3, 0), 'L');
  board.set(new CellPosition(2, 0), 'L');

  assertEquals(3, board.removeFullRows());

  String expected = String.join("\n", new String[] {
    "--",
    "--",
    "--",
    "-T",
    "L-"
  });
  assertEquals(expected, board.prettyString());
}

@Test
public void testRemoveFullRows() {
  TetrisBoard board = TetrisBoard.getTetrisBoardWithContents(new String[] {
    "-T",
    "TT",
    "LT",
   "L-",
    "LL"
  });
  assertEquals(3, board.removeFullRows());
  String expected = String.join("\n", new String[] {
    "--",
    "--",
   "--",
    "-T",
   "L-"
  });
  assertEquals(expected, board.prettyString());
}

@Test
public void testBottomRowIsKept() {
    // Initialiserer et brett hvor nederste rad ikke er fullstendig fylt
    TetrisBoard board = TetrisBoard.getTetrisBoardWithContents(new String[]{
        "TT",
        "TT",
        "L-",
        "LL"
    });

    assertEquals(3, board.removeFullRows());
    String expected = String.join("\n", new String[]{
        "--",
        "--",
        "--",
        "L-"
    });
    assertEquals(expected, board.prettyString());
}
@Test
public void testRemoveTopRow(){
  TetrisBoard board = TetrisBoard.getTetrisBoardWithContents(new String[]{
  "TT",
  "T-",
  "L-",
  "--",
  });
  assertEquals(1, board.removeFullRows());
  String expected=String.join("\n", new String[]{
    "--",
    "T-",
    "L-",
    "--",
  });
  assertEquals(expected, board.prettyString());
}

@Test
public void testAnotherDimension(){
  TetrisBoard board = TetrisBoard.getTetrisBoardWithContents(new String[]{
    "TTT",
    "T--",
    "L--",
    "LL-",
  });
  assertEquals(1, board.removeFullRows());
  String expected=String.join("\n", new String[]{
    "---",
    "T--",
    "L--",
    "LL-",
  });
  assertEquals(expected, board.prettyString());
}

}





