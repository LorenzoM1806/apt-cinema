package fact.it.reservationservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationItemDto {
    private String codeId;
    private String visitorName;
    private String movieTitle;
    private String auditoriumNumber;
}
