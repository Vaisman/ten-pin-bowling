package com.h5g.bowling.common.model;

import com.h5g.bowling.exceptions.BowlingException;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ScoreFrameTest {

  private static final ScoreFrame scoreFrame = new ScoreFrame();

  @Test
  void setNullTurn() {
    assertThrows(BowlingException.class, () -> scoreFrame.setFrameTurns(null));
  }

  @Test
  void setEmptyTurn() {
    List<FrameTurn> frameTurns = Collections.emptyList();
    assertThrows(BowlingException.class, () -> scoreFrame.setFrameTurns(frameTurns));
  }

  @Test
  void setTurnsInvalidBigSize() {
    List<FrameTurn> frameTurns = new ArrayList<>();
    frameTurns.add(FrameTurn.FIVE);
    frameTurns.add(FrameTurn.FIVE);
    frameTurns.add(FrameTurn.FIVE);
    assertThrows(BowlingException.class, () -> scoreFrame.setFrameTurns(frameTurns));
  }

  @Test
  void setTurns() {
    List<FrameTurn> frameTurns = new ArrayList<>();
    frameTurns.add(FrameTurn.FIVE);
    frameTurns.add(FrameTurn.FIVE);
    assertDoesNotThrow(() -> scoreFrame.setFrameTurns(frameTurns));
  }
}