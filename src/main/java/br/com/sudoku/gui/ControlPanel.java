package br.com.sudoku.gui;

import javax.swing.*;
import java.awt.*;
import br.com.sudoku.model.Cell;
import br.com.sudoku.service.SudokuGameService;
import br.com.sudoku.util.SudokuSolver;

import java.util.ArrayList;
import java.util.List;

import java.util.Random;

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
        JButton saveButton = new JButton("Salvar Jogo");
        JButton loadButton = new JButton("Carregar Jogo");

        // Tamanho fixo dos botões
        Dimension buttonSize = new Dimension(120, 40);
        newGameButton.setPreferredSize(buttonSize);
        newGameButton.setMaximumSize(buttonSize);
        clearButton.setPreferredSize(buttonSize);
        clearButton.setMaximumSize(buttonSize);
        saveButton.setPreferredSize(buttonSize);
        saveButton.setMaximumSize(buttonSize);
        loadButton.setPreferredSize(buttonSize);
        loadButton.setMaximumSize(buttonSize);

        newGameButton.setMargin(new Insets(5, 10, 5, 10));
        clearButton.setMargin(new Insets(5, 10, 5, 10));
        saveButton.setMargin(new Insets(5, 10, 5, 10));
        loadButton.setMargin(new Insets(5, 10, 5, 10));

        newGameButton.addActionListener(e -> startNewGame());
        clearButton.addActionListener(e -> clearBoard());
        saveButton.addActionListener(e -> saveGame());
        loadButton.addActionListener(e -> loadGame());


        add(newGameButton);
        add(Box.createVerticalStrut(10));
        add(clearButton);
        add(Box.createVerticalStrut(10));
        add(saveButton);
        add(Box.createVerticalStrut(10));
        add(loadButton);
}

    // Método para iniciar um novo jogo
    private void startNewGame() {
        // Gera tabuleiro completo
        SudokuSolver solver = new SudokuSolver();
        var fullBoard = solver.generateCompleteBoard();

        

        // Remove células aleatoriamente
        List<Cell> cellsToKeep = new ArrayList<>(fullBoard.values());
        Random random = new Random();

        // Defina quantas posições manter — exemplo: manter 30 posições fixas
        while (cellsToKeep.size() > 30) {
            cellsToKeep.remove(random.nextInt(cellsToKeep.size()));
        }

        // Inicia o jogo com essas células
        gameService.startGame(cellsToKeep);
        boardGrid.updateBoard();



        // Debug opcional
        solver.printBoardToConsole();
    }
   
    // Limpar o tabuleiro
    private void clearBoard() {
        gameService.clearUserInputs();
        boardGrid.updateBoard();
    }

    // salvar jogo
    private void saveGame() {
    JFileChooser fileChooser = new JFileChooser();
    int option = fileChooser.showSaveDialog(this);

    if (option == JFileChooser.APPROVE_OPTION) {
        String filePath = fileChooser.getSelectedFile().getAbsolutePath();
        try {
            gameService.saveGame(filePath);
            JOptionPane.showMessageDialog(this, "Jogo salvo com sucesso!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar o jogo: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
    // carregar jogo
    private void loadGame() {
    JFileChooser fileChooser = new JFileChooser();
    int option = fileChooser.showOpenDialog(this);

    if (option == JFileChooser.APPROVE_OPTION) {
        String filePath = fileChooser.getSelectedFile().getAbsolutePath();
        try {
            gameService.loadGame(filePath);
            boardGrid.updateBoard();
            JOptionPane.showMessageDialog(this, "Jogo carregado com sucesso!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar o jogo: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}


    // Método para iniciar um novo jogo - debbug inicial
     /*private void startNewGame() {
        java.util.List<Cell> initialCells = new ArrayList<>();
        initialCells.add(new Cell(0, 0, 5, true));
        initialCells.add(new Cell(0, 1, 3, true));
        initialCells.add(new Cell(1, 0, 6, true));
        gameService.startGame(initialCells);
        boardGrid.updateBoard();
    }*/
}