package fact.it.reservationservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "reservationItem")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReservationItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String codeId;
    private String visitorName;
    private String movieTitle;
    private String auditoriumNumber;
}
