# 🧩 Sudoku Game in Java (Swing + Maven)

![Made with Java](https://img.shields.io/badge/Made%20with-Java-orange?style=for-the-badge&logo=java) ![Swing GUI](https://img.shields.io/badge/Swing-Graphical%20Interface-blueviolet?style=for-the-badge) ![Maven](https://img.shields.io/badge/Maven-Build%20Tool-important?style=for-the-badge&logo=apachemaven)

<a href="https://github.com/Caroline-Teixeira/sudoku_game/blob/main/README.md">
<img src="https://raw.githubusercontent.com/yammadev/flag-icons/refs/heads/master/png/BR%402x.png" alt="Portuguese" ></a>

## 📖 Description

This project is a **Sudoku Game** developed in **Java 21** with a graphical interface using **Swing**, organized in layers and with dependency management via **Maven**.
<a href="https://www.linkedin.com/posts/caroline-francieli-teixeira-05a56b266_java-swing-maven-activity-7349500214354837504-9pHx?utm_source=share&utm_medium=member_desktop&rcm=ACoAAEE5DIYBUFEM9BE7AK3ObchKODr8J0Slyrc">Video</a>

The game features automatic valid board generation via `SudokuSolver`, save and load game functionality via notepad, and a control panel with action buttons. 
To understand the mathematics logic access the file 
<a href="https://github.com/Caroline-Teixeira/sudoku_game/blob/main/Math.md">Math.md.</a>


## 🎯 Features

- ✅ Valid Sudoku board generation via backtracking algorithm
- ✅ Desktop graphical interface made with Java Swing
- ✅ Move validation (no number repetition)
- ✅ New game button (generates new valid board)
- ✅ Save and load game state option
- ✅ Status messages and control via StatusBar
- ✅ Complete board debug (optional in console)
- ✅ Packaging as **executable .jar** via Maven Shade Plugin
- ✅ Execution via **.bat** and **.vbs**

## 📂 Project Structure

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
│                       ├── core/
│                       ├── gui/
│                       ├── model/
│                       ├── service/strategy
│                       ├── util/
│                       └── view/
└── target/
    └── SudokuGame-1.0-SNAPSHOT.jar
```

## 🚀 How to Run

### 🖥️ Via IDE
- Go to the `Sudoku.java` class and press the "run" button.
- To see the initial version, go to the `Main` class and execute it.

### 🖥️ Via Terminal

```bash
mvn clean package
java -jar target/SudokuGame-1.0-SNAPSHOT.jar
```

### 📦 Via Executables

- `run_sudoku.bat` → runs with visible console (just click)
- `run_sudoku.vbs` → runs in background (no console, just click)

### Prerequisites:
- Java 21 or higher installed
- Maven installed and configured in PATH

## 🛠️ Technologies Used

- **Java 21**
- **Java Swing**
- **Maven 3.9.6**
- **Streams API**
- **Backtracking Solver**
- **Maven Shade Plugin** for packaging

## 📖 Main Classes and Packages Explanation

**Packages**
- `core` → Contains the game's configuration interface classes (difficulty levels only)
- `gui` → Contains the game's graphical interface classes
- `model` → Classes with respective attributes, error handling, and cell position rules
- `service` → Contains business rules, how the board works, game status and game strategies
- `util` → Board template, board generator (SudokuSolver) and color scheme
- `view` → Contains the Menu class, which runs the prototype version of the game in Main

**Classes**
- `SudokuSolver` → Automatic valid board generation algorithm, to generate difficulty levels
- `SudokuGameService` → Business rules and game control
- `BoardService` → Cell and board manipulation and control
- `SudokuWindow` → Main game window (GUI)
- `ControlPanel` → Game control buttons (New Game, Save, Load)
- `StatusBar` → Displays status messages at the window border
- `ConsolePrinter` → Prints the board to console (debug)
- `BoardTemplate` → Template for fixed or custom boards

## 🔁 Design Patterns 
This project uses some of the main Design Patterns to keep the code clean, modular and maintainable:

- ✅ Singleton – `ConfigurationManager`: Ensures that only one global instance of the configuration manager (such as the game's difficulty level) is created at runtime.

- ✅ Observer – `StatusBar`: Implements the GameStatusListener interface and "observes" when the game's status changes, automatically updating the interface.

- ✅ Strategy – `DifficultyStrategy`: Defines different difficulty strategies (easy, medium, hard), which can be applied dynamically without changing the game's core logic.
<br>
*The game selects the appropriate strategy based on the level defined in the ConfigurationManager.*


## 📌 Tips to Improve or Expand

- 🐱 Implement custom visual theme for Swing or JavaFX
- 💾 Create saved games history
- 🎮 Implement other difficulty levels
- 🖥️ Add unit tests with JUnit and refactor the existing code.

## 📄 License

This project is under the [MIT](LICENSE) license.

## 👩‍💻 Author

<a href="https://github.com/Caroline-Teixeira">Caroline 💙</a>

---

📌 *Project developed for the DIO (Digital Innovation One) challenge.*
