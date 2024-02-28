package no.uib.inf101.tetris.model;


import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.Grid;
import no.uib.inf101.tetris.model.tetromino.Tetromino;

public class TetrisBoard extends Grid<Character> {

    //private ArrayList<ArrayList<Character>> grid;
    //private int cols;

    public TetrisBoard(int rows, int cols) {
        super(rows, cols, '-');
 
    }

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
    }
    

