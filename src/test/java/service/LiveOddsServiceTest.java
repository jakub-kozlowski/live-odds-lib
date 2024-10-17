package service;

import domain.Match;
import domain.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LiveOddsServiceTest {

    LiveOddsService liveOddsService;
    TeamService teamService;

    Team mexico;
    Team canada;

    @BeforeEach
    void setUp() {
        liveOddsService = new LiveOddsService();
        teamService = new TeamService();

        mexico = teamService.get("Mexico");
        canada = teamService.get("Canada");
    }

    @Test
    void whenNewMatchAdded_thenScoreIsNilNil() {
        liveOddsService.startNewMatch(mexico, canada);

        Match match = liveOddsService.findMatch(mexico, canada).get();
        assertThat(match.getHomeTeamScore()).isZero();
        assertThat(match.getAwayTeamScore()).isZero();
    }

    @Test
    void canUpdateMatchScore() {
        liveOddsService.startNewMatch(mexico, canada);

        Match match = liveOddsService.findMatch(mexico, canada).get();
        liveOddsService.updateMatchScore(mexico, canada, 2, 5);

        assertThat(match.getHomeTeamScore()).isEqualTo(2);
        assertThat(match.getAwayTeamScore()).isEqualTo(5);
    }

    @Test
    void canFinishMatch() {
        liveOddsService.startNewMatch(mexico, canada);
        assertThat(liveOddsService.findMatch(mexico, canada)).isNotEmpty();

        liveOddsService.finishMatch(mexico, canada);
        assertThat(liveOddsService.findMatch(mexico, canada)).isEmpty();

    }
}