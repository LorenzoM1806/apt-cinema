package fact.it.filmservice.repository;


import fact.it.filmservice.model.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MovieRepository extends MongoRepository<Movie, String> {
    List<Movie> findByCodeIdIn(List<String> codeId);
}
