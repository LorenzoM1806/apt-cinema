package fact.it.reservationservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovieResponse {
    private String id;
    private String codeId;
    private String title;
    private int playtime;
    private String genre;
}
