package features.printdisplay;

import features.gameset.GameSet;

public class MessageDisplay {

  private static void printMessage(String message) {
    System.out.println(message);
  }

  public static void finalGameScore(GameSet gameSet) {
    String gameFinalScoreBoard = String.format(
      "Game total points made was: %s | %s - %s | %s",
      gameSet.getPlayerOne().name(),
      gameSet.getPlayerOneScore(),
      gameSet.getPlayerTwoScore(),
      gameSet.getPlayerTwo().name()
    );
    printMessage(gameFinalScoreBoard);
  }

  public static void winner(String winnerName) {
    String gameWinner = String.format(
      "Congratulations on winning %s",
      winnerName
    );
    printMessage(gameWinner);
  }

  public static void gameStart() {
    printMessage("The game starts!!!!");
  }

  public static void gameScore(
    String playerOneName,
    String scoreSection,
    String playerTwoName
  ) {
    String gameFinalScoreBoard = String.format(
      "%s | %s | %s",
      playerOneName,
      scoreSection,
      playerTwoName
    );
    printMessage(gameFinalScoreBoard);
  }

  public static void logErrorSimulator(String logMessage) {
    printMessage(logMessage);
  }
}
