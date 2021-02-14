import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class SimpleGameStateTest {

    SimpleGameState sut = new SimpleGameState();

    @ParameterizedTest
    @CsvSource({"0,0", "1,1", "2,2"})
    void shouldNotChangeStateIfServerIsNotADeuce(int serverScore, int receiverScore) {
        TennisGame tennisGame = TennisGameTest.instantiateGame(serverScore, receiverScore);
        sut.serverScores(tennisGame);
        assertThat(tennisGame.getState()).isInstanceOf(SimpleGameState.class);
    }

    @ParameterizedTest
    @CsvSource({"0,0", "1,1", "2,2"})
    void shouldNotChangeStateIfReceiverIsNotADeuce(int serverScore, int receiverScore) {
        TennisGame tennisGame = TennisGameTest.instantiateGame(serverScore, receiverScore);
        sut.receiverScores(tennisGame);
        assertThat(tennisGame.getState()).isInstanceOf(SimpleGameState.class);
    }

    @Test
    void shouldChangeToWinStateOnServerWin() {
        TennisGame tennisGame = TennisGameTest.instantiateGame(4, 0);
        sut.serverScores(tennisGame);
        assertThat(tennisGame.getState()).isInstanceOf(WinnerGameState.class);
    }
    @Test
    void shouldChangeToWinStateOnReceiverWin() {
        TennisGame tennisGame = TennisGameTest.instantiateGame(0, 4);
        sut = new SimpleGameState();
        sut.receiverScores(tennisGame);
        assertThat(tennisGame.getState()).isInstanceOf(WinnerGameState.class);
    }

    @Test
    void shouldChangeStateToDeuceOnServerScore() {
        TennisGame tennisGame = TennisGameTest.instantiateGame(3, 3);
        sut.serverScores(tennisGame);
        assertThat(tennisGame.getState()).isInstanceOf(DeuceGameState.class);
    }

    @Test
    void shouldChangeStateToDeuceOnReceiverScore() {
        TennisGame tennisGame = TennisGameTest.instantiateGame(3, 3);
        sut.receiverScores(tennisGame);
        assertThat(tennisGame.getState()).isInstanceOf(DeuceGameState.class);
    }

    @Test
    void shouldStartGameWithZeroPoints() {
        assertScore(TennisGameTest.instantiateGame(0, 0), "love - love");
    }

    @Test
    void shouldAddPointOnLeftIfServerScores() {
        assertScore(TennisGameTest.instantiateGame(1, 0), "fifteen - love");
    }

    @Test
    void shouldAddPointOnRightIfReceiverScores() {
        assertScore(TennisGameTest.instantiateGame(0, 1), "love - fifteen");
    }

    @Test
    void shouldAddPointsIfBothScore() {
        assertScore(TennisGameTest.instantiateGame(1, 1), "fifteen - fifteen");
    }

    @Test
    void shouldAddTwoPoints() {
        assertScore(TennisGameTest.instantiateGame(2, 0), "thirty - love");
        assertScore(TennisGameTest.instantiateGame(0, 2), "love - thirty");
    }

    @Test
    void shouldAddThreePoints() {
        assertScore(TennisGameTest.instantiateGame(3, 0), "forty - love");
        assertScore(TennisGameTest.instantiateGame(0, 3), "love - forty");

    }

    private void assertScore(TennisGame tennisGame, String expected) {
        assertThat(sut.formatScore(tennisGame)).isEqualTo(expected);
    }

}