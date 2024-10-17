package service;

import domain.Team;

import java.util.ArrayList;

public class TeamService {

    private final ArrayList<Team> teams;

    public TeamService() {
        teams = new ArrayList<>();
    }

    public Team get(String teamName) {
        return teams.stream()
                .filter(team -> team.getName().equals(teamName))
                .findFirst()
                .orElseGet(() -> {
                    Team team = new Team(teamName);
                    teams.add(team);
                    return team;
                });
    }
}
