package domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode
@Getter
public class Team {

    private final String name;

    public Team(String name) {
        this.name = name;
    }
}
