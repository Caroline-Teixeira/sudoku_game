package br.com.sudoku;
import javax.swing.SwingUtilities;

import br.com.sudoku.gui.SudokuWindow;


public class Sudoku {

    // game interface
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SudokuWindow window = new SudokuWindow();
            window.setVisible(true);
        });
    }
}
