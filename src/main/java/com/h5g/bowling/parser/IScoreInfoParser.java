package com.h5g.bowling.parser;

import com.h5g.bowling.common.model.GameScore;

/**
 * Parse score info string to game core model.
 */
public interface IScoreInfoParser {

  GameScore parseScoreInfo(String scoreInfo);
}
