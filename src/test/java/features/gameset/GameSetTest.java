package features.gameset;

import static org.junit.jupiter.api.Assertions.*;

import errorhandling.GameSetInvalidScoringForPlayer;
import features.player.Player;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GameSetTest {

  private GameSet gameSet;
  private Player playerOne;
  private Player playerTwo;

  @BeforeEach
  void setUp() {
    playerOne = new Player("Jo");
    playerTwo = new Player("Ha");
    gameSet = new GameSet(playerOne, playerTwo);
  }

  @AfterEach
  void tearDown() {
    playerOne = null;
    playerTwo = null;
    gameSet = null;
  }

  @Test
  void whenGameStartPlayerScoreShouldBeZero() {
    //Given
    int expectedScorePlayerOne = 0;
    int expectedScorePlayerTwo = 0;
    Player playerOne = new Player("Jo");
    Player playerTwo = new Player("Ha");
    //When
    GameSet newGameSet = new GameSet(playerOne, playerTwo);
    //Then
    assertEquals(expectedScorePlayerOne, newGameSet.getPlayerOneScore());
    assertEquals(expectedScorePlayerTwo, newGameSet.getPlayerTwoScore());
  }

  @Test
  void whenScorePointForPlayerOneItShouldGainOnePoint() {
    //Given
    int expectedScorePlayerOne = 1;
    int expectedScorePlayerTwo = 0;
    //When
    gameSet.scoreWinnerPoint(playerOne);
    //Then
    assertEquals(expectedScorePlayerOne, gameSet.getPlayerOneScore());
    assertEquals(expectedScorePlayerTwo, gameSet.getPlayerTwoScore());
  }

  @Test
  void whenScorePointForPlayerTwoItShouldGainOnePoint() {
    //Given
    int expectedScorePlayerOne = 0;
    int expectedScorePlayerTwo = 1;
    //When
    gameSet.scoreWinnerPoint(playerTwo);
    //Then
    assertEquals(expectedScorePlayerOne, gameSet.getPlayerOneScore());
    assertEquals(expectedScorePlayerTwo, gameSet.getPlayerTwoScore());
  }

  @Test
  void shouldThrowExceptionWhenScoringForWrongPlayerTwo() {
    //Given
    int expectedScorePlayerOne = 0;
    int expectedScorePlayerTwo = 0;
    String expectedErrorMessage = String.format(
      "Was not able to score points for %s. Informed player was %s",
      playerTwo.name(),
      playerOne.name()
    );
    //When
    Exception exception = assertThrows(
      InvocationTargetException.class,
      () -> getscorePointForPlayerTwoMethod().invoke(gameSet, playerOne)
    );

    //Then
    assertEquals(
      GameSetInvalidScoringForPlayer.class,
      exception.getCause().getClass()
    );
    assertEquals(expectedErrorMessage, exception.getCause().getMessage());
    assertEquals(expectedScorePlayerOne, gameSet.getPlayerOneScore());
    assertEquals(expectedScorePlayerTwo, gameSet.getPlayerTwoScore());
  }

  @Test
  void shouldThrowExceptionWhenScoringForWrongPlayerOne() {
    //Given
    int expectedScorePlayerOne = 0;
    int expectedScorePlayerTwo = 0;
    String expectedErrorMessage = String.format(
      "Was not able to score points for %s. Informed player was %s",
      playerOne.name(),
      playerTwo.name()
    );
    //When
    Exception exception = assertThrows(
      InvocationTargetException.class,
      () -> getscorePointForPlayerOneMethod().invoke(gameSet, playerTwo)
    );

    //Then
    assertEquals(
      GameSetInvalidScoringForPlayer.class,
      exception.getCause().getClass()
    );
    assertEquals(expectedErrorMessage, exception.getCause().getMessage());
    assertEquals(expectedScorePlayerOne, gameSet.getPlayerOneScore());
    assertEquals(expectedScorePlayerTwo, gameSet.getPlayerTwoScore());
  }

  @Test
  void shouldReturnThePlayerScore() {
    //Given
    int expectedScorePlayerTwo = 1;
    scorePointForPlayer(playerTwo, 1);

    //When
    int playerScore = gameSet.getPlayerScore(playerTwo);

    //Then
    assertEquals(expectedScorePlayerTwo, playerScore);
  }

  @Test
  void gameSetShouldEnterDeuceModeWhenBothPlayerHaveThreePoints() {
    //Given
    int expectedScorePlayerOne = 3;
    int expectedScorePlayerTwo = 3;
    int scorePointsForDeuce = 3;
    scorePointForPlayer(playerOne, scorePointsForDeuce);
    scorePointForPlayer(playerTwo, scorePointsForDeuce);

    //When
    gameSet.checkForDeuce(playerOne);

    //Then
    assertEquals(expectedScorePlayerOne, gameSet.getPlayerOneScore());
    assertEquals(expectedScorePlayerTwo, gameSet.getPlayerTwoScore());
    assertTrue(gameSet.isDeuce());
    assertTrue(gameSet.isDeuceMode());
  }

  @Test
  void shouldReturnTheNameOfTheLastPlayerToScore() {
    //Given
    String expectedLastScorePlayer = playerOne.name();
    gameSet.scoreWinnerPoint(playerTwo);
    gameSet.scoreWinnerPoint(playerOne);
    //When
    String lastScoredPlayer = gameSet.getLastScorePlayer();
    //Then
    assertEquals(expectedLastScorePlayer, lastScoredPlayer);
  }

  private void scorePointForPlayer(Player player, int score) {
    for (int i = 0; i < score; i++) {
      gameSet.scoreWinnerPoint(player);
    }
  }

  private Method getscorePointForPlayerOneMethod()
    throws NoSuchMethodException {
    Method method =
      GameSet.class.getDeclaredMethod("scorePointForPlayerOne", Player.class);
    method.setAccessible(true);
    return method;
  }

  private Method getscorePointForPlayerTwoMethod()
    throws NoSuchMethodException {
    Method method =
      GameSet.class.getDeclaredMethod("scorePointForPlayerTwo", Player.class);
    method.setAccessible(true);
    return method;
  }
}
