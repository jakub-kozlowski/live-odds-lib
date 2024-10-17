package service;

import domain.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TeamServiceTest {

    TeamService teamService;

    @BeforeEach
    void setUp() {
        teamService = new TeamService();
    }

    @Test
    public void givenTeamName_whenGettingTeam_sameObjectIsReturnedForSameName() {
        String teamName = "someName";

        Team teamA = teamService.get(new String(teamName));
        Team teamB = teamService.get(new String(teamName));

        assertThat(teamA == teamB).isTrue();
    }

    @Test
    public void givenDifferentTeamNames_whenGettingTeam_differentObjectsAreReturnedForDifferentNames() {
        Team teamA = teamService.get("someName");
        Team teamB = teamService.get("differentName");

        assertThat(teamA == teamB).isFalse();
    }
}