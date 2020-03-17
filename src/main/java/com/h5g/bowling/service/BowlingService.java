package com.h5g.bowling.service;

import com.h5g.bowling.calculator.IScoreCalculator;
import com.h5g.bowling.calculator.model.CalculationResult;
import com.h5g.bowling.common.model.GameScore;
import com.h5g.bowling.calculator.model.ScoreCalculation;
import com.h5g.bowling.exceptions.BowlingException;
import com.h5g.bowling.parser.IScoreInfoParser;
import com.h5g.bowling.parser.ScoreInfoParser;
import com.h5g.bowling.calculator.ScoreCalculator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * A service that calculate game score.
 */
public class BowlingService implements IBowlingService {

  private static final Logger LOGGER = LogManager.getLogger(BowlingService.class);
  private static final int ERROR_SCORE_VALUE = -1;

  private static final IScoreInfoParser scoreInfoParser = new ScoreInfoParser();
  private static final IScoreCalculator scoreCalculator = new ScoreCalculator();

  /**
   * Calculate game score.
   *
   * @param gameScoreInfo the score string
   * @return the game score calculation model.
   * @see ScoreCalculation
   */
  public ScoreCalculation calculateGameScore(String gameScoreInfo) {
    LOGGER.info(String.format("Start calculation score for game: %s", gameScoreInfo));

    try {
      GameScore gameScore = scoreInfoParser.parseScoreInfo(gameScoreInfo);
      int score = scoreCalculator.calculateGameScore(gameScore);
      LOGGER.info(String
          .format("Score calculation finished for game: %s with score %s", gameScoreInfo, score));
      return new ScoreCalculation(score, CalculationResult.SUCCESS);
    } catch (BowlingException e) {
      LOGGER.warn(String.format("Score calculation error: %s", e.getMessage()));
      return new ScoreCalculation(ERROR_SCORE_VALUE, CalculationResult.FAIL, e.getMessage());
    } catch (Exception e) {
      LOGGER.warn(String.format("Score calculation general error: %s", e.getMessage()));
      return new ScoreCalculation(ERROR_SCORE_VALUE, CalculationResult.FAIL, "General error.");
    }
  }
}