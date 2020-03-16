package com.h5g.bowling.common.model;

import com.h5g.bowling.exceptions.ScoreInfoParserException;

import java.util.Arrays;

/**
 * Frame turn model.
 */
public enum FrameTurn {
  TEN("X", 10),
  NINE("9", 9),
  EIGHT("8", 8),
  SEVEN("7", 7),
  SIX("6", 6),
  FIVE("5", 5),
  FOUR("4", 4),
  THREE("3", 3),
  TWO("2", 2),
  ONE("1", 1),
  MISS("-", 0),
  SPARE("/", 0);

  private String value;
  private int score;

  FrameTurn(String value, int score) {
    this.value = value;
    this.score = score;
  }

  public String value() {
    return value;
  }

  public int score() {
    return score;
  }

  public static FrameTurn fromString(String value) {
    return Arrays.stream(values())
        .filter(currentGameTurn -> currentGameTurn.value.equalsIgnoreCase(value))
        .findFirst().orElseThrow(ScoreInfoParserException::new);
  }
}
