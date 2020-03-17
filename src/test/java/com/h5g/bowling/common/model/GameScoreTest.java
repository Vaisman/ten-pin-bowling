package com.h5g.bowling.common.model;

import com.h5g.bowling.common.BowlingGameConstants;
import com.h5g.bowling.exceptions.BowlingException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GameScoreTest {

  private static final GameScore gameScore = new GameScore();

  @Test
  void setNullGameFrames() {
    assertThrows(BowlingException.class, () -> gameScore.setScoreFrames(null));
  }

  @Test
  void setEmptyGameFrames() {
    List<ScoreFrame> scoreFrames = new ArrayList<>();
    assertThrows(BowlingException.class, () -> gameScore.setScoreFrames(scoreFrames));
  }

  @Test
  void setFramesInvalidSize() {
    List<ScoreFrame> scoreFrames = new ArrayList<>();
    scoreFrames.add(new ScoreFrame());
    assertThrows(BowlingException.class, () -> gameScore.setScoreFrames(scoreFrames));
  }

  @ParameterizedTest
  @ValueSource(ints = {BowlingGameConstants.GAME_FRAMES_COUNT, BowlingGameConstants.GAME_FULL_FRAMES_COUNT})
  void setFrames(int size) {
    List<ScoreFrame> scoreFrames = new ArrayList<>();
    for (int i = 0; i < size; i++) {
      scoreFrames.add(new ScoreFrame());
    }
    assertDoesNotThrow(() -> gameScore.setScoreFrames(scoreFrames));
  }
}
