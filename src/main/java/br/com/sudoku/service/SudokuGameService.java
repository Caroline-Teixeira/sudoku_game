package br.com.sudoku.service;

import br.com.sudoku.gui.GameStatusListener;
import br.com.sudoku.model.Board;
import br.com.sudoku.model.Cell;
import br.com.sudoku.model.GameStatus;
import br.com.sudoku.model.Position;
import br.com.sudoku.model.SudokuGame;
import java.util.List;
import java.util.Map;

public class SudokuGameService {

  private final SudokuGame game;
  private final BoardService boardService;
  private GameStatusListener gameListener; //para informar a interface


  // Construtor
  public SudokuGameService(SudokuGame game) {
    this.game = game;
    this.boardService = new BoardService(game.getBoard()); // Inicializa o serviço de tabuleiro
  }


  // listener
  public void setStatusListener(GameStatusListener listener) {
        this.gameListener = listener;
    }


  // Método para iniciar o jogo
  public void startGame(List<Cell> initialCells) {
    boardService.loadInitialSetup(initialCells); // método do board
    game.setStatus(GameStatus.INCOMPLETO); // jogo iniciado, mas incompleto
  }

  // para ver o staus atual do jogo (read)
  public GameStatus getStatusNow() {
    return game.getStatus();
  }

  // Método para atualizar o status
  private void updateGameStatus() {
    boolean hasConflicts = boardService.hasConflict(); // invoca o método de conflito do BoardService

    Map<Position, Cell> cells = game.getBoard().getCells(); // obtém as células do tabuleiro

    // Usar long se o tamanho do tabuleiro aumentar
    int filledCells = (int) cells
      .values()
      .stream() // filtra as células preenchidas e conta
      .filter(cell -> cell.getValue() != 0)
      .count();

    if (filledCells == 0) {
      game.setStatus(GameStatus.NAO_INICIADO);
    } else if (filledCells == Board.BOARD_SIZE && !hasConflicts) {
      game.setStatus(GameStatus.COMPLETO);
    } else {
      game.setStatus(GameStatus.INCOMPLETO);
    }

      notifyStatus(); // status para o listener
  }

  // para listener
  private void notifyStatus() {
    if (gameListener != null) {
            gameListener.onStatusChanged("Status: " + game.getStatus());
  }
}

  // Método para adicionar uma célula
  public void addCell(Cell cell) {
    boardService.addCell(cell);
    updateGameStatus();
  }

  // Método para remover célula
  public void removeCell(int row, int col) {
    boardService.removeCellAt(row, col);
    updateGameStatus();
  }

  // Método para limpar jogadas
  public void clearUserInputs() {
    boardService.clearUserInputs();
    updateGameStatus();
  }

  // Método para salvar o jogo
  public void saveGame(String filePath) {
    boardService.saveGameFile(filePath);
}

  // Método para carregar o jogo
  public void loadGame(String filePath) {
    boardService.loadGameFile(filePath);
    updateGameStatus(); // Atualiza o status após carregar o jogo
  }


  public void printGame() {
    boardService.printBoard();
  }


  // para interface grafica
    public boolean hasConflict() {
        return boardService.hasConflict();
    }

    
    public Board getBoard() {
        return game.getBoard();
    }


  
}
