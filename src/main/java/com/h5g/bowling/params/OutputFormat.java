package com.h5g.bowling.params;

import com.h5g.bowling.exceptions.ScoreInfoParserException;

import java.util.Arrays;

/**
 * Application output format.
 */
public enum OutputFormat {
  STRING("string"),
  JSON("json");

  private String value;

  OutputFormat(String value) {
    this.value = value;
  }

  public String value() {
    return value;
  }

  public static OutputFormat fromString(String value) {
    return Arrays.stream(values())
        .filter(outputFormat -> outputFormat.value.equalsIgnoreCase(value))
        .findFirst().orElseThrow(ScoreInfoParserException::new);
  }
}
