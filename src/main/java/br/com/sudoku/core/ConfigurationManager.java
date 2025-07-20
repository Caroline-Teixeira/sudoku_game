package br.com.sudoku.core;

import br.com.sudoku.service.strategy.DifficultyStrategy;
import br.com.sudoku.service.strategy.EasyDifficulty;
import br.com.sudoku.service.strategy.HardDifficulty;
import br.com.sudoku.service.strategy.MediumDifficulty;

public class ConfigurationManager {

  private static ConfigurationManager instance; //singleton
  private DifficultyLevel difficultyLevel;
  private DifficultyStrategy difficultyStrategy;

  private ConfigurationManager() {
    this.difficultyLevel = DifficultyLevel.FACIL; // valor inicial
    this.difficultyStrategy = new EasyDifficulty();
  }

  public static ConfigurationManager getInstance() {
    if (instance == null) {
      instance = new ConfigurationManager();
    }
    return instance;
  }

  public DifficultyLevel getDifficultyLevel() {
    return difficultyLevel;
  }

  public DifficultyStrategy getDifficultyStrategy() {
    return difficultyStrategy;
  }

  public void setDifficultyLevel(DifficultyLevel level) {
    this.difficultyLevel = level;

    switch (level) {
      case FACIL -> this.difficultyStrategy = new EasyDifficulty();
      case MEDIO -> this.difficultyStrategy = new MediumDifficulty();
      case DIFICIL -> this.difficultyStrategy = new HardDifficulty();
    }
  }
}
