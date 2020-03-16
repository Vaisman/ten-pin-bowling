package com.h5g.bowling.exceptions;

/**
 * General bowling game exceptions.
 */
public class BowlingException extends RuntimeException {

  public BowlingException() {
    super();
  }

  public BowlingException(String errorMessage) {
    super(errorMessage);
  }
}
