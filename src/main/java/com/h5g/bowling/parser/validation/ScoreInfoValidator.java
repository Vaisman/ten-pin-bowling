package com.h5g.bowling.parser.validation;

import com.h5g.bowling.common.BowlingGameConstants;
import com.h5g.bowling.exceptions.ScoreInfoParserException;

public class ScoreInfoValidator {

  private static final int SCORE_INFO_MAX_LENGTH = 33;

  private ScoreInfoValidator() {
  }

  public static void validateScores(String[] scoresContainer) {
    int fullFramesCount =
        BowlingGameConstants.GAME_FULL_FRAMES_COUNT + BowlingGameConstants.GAME_BONUS_FRAMES_COUNT;

    if (scoresContainer.length != (fullFramesCount) &&
        (scoresContainer.length != (BowlingGameConstants.GAME_FRAMES_COUNT))) {
      throw new ScoreInfoParserException(
          String.format("The score should contain %d or %d frames count.",
              BowlingGameConstants.GAME_FRAMES_COUNT, fullFramesCount));
    }
  }

  public static void validateScoreInfo(String scoreInfo) {
    if (scoreInfo == null || scoreInfo.isEmpty()) {
      throw new ScoreInfoParserException("The score info should be not empty.");
    } else if (scoreInfo.length() > SCORE_INFO_MAX_LENGTH) {
      throw new ScoreInfoParserException(
          "The score info too long. Max length:" + SCORE_INFO_MAX_LENGTH);
    }
  }
}
