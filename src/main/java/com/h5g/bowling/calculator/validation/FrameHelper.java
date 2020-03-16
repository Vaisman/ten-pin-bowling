package com.h5g.bowling.calculator.validation;

import com.h5g.bowling.common.model.FrameTurn;
import com.h5g.bowling.common.BowlingGameConstants;
import com.h5g.bowling.common.model.ScoreFrame;

import java.util.List;

public class FrameHelper {

  private FrameHelper() {
  }

  public static boolean isLastFrame(int currentFrameIndex) {
    return currentFrameIndex == BowlingGameConstants.GAME_FRAMES_COUNT - 1;
  }

  public static boolean isBeforeLastFrame(int currentFrameIndex) {
    return currentFrameIndex == BowlingGameConstants.GAME_FRAMES_COUNT - 2;
  }

  public static int getFrameScore(List<ScoreFrame> scoreFrames, int frameIndex, int turnIndex) {
    return scoreFrames.get(frameIndex).getFrameTurns().get(turnIndex).score();
  }

  public static int getFrameScore(ScoreFrame scoreFrame, int turnIndex) {
    return scoreFrame.getFrameTurns().get(turnIndex).score();
  }

  public static int getFrameScore(ScoreFrame scoreFrames) {
    int score = 0;
    for (FrameTurn frameTurn : scoreFrames.getFrameTurns()) {
      score += frameTurn.score();
    }
    return score;
  }

  public static int getStrikeScore() {
    return FrameTurn.TEN.score();
  }

  public static int getSpareScore() {
    return FrameTurn.TEN.score();
  }
}
