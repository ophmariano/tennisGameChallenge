package features.gameset;

import errorhandling.GameSetInvalidScoringForPlayer;
import features.player.Player;
import features.printdisplay.MessageDisplay;

public class GameSet {

  private final Player playerOne;
  private final Player playerTwo;
  private int playerOneScore;
  private int playerTwoScore;
  private Player winnerPlayer;
  private boolean deuce;
  private boolean deuceMode;
  private String lastScorePlayer;

  public GameSet(Player playerOne, Player playerTwo) {
    this.playerOne = playerOne;
    this.playerTwo = playerTwo;
    this.playerOneScore = 0;
    this.playerTwoScore = 0;
  }

  public int getPlayerScore(Player player) {
    if (playerOne.name().equals(player.name())) {
      return getPlayerOneScore();
    } else {
      return getPlayerTwoScore();
    }
  }

  public void checkForDeuce(Player gameSetWinner) {
    if (getPlayerScore(gameSetWinner) < 3) {
      return;
    }
    if (isScoreTie()) {
      setDeuce(true);
      setDeuceMode(true);
    } else {
      setDeuce(false);
    }
  }

  public boolean isScoreTie() {
    return getPlayerOneScore() == getPlayerTwoScore();
  }

  public Player playPoint() {
    double random = Math.random();
    if (random > 0.5) {
      return playerOne;
    }
    return playerTwo;
  }

  public boolean hasVictoryCondition(Player pointWinner) {
    int gameSetWinnerScore = getPlayerScore(pointWinner);
    if (gameSetWinnerScore < 3) {
      return false;
    }
    int scoreDifference = getScoreDifference();
    return (gameSetWinnerScore > 3) && (scoreDifference >= 2);
  }

  public void scoreWinnerPoint(Player pointWinner) {
    try {
      if (playerOne.name().equals(pointWinner.name())) {
        scorePointForPlayerOne(pointWinner);
      } else {
        scorePointForPlayerTwo(pointWinner);
      }
    } catch (GameSetInvalidScoringForPlayer e) {
      MessageDisplay.logErrorSimulator(e.getMessage());
    }
  }

  private int getScoreDifference() {
    return Math.abs(getPlayerOneScore() - getPlayerTwoScore());
  }

  private void scorePointForPlayerOne(Player player)
    throws GameSetInvalidScoringForPlayer {
    if (playerOne.name().equals(player.name())) {
      this.playerOneScore++;
      this.lastScorePlayer = player.name();
    } else {
      String errorMessage = String.format(
        "Was not able to score points for %s. Informed player was %s",
        playerOne.name(),
        player.name()
      );
      throw new GameSetInvalidScoringForPlayer(errorMessage);
    }
  }

  private void scorePointForPlayerTwo(Player player)
    throws GameSetInvalidScoringForPlayer {
    if (playerTwo.name().equals(player.name())) {
      this.playerTwoScore++;
      this.lastScorePlayer = player.name();
    } else {
      String errorMessage = String.format(
        "Was not able to score points for %s. Informed player was %s",
        playerTwo.name(),
        player.name()
      );
      throw new GameSetInvalidScoringForPlayer(errorMessage);
    }
  }

  public int getPlayerOneScore() {
    return playerOneScore;
  }

  public int getPlayerTwoScore() {
    return playerTwoScore;
  }

  public Player getPlayerOne() {
    return playerOne;
  }

  public Player getPlayerTwo() {
    return playerTwo;
  }

  public Player getWinnerPlayer() {
    return winnerPlayer;
  }

  public void setWinnerPlayer(Player winnerPlayer) {
    this.winnerPlayer = winnerPlayer;
  }

  public void setDeuce(boolean deuce) {
    this.deuce = deuce;
  }

  public boolean isDeuce() {
    return deuce;
  }

  public boolean isDeuceMode() {
    return deuceMode;
  }

  public void setDeuceMode(boolean deuceMode) {
    this.deuceMode = deuceMode;
  }

  public String getLastScorePlayer() {
    return lastScorePlayer;
  }
}
