package fact.it.movieservice;

import fact.it.movieservice.dto.MovieRequest;
import fact.it.movieservice.dto.MovieResponse;
import fact.it.movieservice.model.Movie;
import fact.it.movieservice.repository.MovieRepository;
import fact.it.movieservice.service.MovieService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class MovieServiceApplicationTests {

	@InjectMocks
	private MovieService movieService;

	@Mock
	private MovieRepository movieRepository;

	@Test
	public void testCreateMovie() {
		// Arrange
		MovieRequest movieRequest = new MovieRequest();
		movieRequest.setTitle("Jaws");
		movieRequest.setGenre("Horror");
		movieRequest.setPlaytime(120);
		movieRequest.setCodeId("JaHo120");

		// Act
		movieService.createMovie(movieRequest);

		// Assert
		verify(movieRepository, times(1)).save(any(Movie.class));
	}

	@Test
	public void testGetAllMovies() {
		// Arrange
		Movie movie = new Movie();
		movie.setTitle("Jaws");
		movie.setGenre("Horror");
		movie.setPlaytime(124);
		movie.setCodeId("JaHo124");

		when(movieRepository.findAll()).thenReturn(Arrays.asList(movie));

		// Act
		List<MovieResponse> movies = movieService.getAllMovies();

		// Assert
		assertEquals(1, movies.size());
		assertEquals("JaHo124", movies.get(0).getCodeId());
		assertEquals("Jaws", movies.get(0).getTitle());
		assertEquals("Horror", movies.get(0).getGenre());
		assertEquals(124, movies.get(0).getPlaytime());

		verify(movieRepository, times(1)).findAll();
	}

	@Test
	public void testGetAllMoviesByCodeId() {
		// Arrange
		Movie movie = new Movie();
		movie.setId("1");
		movie.setTitle("Jaws");
		movie.setGenre("Horror");
		movie.setPlaytime(124);
		// First two letters of title, first two letters of genre + playtime
		movie.setCodeId("JaHo124");

		when(movieRepository.findByCodeIdIn("JaHo124")).thenReturn(Arrays.asList(movie));

		// Act
		List<MovieResponse> movies = movieService.getAllMoviesByCodeId("JaHo124");

		// Assert
		assertEquals(1, movies.size());
		assertEquals("1", movies.get(0).getId());
		assertEquals("JaHo124", movies.get(0).getCodeId());
		assertEquals("Jaws", movies.get(0).getTitle());
		assertEquals("Horror", movies.get(0).getGenre());
		assertEquals(124, movies.get(0).getPlaytime());

		verify(movieRepository, times(1)).findByCodeIdIn(movie.getCodeId());
	}
}
