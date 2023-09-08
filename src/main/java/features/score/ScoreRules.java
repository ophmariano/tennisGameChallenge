package features.score;

import features.gameset.GameSet;
import features.printdisplay.MessageDisplay;
import java.util.HashMap;
import java.util.Map;

public class ScoreRules {

  private static final Map<Integer, String> SCORE_DISPLAY_NAME = new HashMap<>();

  static {
    SCORE_DISPLAY_NAME.put(0, "Love");
    SCORE_DISPLAY_NAME.put(1, "15");
    SCORE_DISPLAY_NAME.put(2, "30");
    SCORE_DISPLAY_NAME.put(3, "40");
  }

  public static void displayGameScore(GameSet gameSet) {
    if (gameSet.getWinnerPlayer() != null) {
      return;
    }

    if (gameSet.isDeuceMode()) {
      displayDeuceModeScore(gameSet);
    } else {
      displayNormalModeScore(gameSet);
    }
  }

  private static void displayNormalModeScore(GameSet gameSet) {
    String scorePointsSection;
    // TODO: Paulo - feature-01: check with PM for this part since it's not on requirements but is custom in games
    //    if (gameSet.isScoreTie()) {
    //      scorePointsSection =
    //        String.format(
    //          "%s ALL",
    //          SCORE_DISPLAY_NAME.get(gameSet.getPlayerOneScore())
    //        );
    //    } else {
    //      scorePointsSection =
    //        String.format(
    //          "%s - %s",
    //          SCORE_DISPLAY_NAME.get(gameSet.getPlayerOneScore()),
    //          SCORE_DISPLAY_NAME.get(gameSet.getPlayerTwoScore())
    //        );
    //    }

    scorePointsSection =
      String.format(
        "%s - %s",
        SCORE_DISPLAY_NAME.get(gameSet.getPlayerOneScore()),
        SCORE_DISPLAY_NAME.get(gameSet.getPlayerTwoScore())
      );

    MessageDisplay.gameScore(
      gameSet.getPlayerOne().name(),
      scorePointsSection,
      gameSet.getPlayerTwo().name()
    );
  }

  private static void displayDeuceModeScore(GameSet gameSet) {
    String scorePointsSection;
    if (gameSet.isDeuce()) {
      scorePointsSection = "Deuce";
    } else {
      scorePointsSection =
        gameSet.getPlayerOne().name().equals(gameSet.getLastScorePlayer())
          ? "Adv - 40"
          : "40 - Adv";
    }

    MessageDisplay.gameScore(
      gameSet.getPlayerOne().name(),
      scorePointsSection,
      gameSet.getPlayerTwo().name()
    );
  }
}
