package com.h5g.bowling.parser;

import com.h5g.bowling.common.model.FrameTurn;
import com.h5g.bowling.common.model.FrameType;

import java.util.List;

class ParserHelper {

  private ParserHelper() {
  }

  static FrameType getFrameType(List<FrameTurn> frameTurns) {
    for (FrameTurn frameTurn : frameTurns) {
      if (frameTurn == FrameTurn.TEN) {
        return FrameType.STRIKE;
      } else if (frameTurn == FrameTurn.SPARE) {
        return FrameType.SPARE;
      }
    }
    return FrameType.NORMAL;
  }
}
