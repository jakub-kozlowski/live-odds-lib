package domain;

import lombok.Getter;
import lombok.Setter;

@Getter
public class Match {

    private final Team homeTeam;
    private final Team awayTeam;

    @Setter
    private int homeTeamScore;
    @Setter
    private int awayTeamScore;

    public Match(Team homeTeam, Team awayTeam) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
    }
}
