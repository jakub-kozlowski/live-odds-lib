# live-odds-lib

A Live Football World Cup Scoreboard library

- not made thread-safe - the assumption is if the library was used in multiple application instances
it would need to be synchronized externally, eg. on database
- used immutability - however `LiveOddsService.getMatchesSummary()` exposes `Match` objects which are mutable.
If it was persisted rather than kept in-memory it wouldn't have been a problem.