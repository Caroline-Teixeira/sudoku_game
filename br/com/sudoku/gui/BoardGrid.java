package br.com.sudoku.gui;

import javax.swing.*;
import java.awt.*;

import br.com.sudoku.model.Cell;
import br.com.sudoku.model.InvalidMoveException;
import br.com.sudoku.service.SudokuGameService;

public class BoardGrid extends JPanel {

    private final JButton[][] cells; // para matriz
    private final SudokuGameService gameService;
    private boolean gameStarted = false; // Flag para indicar se o jogo foi iniciado

    public BoardGrid(SudokuGameService gameService) {
        this.cells = new JButton[9][9];
        this.gameService = gameService;
        setLayout(new GridLayout(9, 9, 2, 2));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Espaçamento ao redor do tabuleiro
        initBoard();
    }

    // Inicializa o tabuleiro com botões
    private void initBoard() {
        for (int i = 0; i < 81; i++) {
            int row = i / 9; // 9 linhas
            int col = i % 9; // 9 colunas

            JButton button = new JButton();
            button.setFont(new Font("Arial", Font.BOLD, 20));
            button.setFocusPainted(false);

            // bordas para destacar as células
            int top;
            if (row % 3 == 0) {
                top = 2;
            } else {
                top = 1;
            }

            int left;
            if (col % 3 == 0) {
                left = 2;
            } else {
                left = 1;
            }

            int bottom;
            if (row == 8 || row % 3 == 2) {
                bottom = 2;
            } else {
                bottom = 1;
            }

            int right;
            if (col == 8 || col % 3 == 2) {
                right = 2;
            } else {
                right = 1;
            }
            // Define a borda do botão
            button.setBorder(BorderFactory.createMatteBorder(top, left, bottom, right, Color.BLACK));

            // Configura o listener para cliques
            int finalRow = row;
            int finalCol = col;
            button.addActionListener(e -> cellClick(finalRow, finalCol));

            button.setEnabled(false); // Desabilita os botões inicialmente
            cells[row][col] = button;
            add(button);
        }
        updateBoard();
    }
    
    // Método chamado ao clicar em uma célula
    private void cellClick(int row, int col) {
        if (!gameStarted) {
            JOptionPane.showMessageDialog(this, "Inicie um novo jogo primeiro.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Verifica se a célula é fixa
        Cell cell = gameService.getBoard().getCells().get(new br.com.sudoku.model.Position(row, col)); // endereço do Position
        if (cell != null && cell.isFixedByGame()) {
            JOptionPane.showMessageDialog(this, "Não é possível alterar uma célula fixa.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Solicita entrada do usuário
        String input = JOptionPane.showInputDialog(this, "Digite um número (1-9) ou deixe vazio para remover:", "");
        if (input == null) return; // Cancelar

        try {
            if (input.trim().isEmpty()) { // se o usuário deixar em branco
                gameService.removeCell(row, col);
            } else {
                // Verifica se o valor é um número válido
                int value = Integer.parseInt(input.trim());
                gameService.addCell(new Cell(row, col, value, false));
            }
            updateBoard();


        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Entrada inválida. Use números de 1 a 9.", "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (InvalidMoveException e) {
            JOptionPane.showMessageDialog(this, "Erro: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void updateBoard() {
    for (int i = 0; i < 81; i++) {
        int row = i / 9;
        int col = i % 9;

        Cell cell = gameService.getBoard().getCells().get(new br.com.sudoku.model.Position(row, col));
        JButton button = cells[row][col];
        if (cell != null && cell.getValue() != 0) {
            button.setText(String.valueOf(cell.getValue()));
            button.setBackground(cell.isFixedByGame() ? Color.LIGHT_GRAY : Color.WHITE);
        } else {
            button.setText("");
            button.setBackground(Color.WHITE);
            }
        }
    
    
    // Verifica se o jogo foi iniciado e habilita os botões
        gameStarted = !gameService.getBoard().getCells().isEmpty();
        for (int i = 0; i < 81; i++) {
            cells[i / 9][i % 9].setEnabled(gameStarted);
        }
           
    // Verifica se o jogo está completo e exibe o pop-up de vitória
        if (gameService.getStatusNow() == br.com.sudoku.model.GameStatus.COMPLETO) {
            JOptionPane.showMessageDialog(this, "Você finalizou o jogo, parabéns!", "Vitória", JOptionPane.INFORMATION_MESSAGE);
        }
}
}