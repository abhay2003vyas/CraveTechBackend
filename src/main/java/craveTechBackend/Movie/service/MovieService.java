package craveTechBackend.Movie.service;

import java.util.List;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import craveTechBackend.Movie.models.Movie;
import craveTechBackend.Movie.repository.MovieRepository;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    // ================= ADD MOVIE =================
    // ➕ Add movie for logged-in user
    public Movie addMovie(Movie movie, String userEmail) {

        movie.setCreatedBy(userEmail); // 👈 owner set here
        return movieRepository.save(movie);
    }

    // ================= FETCH =================

    // 🎬 Public movies (Movies page)
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    // 👤 User-specific movies (Explore page)
    public List<Movie> getMoviesByUser(String userEmail) {
        return movieRepository.findByCreatedBy(userEmail);
    }

    // ================= DELETE =================

    // 🗑️ Delete movie (ONLY OWNER)
    public void deleteMovie(Long id, String userEmail) {

        Movie movie = movieRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Movie not found")
                );

        if (!movie.getCreatedBy().equals(userEmail)) {
            throw new AccessDeniedException("You are not allowed to delete this movie");
        }

        movieRepository.delete(movie);
    }
}
