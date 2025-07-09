package br.com.sudoku.gui;

import br.com.sudoku.model.SudokuGame;
import br.com.sudoku.service.SudokuGameService;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class SudokuGUI extends JFrame {

  private final SudokuGameService gameService;
  //private final SudokuBoardPanel boardPanel;
  //private final SudokuControlPanel controlPanel;

  // construtor
  public SudokuGUI() {
    gameService = new SudokuGameService(new SudokuGame());
    //boardPanel = new SudokuBoardPanel(gameService);
    //controlPanel = new SudokuControlPanel(gameService, boardPanel);

    initializeUI();
  }

  private void initializeUI() {
    setTitle("Sudoku"); // título
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // fecha o jogo
    setLayout(new BorderLayout()); // (divide em áreas como centro e sul)

    //add(boardPanel, BorderLayout.CENTER);
    //add(controlPanel, BorderLayout.SOUTH);

    pack(); // Ajusta o tamanho da janela automaticamente com base nos componentes

    setLocationRelativeTo(null); // Centraliza a janela na tela
  }

  // aplicação gráfica
  public static void main(String[] args) {
    // Usa SwingUtilities para garantir que a interface seja criada na thread correta
    SwingUtilities.invokeLater(() -> {
      // Cria uma nova instância da GUI e a torna visível
      SudokuGUI gui = new SudokuGUI();
      gui.setVisible(true);
    });
  }
}
