package fact.it.reservationservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationResponse {
    private String reservationNumber;
    private List<ReservationItemDto> reservationItemsList;
}
