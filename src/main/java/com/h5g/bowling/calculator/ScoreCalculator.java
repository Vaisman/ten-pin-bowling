package com.h5g.bowling.calculator;

import com.h5g.bowling.calculator.validation.FrameHelper;
import com.h5g.bowling.calculator.validation.GameScoreValidator;
import com.h5g.bowling.common.BowlingGameConstants;
import com.h5g.bowling.common.model.GameScore;
import com.h5g.bowling.common.model.ScoreFrame;
import com.h5g.bowling.exceptions.BowlingException;
import com.h5g.bowling.exceptions.ScoreCalculatorException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * Calculate score from score model.
 */
public class ScoreCalculator implements IScoreCalculator {

  private static final Logger LOGGER = LogManager.getLogger(ScoreCalculator.class);
  private static final int NORMAL_FRAME_BONUS_VALUE = 0;

  /**
   * Calculate the score from game score model.
   *
   * @param gameScore the score model.
   * @return the game score.
   * @throws ScoreCalculatorException if a score info is invalid.
   * @throws BowlingException in more general cases.
   * @see GameScore
   */
  public int calculateGameScore(GameScore gameScore) {
    GameScoreValidator.validateGameScore(gameScore);

    int totalScore = 0;
    List<ScoreFrame> scoreFrames = gameScore.getScoreFrames();

    for (int frameIndex = 0; frameIndex < BowlingGameConstants.GAME_FRAMES_COUNT; frameIndex++) {
      int baseScore = getFrameBaseScore(scoreFrames.get(frameIndex));
      int frameScore = baseScore + getBonusFrameScore(frameIndex, scoreFrames);
      LOGGER.debug(String
          .format("Frame: %d base core: %d frame score: %d", frameIndex, baseScore, frameScore));
      totalScore += frameScore;
    }

    return totalScore;
  }

  private int getFrameBaseScore(ScoreFrame scoreFrame) {
    if (scoreFrame.isStrikeOrSpare()) {
      return FrameHelper.getStrikeScore();
    } else {
      return FrameHelper.getFrameScore(scoreFrame);
    }
  }

  private int getBonusFrameScore(int currentFrameIndex, List<ScoreFrame> scoreFrames) {
    ScoreFrame currentFrame = scoreFrames.get(currentFrameIndex);

    if (currentFrame.isSpare()) {
      return FrameHelper.getFrameScore(scoreFrames, currentFrameIndex + 1, 0);
    }

    if (currentFrame.isStrike()) {
      ScoreFrame secondFrame = scoreFrames.get(currentFrameIndex + 1);

      if (FrameHelper.isLastFrame(currentFrameIndex)) {
        return getLastFrameBonus(secondFrame);
      }

      ScoreFrame thirdFrame = scoreFrames.get(currentFrameIndex + 2);
      if (FrameHelper.isBeforeLastFrame(currentFrameIndex)) {
        return getBeforeLastFrameBonus(secondFrame, thirdFrame);
      } else {
        return getFrameBonus(secondFrame, thirdFrame);
      }
    }
    return NORMAL_FRAME_BONUS_VALUE;
  }

  private int getFrameBonus(ScoreFrame secondFrame, ScoreFrame thirdFrame) {
    if (secondFrame.isStrike()) {
      return FrameHelper.getStrikeScore() + FrameHelper.getFrameScore(thirdFrame, 0);
    } else if (secondFrame.isSpare()) {
      return FrameHelper.getSpareScore();
    } else {
      return FrameHelper.getFrameScore(secondFrame);
    }
  }

  private int getBeforeLastFrameBonus(ScoreFrame secondFrame, ScoreFrame thirdFrame) {
    if (secondFrame.isStrike()) {
      return FrameHelper.getStrikeScore() + FrameHelper.getFrameScore(thirdFrame, 0);
    } else {
      return FrameHelper.getFrameScore(secondFrame);
    }
  }

  private int getLastFrameBonus(ScoreFrame scoreFrame) {
    if (scoreFrame.isStrike()) {
      return FrameHelper.getStrikeScore() + FrameHelper.getFrameScore(scoreFrame, 1);
    } else {
      return FrameHelper.getFrameScore(scoreFrame);
    }
  }
}
