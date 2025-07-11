package br.com.sudoku.view;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import br.com.sudoku.model.Cell;
import br.com.sudoku.model.InvalidMoveException;
import br.com.sudoku.model.SudokuGame;
import br.com.sudoku.service.SudokuGameService;

import br.com.sudoku.util.ConsolePrinter;

public class Menu {

    private final SudokuGameService service;
    private final Scanner scanner;
    
    
    // Construtor
    public Menu() {
        this.service = new SudokuGameService(new SudokuGame());
        this.scanner = new Scanner(System.in);
    }

    public void start() {
    

        while (true) {
            
            System.out.println("=== Menu do Jogo Sudoku ===");
            System.out.println("1. Iniciar Jogo");
            System.out.println("2. Adicionar Célula");
            System.out.println("3. Remover Célula");
            System.out.println("4. Limpar Jogadas");
            System.out.println("5. Sair");
            System.out.println("===========================");
            System.out.println("Escolha uma opção (1-5): ");
            
            
            try {
            String input = scanner.nextLine().trim();
            int option = Integer.parseInt(input);
            switch (option) {
                case 1 -> {
                    startGame();
                    printGame();
                }
                case 2 -> {
                    addCell(); 
                    printGame();         
                }   
                case 3 -> {
                    removeCell();
                    printGame();
                }
                case 4 -> {
                    clearUserInputs();
                    printGame();
                }
                
                case 5 -> {
                    ConsolePrinter.printWarning("Saindo...");
                    scanner.close();
                    return;
                }
                default -> ConsolePrinter.printError("Opção inválida! Tente novamente.");
                    
            }
        }
        catch (NumberFormatException e) {
            ConsolePrinter.printError("Erro: A entrada deve ser um número válido.");
            }
        }
    }
    // Início
    private void startGame(){
        List<Cell> initialCells = new ArrayList<>();
        initialCells.add(new Cell (0,0,5, true));
        initialCells.add(new Cell(0, 1, 3, true));
        initialCells.add(new Cell(1, 0, 6, true));

        service.startGame(initialCells);
        ConsolePrinter.printSuccess("Novo jogo");
        ConsolePrinter.printInfo("Status do jogo: " + service.getStatusNow());

    }

    // nova célula
    private void addCell() {
        try {
            System.out.print("Digite uma linha (0-8): ");
            int row = scanner.nextInt();
            System.out.print("Digite uma coluna (0-8): ");
            int col = scanner.nextInt();
            System.out.print("Número a inserir na célula (1-9): ");
            int value = scanner.nextInt();
            scanner.nextLine(); 

        
            Cell cell = new Cell(row, col, value, false);  // se a célua é inserida pelo jogador
            service.addCell(cell);
            ConsolePrinter.printSuccess("Célula adicionada.");
            ConsolePrinter.printInfo("Status do jogo: " + service.getStatusNow());

        } 
        

        catch (InputMismatchException e) {
            ConsolePrinter.printError("Erro: Digite apenas números válidos!");
            scanner.nextLine(); }

        catch (InvalidMoveException e){
            ConsolePrinter.printError("Erro: " + e.getMessage());

        }
    }

    // remover célula
    private void removeCell(){
        try{
            System.out.print("Digite uma linha (0-8): ");
            int row = scanner.nextInt();
            System.out.print("Digite uma coluna (0-8): ");
            int col = scanner.nextInt();
            scanner.nextLine(); 

        
            service.removeCell(row, col);
            ConsolePrinter.printSuccess("Célula removida com sucesso.");
            ConsolePrinter.printInfo("Status do jogo: " + service.getStatusNow());
            
        } 
        catch (InputMismatchException e) {
            ConsolePrinter.printError("Erro: Digite apenas números válidos!");
            scanner.nextLine(); }

        catch (InvalidMoveException e){
            ConsolePrinter.printError("Erro: " + e.getMessage());

        }
        
        
    }

    // remover entradas do usuário
    private void clearUserInputs(){
        service.clearUserInputs();
        ConsolePrinter.printWarning("Tabuleiro limpo.");
        ConsolePrinter.printInfo("Status do jogo: " + service.getStatusNow());

    }

    // imprime o tabuleiro
    private void printGame() {
        System.out.println(" ");
        service.printGame();
        
    }


}
