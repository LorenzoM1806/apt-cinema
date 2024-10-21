package fact.it.filmservice.controller;

import fact.it.filmservice.dto.MovieRequest;
import fact.it.filmservice.dto.MovieResponse;
import fact.it.filmservice.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movie")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void createMovie
            (@RequestBody MovieRequest movieRequest) {
        movieService.createMovie(movieRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<MovieResponse> getAllMoviesByCodeId
            (@RequestParam List<String> codeId) {
        return movieService.getAllMoviesByCodeId(codeId);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<MovieResponse> getAllProducts() {
        return movieService.getAllMovies();
    }
}
