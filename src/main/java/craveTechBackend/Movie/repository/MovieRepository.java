package craveTechBackend.Movie.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import craveTechBackend.Movie.models.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    // 👤 Movies created by a specific user (Explore page)
    List<Movie> findByCreatedBy(String createdBy);
}
