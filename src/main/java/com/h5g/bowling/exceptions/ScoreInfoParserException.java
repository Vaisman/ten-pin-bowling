package com.h5g.bowling.exceptions;

/**
 * Parse score info exceptions.
 */
public class ScoreInfoParserException extends BowlingException {

  public ScoreInfoParserException() {
    super();
  }

  public ScoreInfoParserException(String errorMessage) {
    super(errorMessage);
  }
}
