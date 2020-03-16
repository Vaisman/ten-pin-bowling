package com.h5g.bowling.calculator;

import com.h5g.bowling.common.model.GameScore;

/**
 * Calculate score from score model.
 */
public interface IScoreCalculator {

  int calculateGameScore(GameScore gameScore);
}
