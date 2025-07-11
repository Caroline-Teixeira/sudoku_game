
# 🧩 Sudoku Game em Java (Swing + Maven)

![Feito com Java](https://img.shields.io/badge/Feito%20com-Java-orange?style=for-the-badge&logo=java)
![Swing GUI](https://img.shields.io/badge/Swing-Interface%20Gr%C3%A1fica-blueviolet?style=for-the-badge)
![Maven](https://img.shields.io/badge/Maven-Build%20Tool-important?style=for-the-badge&logo=apachemaven)

<a href="https://github.com/Caroline-Teixeira/sudoku_game/blob/main/README_ENG.md"><img src="https://raw.githubusercontent.com/yammadev/flag-icons/refs/heads/master/png/US%402x.png" alt="Inglês" ></a>

## 📖 Descrição

Este projeto é um **Jogo de Sudoku** desenvolvido em **Java 21** com interface gráfica usando **Swing**, organizado em camadas e com gerenciamento de dependências via **Maven**.
<a href="https://www.linkedin.com/posts/caroline-francieli-teixeira-05a56b266_java-swing-maven-activity-7349500214354837504-9pHx?utm_source=share&utm_medium=member_desktop&rcm=ACoAAEE5DIYBUFEM9BE7AK3ObchKODr8J0Slyrc">Video</a>

O jogo possui geração automática de tabuleiro válido via `SudokuSolver`, salvamento e carregamento de partidas via bloco de notas e um painel de controle com botões de ação.
Para entender a lógica matemática acesse o arquivo 
<a href="https://github.com/Caroline-Teixeira/sudoku_game/blob/main/Matematica.md">Matematica.md</a>


## 🎯 Funcionalidades

- ✅ Geração de tabuleiro Sudoku válido via algoritmo backtracking
- ✅ Interface gráfica desktop feita com Java Swing
- ✅ Validação de jogadas (sem repetição de números)
- ✅ Botão de novo jogo (gera novo tabuleiro válido)
- ✅ Opção de salvar e carregar o estado do jogo
- ✅ Mensagens de status e controle via StatusBar
- ✅ Debug do tabuleiro completo (opcional no console)
- ✅ Empacotamento como **.jar executável** via Maven Shade Plugin
- ✅ Execução por **.bat** e **.vbs**

## 📂 Estrutura do Projeto

```
.
├── pom.xml
├── run_sudoku.bat
├── run_sudoku.vbs
├── src/
│   └── main/
│       └── java/
│           └── br/
│               └── com/
│                   └── sudoku/
│                       ├── gui/
│                       ├── model/
│                       ├── service/
│                       ├── util/
│                       └── view/
└── target/
    └── SudokuGame-1.0-SNAPSHOT.jar
```

## 🚀 Como Executar

### 🖥️ Via IDE
- Vá até a classe `Sudoku.java` e aperte o botão "run".
- Para ver a versão inicial, vá até a classe `Main` e execute-a.


### 🖥️ Via Terminal

```bash
mvn clean package
java -jar target/SudokuGame-1.0-SNAPSHOT.jar
```

### 📦 Via Executáveis

- `run_sudoku.bat` → executa com console visível (apenas clicar)
- `run_sudoku.vbs` → executa em background (sem console, apenas clicar)

### Pré-requisitos:
- Java 21 ou superior instalado
- Maven instalado e configurado no PATH

## 🛠️ Tecnologias Utilizadas

- **Java 21**
- **Java Swing**
- **Maven 3.9.6**
- **Streams API**
- **Backtracking Solver**
- **Maven Shade Plugin** para empacotamento

## 📖 Explicação das Principais Classes e Pacotes

**Pacotes**
- `gui` → Contém as classes da interface gráfica do jogo
- `model` → Classes com os respectivos atributos, tratamento de erros e regras pra posição de células
- `service` → Contém a regras do négocio, como funciona o tabuleiro e status do jogo
- `util` → Template de tabuleiro, gerador de tabuleiro (SudokuSolver) e esquema de cores
- `view` → Contém a classe Menu, que roda a versão protótipo do jogo no Main.


**Classes**
- `SudokuSolver` → Algoritmo de geração automática de tabuleiros válidos
- `SudokuGameService` → Regras de negócio e controle do jogo
- `BoardService` → Manipulação e controle das células e tabuleiros
- `SudokuWindow` → Janela principal do jogo (GUI)
- `ControlPanel` → Botões de controle do jogo (Novo Jogo, Salvar, Carregar)
- `StatusBar` → Exibe mensagens de status, na borda da janela
- `ConsolePrinter` → Imprime o tabuleiro no console (debug)
- `BoardTemplate` → Template para tabuleiros fixos ou personalizados

## 📌 Dicas para Melhorar ou Expandir

- 🐱 Implementar tema visual personalizado para o Swing
- 💾 Criar histórico de jogos salvos
- 🎮 Implementar níveis de dificuldades
- 🖥️ Adicionar testes unitários com JUnit

## 📄 Licença

Este projeto está sob a licença [MIT](LICENSE).

## 👩‍💻 Autor(a)

<a href="https://github.com/Caroline-Teixeira">Caroline 💙</a>

---

📌 *Projeto desenvolvido para o desafio da DIO (Digital Innovation One).*
