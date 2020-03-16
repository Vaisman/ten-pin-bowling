package com.h5g.bowling.params;

/**
 * Application input params model.
 */
public class ParamsInfo {

  private String gameScore;
  private OutputFormat outputFormat;

  public String getGameScore() {
    return gameScore;
  }

  public void setGameScore(String gameScore) {
    this.gameScore = gameScore;
  }

  public OutputFormat getOutputFormat() {
    return outputFormat;
  }

  public void setOutputFormat(OutputFormat outputFormat) {
    this.outputFormat = outputFormat;
  }
}
