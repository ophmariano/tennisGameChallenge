package features.score;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import features.gameset.GameSet;
import features.player.Player;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ScoreRulesTest {

  private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

  @Mock
  private GameSet mockGameSet;

  @Mock
  private Player mockPlayerOne;

  @Mock
  private Player mockPlayerTwo;

  @BeforeEach
  public void setUp() {
    System.setOut(new PrintStream(outputStreamCaptor));
    when(mockGameSet.getPlayerOne()).thenReturn(mockPlayerOne);
    when(mockPlayerOne.name()).thenReturn("Jo");
    when(mockGameSet.getPlayerTwo()).thenReturn(mockPlayerTwo);
    when(mockPlayerTwo.name()).thenReturn("Ha");
  }

  @Test
  void shouldDisplayLoveToLoveWhenGameScoreIsZeroAll() {
    //Given
    when(mockGameSet.getWinnerPlayer()).thenReturn(null);
    when(mockGameSet.isDeuceMode()).thenReturn(false);
    when(mockGameSet.getPlayerOneScore()).thenReturn(0);
    when(mockGameSet.getPlayerTwoScore()).thenReturn(0);

    //When
    ScoreRules.displayGameScore(mockGameSet);
    //Then

    assertEquals("Jo | Love - Love | Ha", outputStreamCaptor.toString().trim());
  }

  @Test
  void shouldDisplay15ToLoveWhenGameScoreIsOneToZero() {
    //Given
    when(mockGameSet.getWinnerPlayer()).thenReturn(null);
    when(mockGameSet.isDeuceMode()).thenReturn(false);
    when(mockGameSet.getPlayerOneScore()).thenReturn(1);
    when(mockGameSet.getPlayerTwoScore()).thenReturn(0);

    //When
    ScoreRules.displayGameScore(mockGameSet);
    //Then

    assertEquals("Jo | 15 - Love | Ha", outputStreamCaptor.toString().trim());
  }

  @Test
  void shouldDisplayLoveTo15WhenGameScoreIsZeroToOne() {
    //Given
    when(mockGameSet.getWinnerPlayer()).thenReturn(null);
    when(mockGameSet.isDeuceMode()).thenReturn(false);
    when(mockGameSet.getPlayerOneScore()).thenReturn(0);
    when(mockGameSet.getPlayerTwoScore()).thenReturn(1);

    //When
    ScoreRules.displayGameScore(mockGameSet);
    //Then

    assertEquals("Jo | Love - 15 | Ha", outputStreamCaptor.toString().trim());
  }

  @Test
  void shouldDisplay15To15WhenGameScoreIsOneAll() {
    //Given
    when(mockGameSet.getWinnerPlayer()).thenReturn(null);
    when(mockGameSet.isDeuceMode()).thenReturn(false);
    when(mockGameSet.getPlayerOneScore()).thenReturn(1);
    when(mockGameSet.getPlayerTwoScore()).thenReturn(1);

    //When
    ScoreRules.displayGameScore(mockGameSet);
    //Then

    assertEquals("Jo | 15 - 15 | Ha", outputStreamCaptor.toString().trim());
  }

  @Test
  void shouldDisplay15To30WhenGameScoreIsOneToTwo() {
    //Given
    when(mockGameSet.getWinnerPlayer()).thenReturn(null);
    when(mockGameSet.isDeuceMode()).thenReturn(false);
    when(mockGameSet.getPlayerOneScore()).thenReturn(1);
    when(mockGameSet.getPlayerTwoScore()).thenReturn(2);

    //When
    ScoreRules.displayGameScore(mockGameSet);
    //Then

    assertEquals("Jo | 15 - 30 | Ha", outputStreamCaptor.toString().trim());
  }

  @Test
  void shouldDisplay30To15WhenGameScoreIsTwoToOne() {
    //Given
    when(mockGameSet.getWinnerPlayer()).thenReturn(null);
    when(mockGameSet.isDeuceMode()).thenReturn(false);
    when(mockGameSet.getPlayerOneScore()).thenReturn(2);
    when(mockGameSet.getPlayerTwoScore()).thenReturn(1);

    //When
    ScoreRules.displayGameScore(mockGameSet);
    //Then

    assertEquals("Jo | 30 - 15 | Ha", outputStreamCaptor.toString().trim());
  }

  @Test
  void shouldDisplay30To30WhenGameScoreIsTwoAll() {
    //Given
    when(mockGameSet.getWinnerPlayer()).thenReturn(null);
    when(mockGameSet.isDeuceMode()).thenReturn(false);
    when(mockGameSet.getPlayerOneScore()).thenReturn(2);
    when(mockGameSet.getPlayerTwoScore()).thenReturn(2);

    //When
    ScoreRules.displayGameScore(mockGameSet);
    //Then

    assertEquals("Jo | 30 - 30 | Ha", outputStreamCaptor.toString().trim());
  }

  @Test
  void shouldDisplay40To30WhenGameScoreIsThreeToTwo() {
    //Given
    when(mockGameSet.getWinnerPlayer()).thenReturn(null);
    when(mockGameSet.isDeuceMode()).thenReturn(false);
    when(mockGameSet.getPlayerOneScore()).thenReturn(3);
    when(mockGameSet.getPlayerTwoScore()).thenReturn(2);

    //When
    ScoreRules.displayGameScore(mockGameSet);
    //Then

    assertEquals("Jo | 40 - 30 | Ha", outputStreamCaptor.toString().trim());
  }

  @Test
  void shouldDisplay30To40WhenGameScoreIsTwoToThree() {
    //Given
    when(mockGameSet.getWinnerPlayer()).thenReturn(null);
    when(mockGameSet.isDeuceMode()).thenReturn(false);
    when(mockGameSet.getPlayerOneScore()).thenReturn(2);
    when(mockGameSet.getPlayerTwoScore()).thenReturn(3);

    //When
    ScoreRules.displayGameScore(mockGameSet);
    //Then

    assertEquals("Jo | 30 - 40 | Ha", outputStreamCaptor.toString().trim());
  }

  @Test
  void shouldDisplayDeuceWhenGameScoreIsTieAfterTwoPoints() {
    //Given
    when(mockGameSet.getWinnerPlayer()).thenReturn(null);
    when(mockGameSet.isDeuceMode()).thenReturn(true);
    when(mockGameSet.isDeuce()).thenReturn(true);

    //When
    ScoreRules.displayGameScore(mockGameSet);
    //Then

    assertEquals("Jo | Deuce | Ha", outputStreamCaptor.toString().trim());
  }

  @Test
  void shouldDisplayAdvantageTo40WhenGameScoreIsFourToThree() {
    //Given
    when(mockGameSet.getWinnerPlayer()).thenReturn(null);
    when(mockGameSet.isDeuceMode()).thenReturn(true);
    when(mockGameSet.isDeuce()).thenReturn(false);
    when(mockGameSet.getLastScorePlayer()).thenReturn("Jo");

    //When
    ScoreRules.displayGameScore(mockGameSet);
    //Then

    assertEquals("Jo | Adv - 40 | Ha", outputStreamCaptor.toString().trim());
  }

  @Test
  void shouldDisplay40ToAdvantageWhenGameScoreIsThreeToFour() {
    //Given
    when(mockGameSet.getWinnerPlayer()).thenReturn(null);
    when(mockGameSet.isDeuceMode()).thenReturn(true);
    when(mockGameSet.isDeuce()).thenReturn(false);
    when(mockGameSet.getLastScorePlayer()).thenReturn("Ha");

    //When
    ScoreRules.displayGameScore(mockGameSet);
    //Then

    assertEquals("Jo | 40 - Adv | Ha", outputStreamCaptor.toString().trim());
  }
}
