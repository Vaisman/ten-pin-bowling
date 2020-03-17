package com.h5g.bowling.calculator;

import com.h5g.bowling.common.BowlingGameConstants;
import com.h5g.bowling.common.model.FrameTurn;
import com.h5g.bowling.common.model.FrameType;
import com.h5g.bowling.common.model.GameScore;
import com.h5g.bowling.common.model.ScoreFrame;
import com.h5g.bowling.exceptions.BowlingException;
import com.h5g.bowling.exceptions.ScoreCalculatorException;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ScoreCalculatorTest {

  private static final IScoreCalculator scoreCalculator = new ScoreCalculator();

  @Test
  void calculateNullGameScore() {
    assertThrows(ScoreCalculatorException.class, () -> scoreCalculator.calculateGameScore(null));
  }

  @Test
  void calculateNullGameFrames() {
    assertThrows(ScoreCalculatorException.class,
        () -> scoreCalculator.calculateGameScore(new GameScore()));
  }

  @Test
  void calculateGameFramesInvalidSize() {
    GameScore gameScore = new GameScore();
    List<ScoreFrame> scoreFrames = new ArrayList<>();
    scoreFrames.add(new ScoreFrame());
    assertThrows(BowlingException.class, () -> gameScore.setScoreFrames(scoreFrames));
  }

  @Test
  void calculateScoreGame() {
    GameScore game = new GameScore();
    List<ScoreFrame> scoreFrames = buildScoreFrames();

    game.setScoreFrames(scoreFrames);
    assertEquals(110, scoreCalculator.calculateGameScore(game));
  }

  private List<ScoreFrame> buildScoreFrames() {
    List<ScoreFrame> scoreFrames = new ArrayList<>();
    List<FrameTurn> frameTurns = Arrays.asList(FrameTurn.MISS, FrameTurn.SPARE);

    for (int i = 0; i < BowlingGameConstants.GAME_FRAMES_COUNT; i++) {
      ScoreFrame scoreFrame = new ScoreFrame();
      scoreFrame.setFrameTurns(frameTurns);
      scoreFrame.setFrameType(FrameType.SPARE);

      scoreFrames.add(scoreFrame);
    }

    ScoreFrame scoreFrameStrike = new ScoreFrame();
    List<FrameTurn> strikeFrameTurns = Collections.singletonList(FrameTurn.TEN);
    scoreFrameStrike.setFrameTurns(strikeFrameTurns);
    scoreFrameStrike.setFrameType(FrameType.STRIKE);

    scoreFrames.add(scoreFrameStrike);

    return scoreFrames;
  }
}
