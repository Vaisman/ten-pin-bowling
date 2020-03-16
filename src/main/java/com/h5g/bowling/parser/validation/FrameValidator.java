package com.h5g.bowling.parser.validation;

import com.h5g.bowling.common.model.FrameTurn;
import com.h5g.bowling.common.BowlingGameConstants;
import com.h5g.bowling.exceptions.ScoreInfoParserException;

import java.util.List;

public class FrameValidator {

  private FrameValidator() {
  }

  public static void validateFirstSpare(List<FrameTurn> frameTurns) {
    if (frameTurns.get(0) == FrameTurn.SPARE) {
      throw new ScoreInfoParserException("First turn can't be spare.");
    }
  }

  public static void validateDoubleStrike(List<FrameTurn> frameTurns) {
    if (frameTurns.size() == BowlingGameConstants.FRAME_TURNS_MAX_COUNT &&
        (frameTurns.get(0) == FrameTurn.TEN || frameTurns.get(1) == FrameTurn.TEN)) {
      throw new ScoreInfoParserException("Two strike in one turn.");
    }
  }

  public static void validateSum(List<FrameTurn> frameTurns) {
    if (frameTurns.size() == BowlingGameConstants.FRAME_TURNS_MIN_COUNT && (frameTurns.get(0)
        != FrameTurn.TEN)) {
      throw new ScoreInfoParserException(
          "Invalid one turn frame sum: " + frameTurns.get(0).score());
    } else if (frameTurns.size() == BowlingGameConstants.FRAME_TURNS_MAX_COUNT &&
        (frameTurns.get(0).score() + frameTurns.get(1).score() > FrameTurn.TEN.score())) {
      throw new ScoreInfoParserException("Invalid frame sum: " + frameTurns.get(0).score() +
          frameTurns.get(1).score());
    }
  }
}
