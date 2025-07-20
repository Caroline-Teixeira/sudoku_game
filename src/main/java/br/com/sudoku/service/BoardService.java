package br.com.sudoku.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import br.com.sudoku.core.ConfigurationManager;
import br.com.sudoku.core.DifficultyLevel;
import br.com.sudoku.model.Board;
import br.com.sudoku.model.Cell;
import br.com.sudoku.model.InvalidMoveException;
import br.com.sudoku.model.Position;
import br.com.sudoku.util.AnsiColors;
import br.com.sudoku.util.BoardTemplate;

public class BoardService {
    // lógica do Sudoku

    private final Board board; // instância do tabuleiro

    // construtor
    public BoardService(Board board) {
        this.board = board;
    }


    
    public Optional<Cell> findCell(int row, int col) {
        return Optional.ofNullable(board.getCells().get(new Position(row, col)));
}

    // ----MÉTODOS------
    
    public void loadInitialSetup(List<Cell> fixedCells) {
        resetBoard();
        Map<Position, Cell> cells = board.getCells(); // obtém o mapa de células do tabuleiro

        fixedCells.forEach(cell -> {
            Position position = new Position(cell.getRow(), cell.getCol()); // Classe record: cria uma instância de Position
            if (cells.containsKey(position))
                throw new InvalidMoveException("Posição inicial duplicada em " + position);
            cells.put(position, cell);
        });
    }

    //-----
    public void addCell(Cell cell) {
        if (cell.getValue() < 1 || cell.getValue() > 9)
            throw new InvalidMoveException("Número inválido. Deve ser de 1 a 9.");

        Position position = new Position(cell.getRow(), cell.getCol());
        Cell existingCell = board.getCells().get(position); // verifica se já existe uma célula na posição

        if (existingCell != null && existingCell.isFixedByGame())
            throw new InvalidMoveException("Não é possível sobrescrever um número fixo.");

        // Verifica conflitos antes de adicionar a célula
        List<Cell> filledCells = board.getCells().values().stream()
                .filter(c -> c.getValue() != 0)
                .toList();

        String conflict = isConflictingWithOthers(cell, filledCells); // verifica se há conflitos com outras células preenchidas
            if (conflict != null) {
                throw new InvalidMoveException(conflict);
            }
            
        board.getCells().put(position, cell);
    }

    // -----
    public void removeCellAt(int row, int col) {
        Position position = new Position(row, col);
        Cell cell = board.getCells().get(position);

        if (cell == null)
            throw new InvalidMoveException("Não há número nesta posição.");
        if (cell.isFixedByGame())
            throw new InvalidMoveException("Número fixo não pode ser removido.");

        board.getCells().remove(position);
    }

    //-----
    public void clearUserInputs() {
        board.getCells().values().removeIf(cell -> !cell.isFixedByGame()); 
    }

    // configuração inicial, apenas para esta classe
    private void resetBoard() { 
        board.getCells().clear(); 
    }

    

    // -----
    private String isConflictingWithOthers(Cell currentCell, List<Cell> otherCells) {
    return otherCells.stream() 
            .filter(otherCell -> currentCell != otherCell && currentCell.getValue() == otherCell.getValue()) // Não compara com a própria célula, e considera apenas células com o mesmo valor
            .map(otherCell -> {
                if (currentCell.getRow() == otherCell.getRow()) 
                    return "Número repetido na mesma linha.";
                
                if (currentCell.getCol() == otherCell.getCol()) 
                    return "Número repetido na mesma coluna.";
            
                if (currentCell.getRow() / 3 == otherCell.getRow() / 3 && 
                    currentCell.getCol() / 3 == otherCell.getCol() / 3)
                    return "Número repetido no mesmo bloco 3x3.";
                
                return null; // sem conflitos
            })
            .filter(message -> message != null) // filtra mensagens não nulas
            .findFirst() // retorna a primeira mensagem de conflito encontrada
            .orElse(null); // se não houver conflitos, retorna null
    }

    public boolean hasConflict() {
        List<Cell> filledCells = board.getCells().values().stream()
                .filter(cell -> cell.getValue() != 0)
                .toList();

        return filledCells.stream() 
                .anyMatch(currentCell -> isConflictingWithOthers(currentCell, filledCells) != null); 
}

    // ----
    public void saveGameFile(String filePath) {
    try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
        
        DifficultyLevel difficulty = ConfigurationManager.getInstance().getDifficultyLevel();
            writer.println(difficulty != null ? difficulty.toString() : "UNKNOWN");
        
        
        board.getCells().forEach((position, cell) -> {
            writer.printf("%d,%d,%d,%b%n",
                position.row(),
                position.col(),
                cell.getValue(),
                cell.isFixedByGame());
        });
    } catch (IOException e) {
        throw new RuntimeException("Erro ao salvar o jogo: " + e.getMessage());
    }
}

    // -----
    public void loadGameFile(String filePath) {
    resetBoard();
    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
        
        String difficultyLine = reader.readLine();
            if (difficultyLine != null && !difficultyLine.isEmpty()) {
                try {
                    DifficultyLevel difficulty = DifficultyLevel.valueOf(difficultyLine);
                    ConfigurationManager.getInstance().setDifficultyLevel(difficulty);
                } catch (IllegalArgumentException e) {
                    // Se a dificuldade não for válida, mantém a padrão
                    ConfigurationManager.getInstance().setDifficultyLevel(DifficultyLevel.FACIL);
                }
            }
        
        
        reader.lines()
            .map(line -> line.split(","))
            .forEach(parts -> {
                int row = Integer.parseInt(parts[0]);
                int col = Integer.parseInt(parts[1]);
                int value = Integer.parseInt(parts[2]);
                boolean isFixed = Boolean.parseBoolean(parts[3]);
                Position pos = new Position(row, col);
                board.getCells().put(pos, new Cell(row, col, value, isFixed));
            });
    } catch (IOException e) {
        throw new RuntimeException("Erro ao carregar o jogo: " + e.getMessage());
    }
}


    // ---- Jogo via termianal
    public void printBoard(){
        String[] values = java.util.stream.IntStream.range(0, Board.BOARD_SIZE) // início 0, final 81 (9x9)

            .mapToObj(i -> {
                int row = i / 9; // linha (0 a 8)
                int col = i % 9; // coluna (0 a 8)
                
                Optional<Cell> cell = findCell(row, col); // Busca a célula na posição (linha, coluna)
                
                if (cell.isPresent()) { 
                Cell c = cell.get();     
                if (c.getValue() == 0) {  
                    return " ";
                    
                }
                    if (c.isFixedByGame()) {  
                        return AnsiColors.GREEN + c.getValue() + AnsiColors.RESET;
                    } else {
                        return String.valueOf(c.getValue()); // Se a célula não é fixa, retorna o valor normal
                    }
            } 
            else {
                return " "; 
            }
        })
            .toArray(size -> new String[size]); // Converte o IntStream em um array de Strings 

        String formattedBoard = String.format(BoardTemplate.BOARD_TEMPLATE, (Object[]) values); // Formata o tabuleiro usando o template
        System.out.println(formattedBoard);

        // Depuração: Verifica as células carregadas
        //System.out.println("Células no tabuleiro (depuração): " + board.getCells().size());
    }
}
