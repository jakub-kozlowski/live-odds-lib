package service;

import domain.Match;
import domain.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.function.Function;

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

    @Test
    void givenNoMatchesStarted_whenGettingSummary_thenSummaryIsEmpty() {
        assertThat(liveOddsService.getMatchesSummary()).isEmpty();
    }

    @Test
    void givenTwoMatchesStarted_whenGettingSummary_thenMatchesInOrderOfTotalScore() {
        Team spain = teamService.get("Spain");
        Team brazil = teamService.get("Brazil");

        liveOddsService.startNewMatch(mexico, canada);
        liveOddsService.startNewMatch(spain, brazil);

        liveOddsService.updateMatchScore(mexico, canada, 0, 5);
        liveOddsService.updateMatchScore(spain, brazil, 10, 2);

        assertThat(liveOddsService.getMatchesSummary())
                .extracting(matchAsString())
                .containsExactly(
                        "Spain 10 - Brazil 2",
                        "Mexico 0 - Canada 5"
                );
    }

    @Test
    void givenTwoMatchesStartedWithSameTotalScore_whenGettingSummary_thenMostRecentStartedMatchIsAtTheTop() {
        Team spain = teamService.get("Spain");
        Team brazil = teamService.get("Brazil");
        Team uruguay = teamService.get("Uruguay");
        Team italy = teamService.get("Italy");

        liveOddsService.startNewMatch(spain, brazil);
        liveOddsService.startNewMatch(uruguay, italy);

        liveOddsService.updateMatchScore(spain, brazil, 10, 2);
        liveOddsService.updateMatchScore(uruguay, italy, 6, 6);

        assertThat(liveOddsService.getMatchesSummary())
                .extracting(matchAsString())
                .containsExactly(
                        "Uruguay 6 - Italy 6",
                        "Spain 10 - Brazil 2"
                );
    }

    @Test
    void whenGettingSummary_itMatchesExampleInTheAssignment() {
        Team spain = teamService.get("Spain");
        Team brazil = teamService.get("Brazil");
        Team germany = teamService.get("Germany");
        Team france = teamService.get("France");
        Team uruguay = teamService.get("Uruguay");
        Team italy = teamService.get("Italy");
        Team argentina = teamService.get("Argentina");
        Team australia = teamService.get("Australia");

        liveOddsService.startNewMatch(mexico, canada);
        liveOddsService.startNewMatch(spain, brazil);
        liveOddsService.startNewMatch(germany, france);
        liveOddsService.startNewMatch(uruguay, italy);
        liveOddsService.startNewMatch(argentina, australia);

        liveOddsService.updateMatchScore(mexico, canada, 0, 5);
        liveOddsService.updateMatchScore(spain, brazil, 10, 2);
        liveOddsService.updateMatchScore(germany, france, 2, 2);
        liveOddsService.updateMatchScore(uruguay, italy, 6, 6);
        liveOddsService.updateMatchScore(argentina, australia, 3, 1);

        assertThat(liveOddsService.getMatchesSummary())
                .extracting(matchAsString())
                .containsExactly(
                        "Uruguay 6 - Italy 6",
                        "Spain 10 - Brazil 2",
                        "Mexico 0 - Canada 5",
                        "Argentina 3 - Australia 1",
                        "Germany 2 - France 2"
                );
    }

    Function<Match, String> matchAsString() {
        return match -> match.getHomeTeam().getName() + " " + match.getHomeTeamScore()
                + " - " + match.getAwayTeam().getName() + " " + match.getAwayTeamScore();
    }
}