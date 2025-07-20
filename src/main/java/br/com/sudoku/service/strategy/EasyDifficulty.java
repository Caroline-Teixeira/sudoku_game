package br.com.sudoku.service.strategy;

import br.com.sudoku.model.Cell;
import br.com.sudoku.util.SudokuSolver;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class EasyDifficulty implements DifficultyStrategy {
    private static final int FIXED_CELLS = 40;

    @Override
    public List<Cell> generateInitialCells(SudokuSolver solver) {
        List<Cell> fullBoardCells = new ArrayList<>(solver.generateCompleteBoard().values());
        Collections.shuffle(fullBoardCells, new Random());

        return fullBoardCells.stream()
                .limit(FIXED_CELLS)
                .map(cell -> new Cell(cell.getRow(), cell.getCol(), cell.getValue(), true))
                .toList();
    }
}