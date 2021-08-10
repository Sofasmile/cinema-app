package cinema.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import cinema.model.MovieSession;

public interface MovieSessionDao {
    List<MovieSession> findAvailableSessions(Long movieId, LocalDate date);

    Optional<MovieSession> get(Long id);

    MovieSession add(MovieSession session);
}
