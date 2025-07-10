package br.com.sudoku.gui;

import javax.swing.*;
import java.awt.*;
import br.com.sudoku.model.Cell;
import br.com.sudoku.service.SudokuGameService;

import java.util.ArrayList;

public class ControlPanel extends JPanel {

    private final SudokuGameService gameService;
    private final BoardGrid boardGrid;

    public ControlPanel(SudokuGameService gameService, BoardGrid boardGrid) {
        this.gameService = gameService;
        this.boardGrid = boardGrid;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        initControls();
}

    private void initControls() {
        JButton newGameButton = new JButton("Novo Jogo");
        JButton clearButton = new JButton("Limpar");

        // Tamanho fixo dos botões
        Dimension buttonSize = new Dimension(120, 40);
        newGameButton.setPreferredSize(buttonSize);
        newGameButton.setMaximumSize(buttonSize);
        clearButton.setPreferredSize(buttonSize);
        clearButton.setMaximumSize(buttonSize);

        newGameButton.setMargin(new Insets(5, 10, 5, 10));
        clearButton.setMargin(new Insets(5, 10, 5, 10));

        newGameButton.addActionListener(e -> startNewGame());
        clearButton.addActionListener(e -> clearBoard());

        add(newGameButton);
        add(Box.createVerticalStrut(10));
        add(clearButton);
}

    private void startNewGame() {
        java.util.List<Cell> initialCells = new ArrayList<>();
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
}