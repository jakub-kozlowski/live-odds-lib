package domain;

import lombok.Getter;

@Getter
public class Match {

    private final Team homeTeam;
    private final Team awayTeam;

    private int homeTeamScore;
    private int awayTeamScore;

    public Match(Team homeTeam, Team awayTeam) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
    }
}
