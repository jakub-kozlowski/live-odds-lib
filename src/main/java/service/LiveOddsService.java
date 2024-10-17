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

    public Optional<Match> findMatch(Team homeTeam, Team awayTeam) {
        return matches.stream()
                .filter(match -> match.getHomeTeam().equals(homeTeam) && match.getAwayTeam().equals(awayTeam))
                .findAny();
    }
}
