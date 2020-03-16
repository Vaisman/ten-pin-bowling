package com.h5g.bowling;

import com.h5g.bowling.service.BowlingService;
import com.h5g.bowling.service.IBowlingService;
import com.h5g.bowling.params.OutputFormat;
import com.h5g.bowling.params.ParamsHelper;
import com.h5g.bowling.params.ParamsInfo;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * A main class to calculate game score.
 */
public class BowlingApplication {

  private static final Logger LOGGER = LogManager.getLogger(BowlingApplication.class);
  private final IBowlingService bowlingService = new BowlingService();

  /**
   * Main class to calculate game score.
   *
   * @param args the game score string and output format (string or json)
   */
  public static void main(String[] args) {
    LOGGER.info("Start ten-pin-bowling application.");

    ParamsHelper paramsHelper = new ParamsHelper();
    ParamsInfo paramsInfo = paramsHelper.getParams(args);

    if (!StringUtils.isEmpty(paramsInfo.getGameScore())) {
      BowlingApplication bowlingApplication = new BowlingApplication();
      String calculationResult = bowlingApplication.calculate(paramsInfo.getGameScore(),
          paramsInfo.getOutputFormat());
      LOGGER.info(String.format("Result: %s", calculationResult));
    }

    LOGGER.info("Application ten-pin-bowling finished.");
  }

  private String calculate(String gameScore, OutputFormat outputFormat) {
    if (outputFormat == OutputFormat.STRING) {
      return String.valueOf(bowlingService.calculateGameScore(gameScore).getScore());
    }
    if (outputFormat == OutputFormat.JSON) {
      return bowlingService.calculateGameScore(gameScore).toString();
    }

    LOGGER.warn(String.format("Unknown output format %s", outputFormat));
    return "";
  }
}
