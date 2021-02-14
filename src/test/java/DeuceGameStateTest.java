import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class DeuceGameStateTest {

    private DeuceGameState sut = new DeuceGameState();

    @ParameterizedTest
    @CsvSource({"3,3", "4,4", "5,5"})
    void shouldMoveToAdvantageIfServerScores(int serverScore, int receiverScore) {
        TennisGame tennisGame = TennisGameTest.instantiateGame(serverScore, receiverScore);
        sut.serverScores(tennisGame);

        assertThat(tennisGame.getState()).isInstanceOf(AdvantageGameState.class);
    }
    @ParameterizedTest
    @CsvSource({"3,3", "4,4", "5,5"})
    void shouldMoveToAdvantageIfReceiverScores(int serverScore, int receiverScore) {
        TennisGame tennisGame = TennisGameTest.instantiateGame(serverScore, receiverScore);
        sut.receiverScores(tennisGame);

        assertThat(tennisGame.getState()).isInstanceOf(AdvantageGameState.class);
    }

    @ParameterizedTest
    @CsvSource({"3,3", "4,4", "5,5"})
    void shouldGetDeuceAsScore(int serverScore, int receiverScore) {
        sut = new DeuceGameState();
        assertThat(sut.formatScore(TennisGameTest.instantiateGame(serverScore, receiverScore))).isEqualTo("deuce");
    }
}