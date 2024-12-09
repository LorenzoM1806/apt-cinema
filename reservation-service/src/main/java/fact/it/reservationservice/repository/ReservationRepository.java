package fact.it.reservationservice.repository;

import fact.it.reservationservice.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByCodeIdIn(List<String> codeId);
}
