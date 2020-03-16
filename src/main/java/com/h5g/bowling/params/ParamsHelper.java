package com.h5g.bowling.params;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Application params parser.
 */
public class ParamsHelper {

  private static final Logger LOGGER = LogManager.getLogger(ParamsHelper.class);

  /**
   * Main class to calculate game score.
   *
   * @param args the game score string and output format (string or json)
   * @return the game score string and output format model.
   * @see ParamsInfo
   */
  public ParamsInfo getParams(String[] args) {
    Options options = new Options();

    Option input = new Option("s", "game-score-info", true,
        "Bowling score info. Example: \"X|7/|9-|X|-8|8/|-6|X|X|X||81\"");
    input.setRequired(true);
    options.addOption(input);

    Option output = new Option("o", "output", true,
        "Output format: (String or JSON). The string by default.");
    output.setRequired(false);
    options.addOption(output);

    CommandLineParser parser = new DefaultParser();
    HelpFormatter formatter = new HelpFormatter();
    CommandLine cmd;

    ParamsInfo paramsInfo = new ParamsInfo();

    try {
      cmd = parser.parse(options, args);

      String gameScoreInfo = cmd.getOptionValue("game-score-info");
      paramsInfo.setGameScore(gameScoreInfo);

      String outputFormat = cmd.getOptionValue("output");
      if (outputFormat == null || outputFormat.isEmpty()) {
        paramsInfo.setOutputFormat(OutputFormat.STRING);
      } else {
        paramsInfo.setOutputFormat(OutputFormat.fromString(outputFormat));
      }
    } catch (ParseException e) {
      LOGGER.warn(String.format("Bowling application error: %s", e.getMessage()));
      formatter.printHelp("ten-pin-bowling", options);
    }
    return paramsInfo;
  }
}