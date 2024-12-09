package fact.it.movieservice.service;

import fact.it.movieservice.dto.MovieRequest;
import fact.it.movieservice.dto.MovieResponse;
import fact.it.movieservice.model.Movie;
import fact.it.movieservice.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;

    public void createMovie(MovieRequest movieRequest){
        Movie movie = Movie.builder()
                .codeId(movieRequest.getCodeId())
                .title(movieRequest.getTitle())
                .playtime(movieRequest.getPlaytime())
                .genre(movieRequest.getGenre())
                .build();

        movieRepository.save(movie);
    }

    public List<MovieResponse> getAllMovies() {
        List<Movie> movieList = movieRepository.findAll();

        return movieList.stream().map(this::mapToMovieResponse).toList();
    }

    public List<MovieResponse> getAllMoviesByCodeId(List<String> codeId) {
        List<Movie> movieList = movieRepository.findByCodeIdIn(codeId);

        return movieList.stream().map(this::mapToMovieResponse).toList();
    }

    private MovieResponse mapToMovieResponse(Movie movie) {
        return MovieResponse.builder()
                .id(movie.getId())
                .codeId(movie.getCodeId())
                .title(movie.getTitle())
                .playtime(movie.getPlaytime())
                .genre(movie.getGenre())
                .build();
    }
}
