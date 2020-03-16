package com.h5g.bowling.parser;

import com.h5g.bowling.common.BowlingGameConstants;
import com.h5g.bowling.common.model.FrameTurn;
import com.h5g.bowling.common.model.GameScore;
import com.h5g.bowling.common.model.ScoreFrame;
import com.h5g.bowling.exceptions.BowlingException;
import com.h5g.bowling.exceptions.ScoreInfoParserException;

import com.h5g.bowling.parser.validation.FrameValidator;
import com.h5g.bowling.parser.validation.ScoreInfoValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Parse score info string to game core model.
 */
public class ScoreInfoParser implements IScoreInfoParser {

  private static final Logger LOGGER = LogManager.getLogger(ScoreInfoParser.class);
  private static final String FRAME_BORDER = "\\|";

  /**
   * Parse score info string.
   *
   * @param scoreInfo the score string.
   * @return the game score model.
   * @throws ScoreInfoParserException if a score info is invalid.
   * @throws BowlingException in more general cases.
   * @see GameScore
   */
  public GameScore parseScoreInfo(String scoreInfo) {
    ScoreInfoValidator.validateScoreInfo(scoreInfo);

    GameScore game = new GameScore();
    List<ScoreFrame> scoreFrames = new ArrayList<>();
    String[] scoresContainer = scoreInfo.split(FRAME_BORDER);

    ScoreInfoValidator.validateScores(scoresContainer);

    int frameIndex = 0;
    for (String scoreFrame : scoresContainer) {
      if (!scoreFrame.isEmpty()) {
        scoreFrames.add(buildScoreFrame(scoreFrame, frameIndex++));
      }
    }
    game.setScoreFrames(scoreFrames);
    return game;
  }

  private ScoreFrame buildScoreFrame(String scoreFrame, int frameIndex) {
    LOGGER.debug(String.format("Build frame from: %s", scoreFrame));

    ScoreFrame frame = new ScoreFrame();
    List<FrameTurn> frameTurns = new ArrayList<>();

    for (char frameChunk : scoreFrame.toCharArray()) {
      FrameTurn frameTurn = FrameTurn.fromString(String.valueOf(frameChunk));
      LOGGER.debug(String.format("Add frame turn %s to frame %s", frameTurn, scoreFrame));
      frameTurns.add(frameTurn);
    }

    FrameValidator.validateFirstSpare(frameTurns);
    if (frameIndex < BowlingGameConstants.GAME_FRAMES_COUNT) {
      FrameValidator.validateDoubleStrike(frameTurns);
      FrameValidator.validateSum(frameTurns);
    }

    frame.setFrameTurns(frameTurns);
    frame.setFrameType(ParserHelper.getFrameType(frameTurns));

    LOGGER.debug(String.format("Finish build frame from: %s", scoreFrame));
    return frame;
  }
}