package com.h5g.bowling.calculator.model;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Score calculation model.
 */
public class ScoreCalculation {

  private int score;
  private CalculationResult status;
  private String message;

  public ScoreCalculation(int score, CalculationResult status, String message) {
    this.score = score;
    this.status = status;
    this.message = message;
  }

  public ScoreCalculation(int score, CalculationResult status) {
    this.score = score;
    this.status = status;
  }

  public int getScore() {
    return score;
  }

  public void setScore(int score) {
    this.score = score;
  }

  public CalculationResult getStatus() {
    return status;
  }

  public void setStatus(CalculationResult status) {
    this.status = status;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("score", score)
        .append("status", status)
        .append("message", message)
        .toString();
  }
}
