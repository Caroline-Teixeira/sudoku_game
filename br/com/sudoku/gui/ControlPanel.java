package br.com.sudoku.gui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import br.com.sudoku.model.Cell;
import br.com.sudoku.service.SudokuGameService;

public class ControlPanel extends JPanel {

    private final SudokuGameService gameService;
    private final BoardGrid boardGrid;

    public ControlPanel(SudokuGameService gameService, BoardGrid boardGrid) {
        this.gameService = gameService;
        this.boardGrid = boardGrid;
        setLayout(new GridLayout(3, 1, 10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        initControls();
    }

    // Inicializa os botões de controle
    private void initControls() {
        JButton newGameButton = new JButton("Novo Jogo");
        JButton clearButton = new JButton("Limpar");
        JButton checkButton = new JButton("Verificar Conflitos");

        newGameButton.addActionListener(e -> startNewGame());
        clearButton.addActionListener(e -> clearBoard());
        checkButton.addActionListener(e -> checkConflicts());

        add(newGameButton);
        add(clearButton);
        add(checkButton);
    }

    private void startNewGame() {
        java.util.List<Cell> initialCells = new ArrayList<>();
        // Exemplo de células iniciais para o Sudoku
        initialCells.add(new Cell(0, 0, 5, true));
        initialCells.add(new Cell(0, 1, 3, true));
        initialCells.add(new Cell(1, 0, 6, true));
        gameService.startGame(initialCells);
        boardGrid.updateBoard();
    }

    private void clearBoard() {
        gameService.clearUserInputs();
        boardGrid.updateBoard();
    }

    private void checkConflicts() {
        if (gameService.hasConflict()) {
            JOptionPane.showMessageDialog(this, "O tabuleiro contém conflitos!", "Conflitos", JOptionPane.WARNING_MESSAGE);
        } else if (gameService.getStatusNow() == br.com.sudoku.model.GameStatus.COMPLETO) {
            JOptionPane.showMessageDialog(this, "Parabéns! O Sudoku está resolvido!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Nenhum conflito encontrado, mas o tabuleiro está incompleto.", "Status", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}