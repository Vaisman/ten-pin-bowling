package com.h5g.bowling.params;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class ParamsHelperTest {

  private static final ParamsHelper paramsHelper = new ParamsHelper();

  @Test
  void nullArgs() {
    ParamsInfo paramsInfo = paramsHelper.getParams(null);
    assertNull(paramsInfo.getGameScore());
  }

  @Test
  void emptyArgs() {
    ParamsInfo paramsInfo = paramsHelper.getParams(new String[]{});
    assertNull(paramsInfo.getGameScore());
  }

  @Test
  void inputNoRequiredArgArgs() {
    String[] args = new String[]{"-o", "json"};
    ParamsInfo paramsInfo = paramsHelper.getParams(args);
    assertNull(paramsInfo.getGameScore());
  }

  @Test
  void inputUnknownArgs() {
    String gameScore = "X|7/|9-|X|-8|8/|-6|X|X|X||89";
    String[] args = new String[]{"-s", gameScore, "-t", "json"};
    ParamsInfo paramsInfo = paramsHelper.getParams(args);
    assertNull(paramsInfo.getGameScore());
  }

  @Test
  void validInputJsonOutputArgs() {
    String gameScore = "X|7/|9-|X|-8|8/|-6|X|X|X||89";
    String[] args = new String[]{"-s", gameScore, "-o", "json"};
    ParamsInfo paramsInfo = paramsHelper.getParams(args);
    assertEquals(gameScore, paramsInfo.getGameScore());
    assertEquals(OutputFormat.JSON, paramsInfo.getOutputFormat());
  }

  @Test
  void validInputDefaultOutputArgs() {
    String gameScore = "X|7/|9-|X|-8|8/|-6|X|X|X||89";
    String[] args = new String[]{"-s", gameScore};
    ParamsInfo paramsInfo = paramsHelper.getParams(args);
    assertEquals(gameScore, paramsInfo.getGameScore());
    assertEquals(OutputFormat.STRING, paramsInfo.getOutputFormat());
  }
}
