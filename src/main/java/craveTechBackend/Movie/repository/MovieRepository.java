package craveTechBackend.Movie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import craveTechBackend.Movie.models.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}
