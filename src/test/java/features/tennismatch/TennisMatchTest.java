package features.tennismatch;

import features.gameset.GameSet;
import features.tennismatch.TennisMatch;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TennisMatchTest {

  private TennisMatch tennisMatch;

  @BeforeEach
  void setUp() {
    tennisMatch = new TennisMatch();
  }

  @Test
  public void playTennisGameWinnerShouldHaveMinTwoPointsDifferenceAndMoreThanThreePoints() {
    //Given
    int expectedMinimumScoreDifference = 2;
    int expectedMinimumScorePoints = 3;
    //When
    GameSet gameSet = tennisMatch.playTennisMatch("Jo", "Ha");

    //Then
    int gameScoreDifference = Math.abs(
      gameSet.getPlayerOneScore() - gameSet.getPlayerTwoScore()
    );
    Assertions.assertNotNull(gameSet.getWinnerPlayer());
    Assertions.assertTrue(
      gameScoreDifference >= expectedMinimumScoreDifference
    );
    Assertions.assertTrue(
      gameSet.getPlayerScore(gameSet.getWinnerPlayer()) >
      expectedMinimumScorePoints
    );
  }
}
//love-love -> 0
//15-love -> 1
//30-0 -> 2
//40-0 -> 3
//50-0 -> 4 = 1win
//-------------
//30-30 -> 2
//40-30 -> 3
//50-30 -> 4 = 1win
//-------------
//30-40 -> 2
//40-40 -> 3 = deuce
//50-40 -> 4 = Advantage A
//50-50 -> 5 = deuce -> player A loose advantage
//50-60 -> 6 = Advantage B
//50-70 -> 5 = win B
//------------
