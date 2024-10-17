package service;

import domain.Match;
import domain.Team;

import java.util.ArrayList;
import java.util.Optional;

public class LiveOddsService {

    private final ArrayList<Match> matches;

    public LiveOddsService() {
        matches = new ArrayList<>();
    }

    public void startNewMatch(Team homeTeam, Team awayTeam) {
        matches.add(new Match(homeTeam, awayTeam));
    }

    public void updateMatchScore(Team homeTeam, Team awayTeam, int homeTeamScore, int awayTeamScore) {
        Match match = findMatch(homeTeam, awayTeam).orElseThrow();
        match.setHomeTeamScore(homeTeamScore);
        match.setAwayTeamScore(awayTeamScore);
    }

    public void finishMatch(Team homeTeam, Team awayTeam) {
        matches.removeIf(match -> match.getHomeTeam().equals(homeTeam) && match.getAwayTeam().equals(awayTeam));
    }

    Optional<Match> findMatch(Team homeTeam, Team awayTeam) {
        return matches.stream()
                .filter(match -> match.getHomeTeam().equals(homeTeam) && match.getAwayTeam().equals(awayTeam))
                .findAny();
    }
}
