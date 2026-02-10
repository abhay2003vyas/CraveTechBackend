package craveTechBackend.Movie.service;

import java.util.List;

import org.springframework.stereotype.Service;

import craveTechBackend.Movie.models.Movie;
import craveTechBackend.Movie.repository.MovieRepository;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    // ➕ Add movie
    public Movie addMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    // 📥 Get all movies
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    // 🗑️ Delete movie
    public void deleteMovie(Long id) {
        movieRepository.deleteById(id);
    }
}
