import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TennisGameTest {

    @Test
    void shouldStartGameWithZeroPoints() {
        assertScore(TennisGame.of(0, 0), "love - love");
    }

    @Test
    void shouldAddPointOnLeftIfServerScores() {
        assertScore(TennisGame.of(1, 0), "fifteen - love");
    }

    @Test
    void shouldAddPointOnRightIfReceiverScores() {
        assertScore(TennisGame.of(0, 1), "love - fifteen");
    }

    @Test
    void shouldAddPointsIfBothScore() {
        assertScore(TennisGame.of(1, 1), "fifteen - fifteen");
    }

    @Test
    void shouldAddTwoPoints() {
        assertScore(TennisGame.of(2, 0), "thirty - love");
        assertScore(TennisGame.of(0, 2), "love - thirty");
    }

    @Test
    void shouldAddThreePoints() {
        assertScore(TennisGame.of(3, 0), "forty - love");
        assertScore(TennisGame.of(0, 3), "love - forty");

    }

    @Test
    void shouldCallDeuceIfBothPlayersHaveThreePoints() {
        assertScore(TennisGame.of(3, 3), "deuce");
    }

    @Test
    void shouldCallAdvantageInOnServerPointWhenInDeuce() {
        assertScore(TennisGame.of(4, 3), "advantage in");
        assertScore(TennisGame.of(5, 4), "advantage in");
        assertScore(TennisGame.of(6, 5), "advantage in");
    }

    @Test
    void shouldCallAdvantageOutOnReceiverPointWhenInDeuce() {
        assertScore(TennisGame.of(3, 4), "advantage out");
        assertScore(TennisGame.of(4, 5), "advantage out");
        assertScore(TennisGame.of(5, 6), "advantage out");
    }

    @Test
    void shouldCallWinnerOnServerIfScoreIsFourAndTwoPointsAheadOfReceiver() {
        assertScore(TennisGame.of(4, 0), "winner in");
        assertScore(TennisGame.of(5, 3), "winner in");
        assertScore(TennisGame.of(6, 4), "winner in");
    }

    @Test
    void shouldCallWinnerOnReceiverIfScoreIsFourAndTwoPointsAheadOfServer() {
        assertScore(TennisGame.of(0, 4), "winner out");
        assertScore(TennisGame.of(3, 5), "winner out");
        assertScore(TennisGame.of(4, 6), "winner out");
    }

    private void assertScore(TennisGame sut, String expected) {
        assertThat(sut.evaluateScore()).isEqualTo(expected);
    }
}