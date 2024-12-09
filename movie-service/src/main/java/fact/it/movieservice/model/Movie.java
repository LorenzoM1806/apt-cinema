package fact.it.movieservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(value = "movie")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Movie {
    private String id;
    private String codeId;
    private String title;
    private int playtime;
    private String genre;
}