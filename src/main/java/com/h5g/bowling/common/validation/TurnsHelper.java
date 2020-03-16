package com.h5g.bowling.common.validation;

import com.h5g.bowling.common.BowlingGameConstants;
import com.h5g.bowling.common.model.FrameTurn;
import com.h5g.bowling.exceptions.BowlingException;

import java.util.List;

public class TurnsHelper {

  private TurnsHelper() {
  }

  public static void validateFrameTurns(List<FrameTurn> frameTurns) {
    if (frameTurns == null) {
      throw new BowlingException("Frame turns is null");
    } else if (frameTurns.size() < BowlingGameConstants.FRAME_TURNS_MIN_COUNT ||
        frameTurns.size() > BowlingGameConstants.FRAME_TURNS_MAX_COUNT) {
      throw new BowlingException(
          String.format("Frame turns in frame is %d. min: %d max: %d", frameTurns.size(),
              BowlingGameConstants.FRAME_TURNS_MIN_COUNT,
              BowlingGameConstants.FRAME_TURNS_MAX_COUNT));
    }
  }
}
