package features.tennismatch;

import features.gameset.GameSet;
import features.player.Player;
import features.printdisplay.MessageDisplay;
import features.score.ScoreRules;

public class TennisMatch {

  public TennisMatch() {}

  public GameSet playTennisMatch(String playerOneName, String playerTwoName) {
    Player playerOne = new Player(playerOneName);
    Player playerTwo = new Player(playerTwoName);
    GameSet gameSet = new GameSet(playerOne, playerTwo);
    MessageDisplay.gameStart();
    ScoreRules.displayGameScore(gameSet);
    while (gameSet.getWinnerPlayer() == null) {
      Player pointWinner = gameSet.playPoint();
      gameSet.scoreWinnerPoint(pointWinner);
      if (gameSet.hasVictoryCondition(pointWinner)) {
        gameSet.setWinnerPlayer(pointWinner);
      } else {
        gameSet.checkForDeuce(pointWinner);
      }
      ScoreRules.displayGameScore(gameSet);
    }
    MessageDisplay.winner(gameSet.getWinnerPlayer().name());
    MessageDisplay.finalGameScore(gameSet);
    return gameSet;
  }
}
