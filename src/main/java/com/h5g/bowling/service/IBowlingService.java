package com.h5g.bowling.service;

import com.h5g.bowling.calculator.model.ScoreCalculation;

/**
 * A service that calculate game score.
 */
public interface IBowlingService {

  ScoreCalculation calculateGameScore(String gameScoreInfo);
}
