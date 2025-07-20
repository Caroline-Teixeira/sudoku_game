package br.com.sudoku.service.strategy;

import br.com.sudoku.model.Cell;
import br.com.sudoku.util.SudokuSolver;
import java.util.List;

public interface DifficultyStrategy {
    List<Cell> generateInitialCells(SudokuSolver solver);
}