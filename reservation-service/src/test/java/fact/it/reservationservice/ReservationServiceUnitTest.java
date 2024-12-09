package fact.it.reservationservice;

import fact.it.reservationservice.Service.ReservationService;
import fact.it.reservationservice.dto.ReservationItemDto;
import fact.it.reservationservice.dto.ReservationRequest;
import fact.it.reservationservice.dto.ReservationResponse;
import fact.it.reservationservice.model.Reservation;
import fact.it.reservationservice.model.ReservationItem;
import fact.it.reservationservice.repository.ReservationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class ReservationServiceUnitTest {
    @InjectMocks
    private ReservationService reservationService;

    @Mock
    private ReservationRepository reservationRepository;

    @Test
    public void testMakeReservation() {
        ReservationItemDto reservationItemDto = new ReservationItemDto();
        reservationItemDto.setCodeId("LoSc5");
        reservationItemDto.setVisitorName("Lorenzo Miechielsen");
        reservationItemDto.setAuditoriumNumber("5");
        reservationItemDto.setMovieTitle("Scream 6");

        List<ReservationItemDto> reservationItemDtoList = new ArrayList<>();
        reservationItemDtoList.add(reservationItemDto);

        ReservationRequest reservationRequest = new ReservationRequest();
        reservationRequest.setReservationItemDtoList(reservationItemDtoList);

        reservationService.makeReservation(reservationRequest);

        verify(reservationRepository, times(1)).save(any(Reservation.class));
    }

    @Test
    public void testGetAllReservations() {
        ReservationItem reservationItem = new ReservationItem();
        reservationItem.setCodeId("LoSc5");
        reservationItem.setVisitorName("Lorenzo");
        reservationItem.setAuditoriumNumber("5");
        reservationItem.setMovieTitle("Scream 6");

        List<ReservationItem> reservationItems = new ArrayList<>();
        reservationItems.add(reservationItem);

        Reservation reservation = new Reservation();
        reservation.setId((long) 1);
        reservation.setCodeId("LoSc5");
        reservation.setReservationItemsList(reservationItems);

        when(reservationRepository.findAll()).thenReturn(Arrays.asList(reservation));

        List<ReservationResponse> reservations = reservationService.getAllReservations();

        assertEquals(1, reservations.size());
        assertEquals("LoSc5", reservations.get(0).getReservationItemsList().get(0).getCodeId());
        assertEquals("Lorenzo", reservations.get(0).getReservationItemsList().get(0).getVisitorName());
        assertEquals("5", reservations.get(0).getReservationItemsList().get(0).getAuditoriumNumber());
        assertEquals("Scream 6", reservations.get(0).getReservationItemsList().get(0).getMovieTitle());

        verify(reservationRepository, times(1)).findAll();

    }

    @Test
    public void testDeleteProduct() {
        ReservationItem reservationItem = new ReservationItem();
        reservationItem.setCodeId("LoSc5");
        reservationItem.setVisitorName("Lorenzo");
        reservationItem.setAuditoriumNumber("5");
        reservationItem.setMovieTitle("Scream 6");

        List<ReservationItem> reservationItems = new ArrayList<>();
        reservationItems.add(reservationItem);

        Reservation reservation = new Reservation();
        reservation.setId((long) 1);
        reservation.setCodeId("LoSc5");
        reservation.setReservationItemsList(reservationItems);

        reservationRepository.delete(reservation);

        List<ReservationResponse> reservations = reservationService.getAllReservations();

        assertEquals(0, reservations.size());

        verify(reservationRepository, times(1)).findAll();
    }


}
