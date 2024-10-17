package service;

import domain.Match;
import domain.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LiveOddsServiceTest {

    LiveOddsService liveOddsService;
    TeamService teamService;

    @BeforeEach
    void setUp() {
        liveOddsService = new LiveOddsService();
        teamService = new TeamService();
    }

    @Test
    void whenNewMatchAdded_thenScoreIsNilNil() {
        Team mexico = teamService.get("Mexico");
        Team canada = teamService.get("Canada");
        liveOddsService.startNewMatch(mexico, canada);

        Match match = liveOddsService.findMatch(mexico, canada).get();
        assertThat(match.getHomeTeamScore()).isZero();
        assertThat(match.getAwayTeamScore()).isZero();
    }
}