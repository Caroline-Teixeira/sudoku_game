package br.com.sudoku.gui;

import javax.swing.*;
import java.awt.*;
import br.com.sudoku.model.SudokuGame;
import br.com.sudoku.service.SudokuGameService;

public class SudokuWindow extends JFrame {

    // janela principal 
    private final SudokuGameService gameService;
    private final BoardGrid boardGrid;
    private final ControlPanel controlPanel;
    private final StatusBar statusBar;

    public SudokuWindow() {
        
        gameService = new SudokuGameService(new SudokuGame());

        // janela principal
        setTitle("Sudoku");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // componentes
        statusBar = new StatusBar();
        gameService.setStatusListener(statusBar);
        boardGrid = new BoardGrid(gameService); 
        controlPanel = new ControlPanel(gameService, boardGrid);

       
        add(boardGrid, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.EAST);
        add(statusBar, BorderLayout.SOUTH);

        // tamanho 
        setSize(800, 600);
        setMinimumSize(new Dimension(600, 600));
        setLocationRelativeTo(null);
    }

}