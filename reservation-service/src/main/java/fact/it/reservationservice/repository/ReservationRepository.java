package fact.it.reservationservice.repository;

import fact.it.reservationservice.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    //List<Reservation> findByCodeIdIn(List<String> codeId);

    @Query("SELECT r FROM Reservation r JOIN r.reservationItemsList i WHERE i.codeId IN :codeIds")
    List<Reservation> findByReservationItemCodeIds(List<String> codeIds);
}
