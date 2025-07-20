package br.com.sudoku.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import br.com.sudoku.model.Cell;
import br.com.sudoku.model.Position;

public class SudokuSolver {
    // Métodos pra gerar tabuleiro aleatório

   private final Map<Position, Cell> cells;

   public SudokuSolver() {
    this.cells = new HashMap<>();
   }
   

   // Método recursivo para resolver o Sudoku
   private boolean solve(int row, int col) {
        
        if (row == 9) {
            return true;
        }
        
        // próxima posição
        int nextRow;
        int nextCol;
        
        if (col == 8) {
            nextRow = row + 1;
            nextCol = 0;
        } else {
            
            nextRow = row;
            nextCol = col + 1;
        }
        
        // para randomizar o tabuleiro
        List<Integer> numbers = IntStream.rangeClosed(1, 9)
                .boxed() // stream de números de 1 a 9
                .collect(Collectors.toList());
        Collections.shuffle(numbers); // Embaralhar
        
        // Tenta cada número na posição atual
        for (int num : numbers) {
            if (isSafeToAdd(row, col, num)) {
                cells.put(new Position(row, col), new Cell(row, col, num, true));
                
                // Chama recursivamente para próxima posição
                if (solve(nextRow, nextCol)) {
                    return true; // Se próxima posição teve sucesso, propaga sucesso
                }
                
                // Se chegou aqui, próxima posição falhou
                // Remove o número atual (BACKTRACK) e tenta próximo número
                cells.remove(new Position(row, col));
            }
        }
        
        // Se não conseguiu colocar nenhum número válido
        return false;
    }


    // ----
   private boolean isSafeToAdd(int row, int col, int num) {
    return cells.values().stream().noneMatch(cell ->
                (cell.getRow() == row && cell.getValue() == num) ||
                        (cell.getCol() == col && cell.getValue() == num) ||
                        (cell.getRow() / 3 == row / 3 &&
                                cell.getCol() / 3 == col / 3 &&
                                cell.getValue() == num)
        );
}



   public Map <Position, Cell> generateCompleteBoard() {
    cells.clear(); 
    solve(0, 0); // resolução começa posição (0, 0)
    return new HashMap<>(cells); // Retorna uma cópia 
}

// depuração (dev)
    public void printBoardToConsole() {
         String[][] boardMatrix = new String[9][9];

        // Inicializa com espaços vazios
        for (int i = 0; i < 9; i++) {
            Arrays.fill(boardMatrix[i], " "); // Preenche com espaços vazios
        }

        // Preenche com os números das células
        cells.values().forEach(cell ->
                boardMatrix[cell.getRow()][cell.getCol()] = String.valueOf(cell.getValue())
        );

        System.out.println("=== Tabuleiro Gerado (Debug) ===");

        for (int index = 0; index < 81; index++) {
            int row = index / 9;
            int col = index % 9;

            if (col == 0) {
                if (row % 3 == 0) System.out.println("+-------+-------+-------+");
                System.out.print("| ");
            }

            System.out.print(boardMatrix[row][col] + " ");

            if (col % 3 == 2) System.out.print("| ");

            if (col == 8) System.out.println();
        }

            System.out.println("+-------+-------+-------+");

     }
   


}
