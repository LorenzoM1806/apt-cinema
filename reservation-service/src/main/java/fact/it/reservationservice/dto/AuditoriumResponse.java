package fact.it.reservationservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuditoriumResponse {
    private Long id;
    private String codeId;
    private Integer auditoriumNumber;
    private Integer auditoriumType;
}
