package fact.it.reservationservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import fact.it.reservationservice.dto.ReservationResponse;
import fact.it.reservationservice.dto.ReservationRequest;
import fact.it.reservationservice.Service.ReservationService;
import java.util.List;

@RestController
@RequestMapping("/api/reservation")
@RequiredArgsConstructor
public class reservationController {

    private final ReservationService reservationService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public String makeReservation(@RequestBody ReservationRequest reservationRequest) {
        boolean result = reservationService.makeReservation(reservationRequest);
        return (result ? "Reservation made successfully" : "Reservation made failed");
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public  List<ReservationResponse> getAllReservations() {return reservationService.getAllReservations();}

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void deleteReservation
            (@RequestBody ReservationRequest reservationRequest) {
        reservationService.deleteReservation(reservationRequest);
    }

}
