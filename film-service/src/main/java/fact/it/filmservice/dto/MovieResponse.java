package fact.it.filmservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovieResponse {
    private String id;
    private String codeId;
    private String title;
    private int playtime;
    private String genre;
    private List<String> playsInCinemaList;
}
