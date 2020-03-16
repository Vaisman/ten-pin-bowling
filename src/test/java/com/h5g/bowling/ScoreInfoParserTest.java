package com.h5g.bowling;

import com.h5g.bowling.common.BowlingGameConstants;
import com.h5g.bowling.common.model.GameScore;
import com.h5g.bowling.common.model.FrameType;
import com.h5g.bowling.common.model.ScoreFrame;
import com.h5g.bowling.exceptions.BowlingException;
import com.h5g.bowling.exceptions.ScoreInfoParserException;
import com.h5g.bowling.parser.IScoreInfoParser;
import com.h5g.bowling.parser.ScoreInfoParser;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ScoreInfoParserTest {

  private final IScoreInfoParser scoreInfoParser = new ScoreInfoParser();

  @Test
  void parseGameScoreNullInput() {
    assertThrows(ScoreInfoParserException.class, () -> scoreInfoParser.parseScoreInfo(null));
  }

  @Test
  void parseGameScoreEmptyInput() {
    assertThrows(ScoreInfoParserException.class, () -> scoreInfoParser.parseScoreInfo(""));
  }

  @Test
  void parseGameScoreShortInput() {
    assertThrows(ScoreInfoParserException.class, () -> scoreInfoParser.parseScoreInfo("111"));
  }

  @Test
  void parseGameScoreLongInput() {
    assertThrows(ScoreInfoParserException.class,
        () -> scoreInfoParser.parseScoreInfo(RandomStringUtils.randomAlphabetic(10000)));
  }

  @Test
  void parseGameScoreFirstSpare() {
    assertThrows(ScoreInfoParserException.class,
        () -> scoreInfoParser.parseScoreInfo("--|--|--|--|--|--|--|--|--|/-||"));
  }

  @Test
  void parseGameScoreDoubleStrike() {
    assertThrows(ScoreInfoParserException.class,
        () -> scoreInfoParser.parseScoreInfo("XX|--|--|--|--|--|--|--|--|/-||"));
  }

  @Test
  void parseGameScoreSumOverflow1() {
    assertThrows(ScoreInfoParserException.class,
        () -> scoreInfoParser.parseScoreInfo("X1|--|--|--|--|--|--|--|--|/-||"));
  }

  @Test
  void parseGameScoreSumOverflow2() {
    assertThrows(ScoreInfoParserException.class,
        () -> scoreInfoParser.parseScoreInfo("81|--|--|--|--|--|--|--|--|/-||"));
  }

  @Test
  void parseGameScoreSumUnderflow() {
    assertThrows(ScoreInfoParserException.class,
        () -> scoreInfoParser.parseScoreInfo("1|--|--|--|--|--|--|--|--|/-||"));
  }

  @Test
  void parseGameScoreInvalidData() {
    assertThrows(ScoreInfoParserException.class,
        () -> scoreInfoParser.parseScoreInfo("19|AB|--|--|--|--|--|--|--|/-||"));
  }

  @Test
  void parseGameScoreFrameNotFull() {
    assertThrows(ScoreInfoParserException.class,
        () -> scoreInfoParser.parseScoreInfo("-|--|--|--|--|--|--|--|--|/-||"));
  }

  @Test
  void parseGameScoreEmptyFrame() {
    assertThrows(ScoreInfoParserException.class,
        () -> scoreInfoParser.parseScoreInfo("--||--|--|--|--|--|--|--|/-||"));
  }

  @Test
  void parseGameScoreInvalidBonusSum() {
    assertThrows(BowlingException.class,
        () -> scoreInfoParser.parseScoreInfo("-1|19|28|37|46|55|9/|--|X|X||XXX"));
  }

  @Test
  void parseGameScore() {
    GameScore gameScore = scoreInfoParser.parseScoreInfo("-1|19|28|37|46|55|9/|--|X|X||XX");
    List<ScoreFrame> scoreFrames = gameScore.getScoreFrames();

    assertEquals(BowlingGameConstants.GAME_FULL_FRAMES_COUNT, scoreFrames.size());
    assertEquals(FrameType.NORMAL, scoreFrames.get(0).getFrameType());
    assertEquals(FrameType.NORMAL, scoreFrames.get(1).getFrameType());
    assertEquals(FrameType.NORMAL, scoreFrames.get(2).getFrameType());
    assertEquals(FrameType.NORMAL, scoreFrames.get(3).getFrameType());
    assertEquals(FrameType.NORMAL, scoreFrames.get(4).getFrameType());
    assertEquals(FrameType.NORMAL, scoreFrames.get(5).getFrameType());
    assertEquals(FrameType.SPARE, scoreFrames.get(6).getFrameType());
    assertEquals(FrameType.NORMAL, scoreFrames.get(7).getFrameType());
    assertEquals(FrameType.STRIKE, scoreFrames.get(8).getFrameType());
    assertEquals(FrameType.STRIKE, scoreFrames.get(9).getFrameType());

    assertEquals(2, scoreFrames.get(0).getFrameTurns().size());
    assertEquals(2, scoreFrames.get(1).getFrameTurns().size());
    assertEquals(2, scoreFrames.get(2).getFrameTurns().size());
    assertEquals(2, scoreFrames.get(3).getFrameTurns().size());
    assertEquals(2, scoreFrames.get(4).getFrameTurns().size());
    assertEquals(2, scoreFrames.get(5).getFrameTurns().size());
    assertEquals(2, scoreFrames.get(6).getFrameTurns().size());
    assertEquals(2, scoreFrames.get(7).getFrameTurns().size());
    assertEquals(1, scoreFrames.get(8).getFrameTurns().size());
    assertEquals(1, scoreFrames.get(9).getFrameTurns().size());

    assertEquals(0, scoreFrames.get(0).getFrameTurns().get(0).score());
    assertEquals(1, scoreFrames.get(0).getFrameTurns().get(1).score());
    assertEquals("-", scoreFrames.get(0).getFrameTurns().get(0).value());
    assertEquals("1", scoreFrames.get(0).getFrameTurns().get(1).value());

    assertEquals(1, scoreFrames.get(1).getFrameTurns().get(0).score());
    assertEquals(9, scoreFrames.get(1).getFrameTurns().get(1).score());
    assertEquals("1", scoreFrames.get(1).getFrameTurns().get(0).value());
    assertEquals("9", scoreFrames.get(1).getFrameTurns().get(1).value());

    assertEquals(2, scoreFrames.get(2).getFrameTurns().get(0).score());
    assertEquals(8, scoreFrames.get(2).getFrameTurns().get(1).score());
    assertEquals("2", scoreFrames.get(2).getFrameTurns().get(0).value());
    assertEquals("8", scoreFrames.get(2).getFrameTurns().get(1).value());

    assertEquals(3, scoreFrames.get(3).getFrameTurns().get(0).score());
    assertEquals(7, scoreFrames.get(3).getFrameTurns().get(1).score());
    assertEquals("3", scoreFrames.get(3).getFrameTurns().get(0).value());
    assertEquals("7", scoreFrames.get(3).getFrameTurns().get(1).value());

    assertEquals(4, scoreFrames.get(4).getFrameTurns().get(0).score());
    assertEquals(6, scoreFrames.get(4).getFrameTurns().get(1).score());
    assertEquals("4", scoreFrames.get(4).getFrameTurns().get(0).value());
    assertEquals("6", scoreFrames.get(4).getFrameTurns().get(1).value());

    assertEquals(5, scoreFrames.get(5).getFrameTurns().get(0).score());
    assertEquals("5", scoreFrames.get(5).getFrameTurns().get(0).value());
    assertEquals(5, scoreFrames.get(5).getFrameTurns().get(1).score());
    assertEquals("5", scoreFrames.get(5).getFrameTurns().get(1).value());

    assertEquals(9, scoreFrames.get(6).getFrameTurns().get(0).score());
    assertEquals(0, scoreFrames.get(6).getFrameTurns().get(1).score());
    assertEquals("9", scoreFrames.get(6).getFrameTurns().get(0).value());
    assertEquals("/", scoreFrames.get(6).getFrameTurns().get(1).value());

    assertEquals(0, scoreFrames.get(7).getFrameTurns().get(0).score());
    assertEquals(0, scoreFrames.get(7).getFrameTurns().get(1).score());
    assertEquals("-", scoreFrames.get(7).getFrameTurns().get(0).value());
    assertEquals("-", scoreFrames.get(7).getFrameTurns().get(1).value());

    assertEquals(10, scoreFrames.get(8).getFrameTurns().get(0).score());
    assertEquals(10, scoreFrames.get(9).getFrameTurns().get(0).score());
    assertEquals(10, scoreFrames.get(10).getFrameTurns().get(0).score());
    assertEquals("X", scoreFrames.get(8).getFrameTurns().get(0).value());
    assertEquals("X", scoreFrames.get(9).getFrameTurns().get(0).value());
    assertEquals("X", scoreFrames.get(10).getFrameTurns().get(0).value());
  }
}
