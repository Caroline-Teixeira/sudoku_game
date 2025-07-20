package br.com.sudoku.service;

import br.com.sudoku.core.ConfigurationManager;
import br.com.sudoku.core.DifficultyLevel;
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
  private GameStatusListener gameListener; // para interface listener


  // Construtor
  public SudokuGameService(SudokuGame game) {
    this.game = game;
    this.boardService = new BoardService(game.getBoard()); 
  }


  // listener
  public void setStatusListener(GameStatusListener listener) {
        this.gameListener = listener;
    }


  // ----
  public void startGame(List<Cell> initialCells) {
    boardService.loadInitialSetup(initialCells); // método do board
    game.setStatus(GameStatus.INCOMPLETO); 
  }

  // read apenas
  public GameStatus getStatusNow() {
    return game.getStatus();
  }

  // ---
  private void updateGameStatus() {
    boolean hasConflicts = boardService.hasConflict(); // do BoardService

    Map<Position, Cell> cells = game.getBoard().getCells(); // obtém as células do tabuleiro

    // * Usar long se o tamanho do tabuleiro aumentar
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
            DifficultyLevel difficulty = ConfigurationManager.getInstance().getDifficultyLevel();
            gameListener.onStatusChanged(game.getStatus().toString(), difficulty);
        }
    }

  // ---
  public void addCell(Cell cell) {
    boardService.addCell(cell);
    updateGameStatus();
  }

  // ----
  public void removeCell(int row, int col) {
    boardService.removeCellAt(row, col);
    updateGameStatus();
  }

  // ----
  public void clearUserInputs() {
    boardService.clearUserInputs();
    updateGameStatus();
  }

  // ----
  public void saveGame(String filePath) {
    boardService.saveGameFile(filePath);
}

  // ---
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
