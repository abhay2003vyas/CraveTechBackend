package craveTechBackend.Movie.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import craveTechBackend.Movie.models.Movie;
import craveTechBackend.Movie.service.MovieService;

@RestController
@RequestMapping("/api/movies")
@CrossOrigin(
    origins = {
        "http://localhost:5173",
        "https://cravetech.vercel.app"
    }
)
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    // ================= PUBLIC =================

    // 🎬 MOVIES PAGE (VISIBLE TO ALL USERS)
    @GetMapping("/public")
    public ResponseEntity<List<Movie>> getAllPublicMovies() {
        return ResponseEntity.ok(movieService.getAllMovies());
    }

    // ================= USER-SPECIFIC =================

    // 📥 EXPLORE PAGE (ONLY LOGGED-IN USER MOVIES)
    @GetMapping("/my")
    public ResponseEntity<List<Movie>> getMyMovies(Principal principal) {
        return ResponseEntity.ok(
            movieService.getMoviesByUser(principal.getName())
        );
    }

    // ➕ ADD MOVIE (JWT REQUIRED)
    @PostMapping
    public ResponseEntity<Movie> addMovie(
            @RequestBody Movie movie,
            Principal principal
    ) {
        Movie savedMovie =
            movieService.addMovie(movie, principal.getName());

        return ResponseEntity.ok(savedMovie);
    }

    // 🗑️ DELETE MOVIE (ONLY OWNER CAN DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMovie(
            @PathVariable Long id,
            Principal principal
    ) {
        movieService.deleteMovie(id, principal.getName());
        return ResponseEntity.ok().build();
    }
}
