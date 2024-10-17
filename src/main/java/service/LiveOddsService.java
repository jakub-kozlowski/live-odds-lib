package service;

import domain.Match;
import domain.Team;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class LiveOddsService {

    private final LinkedList<Match> matches;

    public LiveOddsService() {
        matches = new LinkedList<>();
    }

    public void startNewMatch(Team homeTeam, Team awayTeam) {
        matches.addFirst(new Match(homeTeam, awayTeam));
    }

    public void updateMatchScore(Team homeTeam, Team awayTeam, int homeTeamScore, int awayTeamScore) {
        Match match = findMatch(homeTeam, awayTeam).orElseThrow();
        match.setHomeTeamScore(homeTeamScore);
        match.setAwayTeamScore(awayTeamScore);
    }

    public void finishMatch(Team homeTeam, Team awayTeam) {
        matches.removeIf(match -> match.getHomeTeam().equals(homeTeam) && match.getAwayTeam().equals(awayTeam));
    }

    public List<Match> getMatchesSummary() {
        return matches.stream()
                .sorted(Comparator.comparing((Match match) -> match.getHomeTeamScore() + match.getAwayTeamScore())
                        .reversed())
                .toList();
    }

    Optional<Match> findMatch(Team homeTeam, Team awayTeam) {
        return matches.stream()
                .filter(match -> match.getHomeTeam().equals(homeTeam) && match.getAwayTeam().equals(awayTeam))
                .findAny();
    }
}
