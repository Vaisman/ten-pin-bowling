package com.h5g.bowling.common.validation;

import com.h5g.bowling.common.BowlingGameConstants;
import com.h5g.bowling.common.model.ScoreFrame;
import com.h5g.bowling.exceptions.BowlingException;

import java.util.List;

public class FramesHelper {

  private FramesHelper() {
  }

  public static void validateScoreFrames(List<ScoreFrame> scoreFrames) {
    if (scoreFrames == null) {
      throw new BowlingException("Score frames is null.");
    } else if (scoreFrames.size() != BowlingGameConstants.GAME_FRAMES_COUNT &&
        scoreFrames.size() != BowlingGameConstants.GAME_FULL_FRAMES_COUNT) {
      throw new BowlingException(
          String.format("Frames count is %d. min: %d max: %d", scoreFrames.size(),
              BowlingGameConstants.GAME_FRAMES_COUNT, BowlingGameConstants.GAME_FULL_FRAMES_COUNT));
    }
  }
}
