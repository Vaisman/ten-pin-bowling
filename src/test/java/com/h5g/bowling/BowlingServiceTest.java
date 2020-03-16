package com.h5g.bowling;

import com.h5g.bowling.service.BowlingService;
import com.h5g.bowling.calculator.model.ScoreCalculation;
import com.h5g.bowling.calculator.model.CalculationResult;
import com.h5g.bowling.service.IBowlingService;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BowlingServiceTest {

  private final IBowlingService bowlingService = new BowlingService();
  private final static int ERROR_SCORE_VALUE = -1;

  @Test
  void calculateInvalidInput() {
    checkGameScore(ERROR_SCORE_VALUE, CalculationResult.FAIL,
        bowlingService.calculateGameScore("1111"));
  }

  @Test
  void calculateZero() {
    checkGameScore(0, CalculationResult.SUCCESS,
        bowlingService.calculateGameScore("--|--|--|--|--|--|--|--|--|--||"));
  }

  @Test
  void calculateOne() {
    checkGameScore(1, CalculationResult.SUCCESS,
        bowlingService.calculateGameScore("1-|--|--|--|--|--|--|--|--|--||"));
  }

  @Test
  void calculateTwo() {
    checkGameScore(2, CalculationResult.SUCCESS,
        bowlingService.calculateGameScore("--|-2|--|--|--|--|--|--|--|--||"));
  }

  @Test
  void calculateTwenty() {
    checkGameScore(20, CalculationResult.SUCCESS,
        bowlingService.calculateGameScore("11|11|11|11|11|11|11|11|11|11||"));
  }

  @Test
  void calculateTen() {
    checkGameScore(10, CalculationResult.SUCCESS,
        bowlingService.calculateGameScore("X|--|--|--|--|--|--|--|--|--||"));
  }

  @Test
  void calculateSpare() {
    checkGameScore(10, CalculationResult.SUCCESS,
        bowlingService.calculateGameScore("1/|--|--|--|--|--|--|--|--|--||"));
  }

  @Test
  void calculateSpareAndBonus() {
    checkGameScore(14, CalculationResult.SUCCESS,
        bowlingService.calculateGameScore("1/|2-|--|--|--|--|--|--|--|--||"));
  }

  @Test
  void calculateTwoFrames() {
    checkGameScore(12, CalculationResult.SUCCESS,
        bowlingService.calculateGameScore("12|36|--|--|--|--|--|--|--|--||"));
  }

  @Test
  void calculateStrikeAndBonus() {
    checkGameScore(20, CalculationResult.SUCCESS,
        bowlingService.calculateGameScore("X|23|--|--|--|--|--|--|--|--||"));
  }

  @Test
  void calculateMax() {
    checkGameScore(300, CalculationResult.SUCCESS,
        bowlingService.calculateGameScore("X|X|X|X|X|X|X|X|X|X||XX"));
  }

  @Test
  void calculateRandom1() {
    checkGameScore(90, CalculationResult.SUCCESS,
        bowlingService.calculateGameScore("9-|9-|9-|9-|9-|9-|9-|9-|9-|9-|"));
  }

  @Test
  void calculateRandom2() {
    checkGameScore(150, CalculationResult.SUCCESS,
        bowlingService.calculateGameScore("5/|5/|5/|5/|5/|5/|5/|5/|5/|5/||5"));
  }

  @Test
  void calculateRandom3() {
    checkGameScore(167, CalculationResult.SUCCESS,
        bowlingService.calculateGameScore("X|7/|9-|X|-8|8/|-6|X|X|X||81"));
  }

  @Test
  void calculateRandom4() {
    checkGameScore(133, CalculationResult.SUCCESS,
        bowlingService.calculateGameScore("63|52|8/|-6|X|X|3/|7/|8/|53||"));
  }

  @Test
  void calculateRandom5() {
    checkGameScore(171, CalculationResult.SUCCESS,
        bowlingService.calculateGameScore("X|X|9/|8-|X|X|9-|8/|7/|44||"));
  }

  @Test
  void calculateRandom6() {
    checkGameScore(93, CalculationResult.SUCCESS,
        bowlingService.calculateGameScore("X|23|71|9/|6-|13|2/|71|-6|17||"));
  }

  @Test
  void calculateRandom7() {
    checkGameScore(190, CalculationResult.SUCCESS,
        bowlingService.calculateGameScore("24|X|-9|X|8/|7/|X|X|9/|X||XX"));
  }

  @Test
  void calculateRandom8() {
    checkGameScore(156, CalculationResult.SUCCESS,
        bowlingService.calculateGameScore("9/|X|9-|X|3/|9/|9/|-/|-/|-/||X"));
  }

  @Test
  void calculateRandom9() {
    checkGameScore(110, CalculationResult.SUCCESS,
        bowlingService.calculateGameScore("-/|-/|-/|-/|-/|-/|-/|-/|-/|-/||X"));
  }

  private void checkGameScore(int score, CalculationResult status, ScoreCalculation result) {
    assertEquals(score, result.getScore());
    assertEquals(status, result.getStatus());
  }
}
