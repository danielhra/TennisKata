import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class DeuceGameStateTest {

    private DeuceGameState sut;

    @ParameterizedTest
    @CsvSource({"3,3", "4,4", "5,5"})
    void shouldMoveToAdvantageIfAnyScores(int serverScore, int receiverScore) {
        sut = new DeuceGameState(TennisGame.of(serverScore, receiverScore));
        assertThat(sut.serverScores()).isInstanceOf(AdvantageGameState.class);
        assertThat(sut.receiverScores()).isInstanceOf(AdvantageGameState.class);
    }

    @ParameterizedTest
    @CsvSource({"3,3", "4,4", "5,5"})
    void shouldGetDeuceAsScore(int serverScore, int receiverScore) {
        sut = new DeuceGameState(TennisGame.of(serverScore, receiverScore));
        assertThat(sut.formatScore()).isEqualTo("deuce");

    }
}