package com.h5g.bowling.common.model;

import com.h5g.bowling.common.validation.TurnsHelper;

import java.util.List;

/**
 * The game score frame model.
 */
public class ScoreFrame {

  private List<FrameTurn> frameTurns;
  private FrameType frameType;

  public void setFrameType(FrameType frameType) {
    this.frameType = frameType;
  }

  public FrameType getFrameType() {
    return frameType;
  }

  public void setFrameTurns(List<FrameTurn> frameTurns) {
    TurnsHelper.validateFrameTurns(frameTurns);
    this.frameTurns = frameTurns;
  }

  public List<FrameTurn> getFrameTurns() {
    return frameTurns;
  }

  public boolean isStrikeOrSpare() {
    return frameType == FrameType.STRIKE || frameType == FrameType.SPARE;
  }

  public boolean isStrike() {
    return frameType == FrameType.STRIKE;
  }

  public boolean isSpare() {
    return frameType == FrameType.SPARE;
  }
}
