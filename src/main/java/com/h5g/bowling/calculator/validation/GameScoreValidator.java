package com.h5g.bowling.calculator.validation;

import com.h5g.bowling.common.model.GameScore;
import com.h5g.bowling.exceptions.ScoreCalculatorException;

public class GameScoreValidator {

  private GameScoreValidator() {
  }

  public static void validateGameScore(GameScore gameScore) {
    if (gameScore == null) {
      throw new ScoreCalculatorException("The game score is null.");
    } else if (gameScore.getScoreFrames() == null) {
      throw new ScoreCalculatorException("The game score frames is null.");
    }
  }
}