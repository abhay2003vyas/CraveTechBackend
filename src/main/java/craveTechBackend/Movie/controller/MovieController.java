package craveTechBackend.Movie.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import craveTechBackend.Movie.models.Movie;
import craveTechBackend.Movie.service.MovieService;

@RestController
@RequestMapping("/api/movies")
@CrossOrigin(origins = "http://localhost:5173")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    // 📥 FETCH ALL MOVIES (Explore + Movies page)
    @GetMapping
    public ResponseEntity<List<Movie>> getAllMovies() {
        return ResponseEntity.ok(movieService.getAllMovies());
    }

    // ➕ ADD MOVIE (JWT Protected)
    @PostMapping
    public ResponseEntity<Movie> addMovie(@RequestBody Movie movie) {
        return ResponseEntity.ok(movieService.addMovie(movie));
    }

    // 🗑️ DELETE MOVIE (JWT Protected)
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMovie(@PathVariable Long id) {
        movieService.deleteMovie(id);
        return ResponseEntity.ok().build();
    }
}
