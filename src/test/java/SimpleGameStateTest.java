import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class SimpleGameStateTest {

    SimpleGameState sut;

    @ParameterizedTest
    @CsvSource({"0,0", "1,1", "2,2"})
    void shouldNotChangeStateIfIsNotADeuce(int serverScore, int receiverScore) {
        sut = new SimpleGameState(TennisGame.of(serverScore, receiverScore));
        assertThat(sut.serverScores()).isInstanceOf(SimpleGameState.class);
        assertThat(sut.receiverScores()).isInstanceOf(SimpleGameState.class);
    }

    @Test
    void shouldChangeToWinStateOnServerWin() {
        sut = new SimpleGameState(TennisGame.of(3,0));
        assertThat(sut.serverScores()).isInstanceOf(WinnerGameState.class);
    }
    @Test
    void shouldChangeToWinStateOnReceiverWin() {
        sut = new SimpleGameState(TennisGame.of(0,3));
        assertThat(sut.receiverScores()).isInstanceOf(WinnerGameState.class);
    }

    @Test
    void shouldChangeStateToDeuceOnServerScore() {
        sut = new SimpleGameState(TennisGame.of(2,3));
        assertThat(sut.serverScores()).isInstanceOf(DeuceGameState.class);
    }

    @Test
    void shouldChangeStateToDeuceOnReceiverScore() {
        sut = new SimpleGameState(TennisGame.of(3,2));
        assertThat(sut.receiverScores()).isInstanceOf(DeuceGameState.class);
    }

    @Test
    void shouldStartGameWithZeroPoints() {
        assertScore(new SimpleGameState(TennisGame.of(0, 0)), "love - love");
    }

    @Test
    void shouldAddPointOnLeftIfServerScores() {
        assertScore(new SimpleGameState(TennisGame.of(1, 0)), "fifteen - love");
    }

    @Test
    void shouldAddPointOnRightIfReceiverScores() {
        assertScore(new SimpleGameState(TennisGame.of(0, 1)), "love - fifteen");
    }

    @Test
    void shouldAddPointsIfBothScore() {
        assertScore(new SimpleGameState(TennisGame.of(1, 1)), "fifteen - fifteen");
    }

    @Test
    void shouldAddTwoPoints() {
        assertScore(new SimpleGameState(TennisGame.of(2, 0)), "thirty - love");
        assertScore(new SimpleGameState(TennisGame.of(0, 2)), "love - thirty");
    }

    @Test
    void shouldAddThreePoints() {
        assertScore(new SimpleGameState(TennisGame.of(3, 0)), "forty - love");
        assertScore(new SimpleGameState(TennisGame.of(0, 3)), "love - forty");

    }

    private void assertScore(SimpleGameState sut, String expected) {
        assertThat(sut.formatScore()).isEqualTo(expected);
    }

}