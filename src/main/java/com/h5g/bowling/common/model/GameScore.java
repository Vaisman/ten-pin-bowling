package com.h5g.bowling.common.model;

import com.h5g.bowling.common.validation.FramesHelper;

import java.util.List;

/**
 * The game score model.
 */
public class GameScore {

  private List<ScoreFrame> scoreFrames;

  public void setScoreFrames(List<ScoreFrame> scoreFrames) {
    FramesHelper.validateScoreFrames(scoreFrames);
    this.scoreFrames = scoreFrames;
  }

  public List<ScoreFrame> getScoreFrames() {
    return scoreFrames;
  }
}
