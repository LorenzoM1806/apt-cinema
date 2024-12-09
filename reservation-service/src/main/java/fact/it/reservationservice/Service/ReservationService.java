package fact.it.reservationservice.Service;

import fact.it.reservationservice.dto.*;
import fact.it.reservationservice.model.Reservation;
import fact.it.reservationservice.model.ReservationItem;
import fact.it.reservationservice.repository.ReservationRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ReservationService {
        private final ReservationRepository reservationRepository;
        private final WebClient webClient;

        @Value("${visitorService.baseurl}")
        private String visitorServiceBaseUrl;

        @Value("${auditoriumService.baseurl}")
        private String auditoriumServiceBaseUrl;

        @Value("${movieService.baseurl}")
        private String movieServiceBaseUrl;

        public boolean makeReservation(ReservationRequest reservationRequest) {
                // Check if the reservationItemDtoList is null or empty
                if (reservationRequest == null || reservationRequest.getReservationItemDtoList() == null || reservationRequest.getReservationItemDtoList().isEmpty()) {
                        System.out.println("No reservation items provided");
                        return false;  // Handle the case when no items are provided
                }

                // Proceed with the reservation logic if the list is valid
                List<ReservationItem> reservationItems = reservationRequest.getReservationItemDtoList()
                        .stream()
                        .map(this::mapToReservationItem)  // Mapping each DTO to ReservationItem
                        .collect(Collectors.toList());

                // Create and save the reservation entity
                Reservation reservation = new Reservation();
                reservation.setCodeId(UUID.randomUUID().toString()); // Or generate your own ID
                reservation.setReservationItemsList(reservationItems);

                // Save the reservation
                reservationRepository.save(reservation);

                return true;
        }

        public void deleteReservation(ReservationRequest reservationRequest){
                List<String> reservationCodeIds = new ArrayList<>();
                reservationCodeIds.add(reservationRequest.getReservationItemDtoList().stream().map(r -> r.getCodeId()).toString());
                if(reservationRepository.findByCodeIdIn(reservationCodeIds).stream().findFirst().isPresent()) {
                        Reservation reservation = reservationRepository.findByCodeIdIn(reservationCodeIds).stream().findFirst().get();
                        reservationRepository.delete(reservation);
                }

        }
        public List<ReservationResponse> getAllReservations() {
                List<Reservation> reservations = reservationRepository.findAll();

                return reservations.stream()
                        .map(reservation -> new ReservationResponse(
                                reservation.getCodeId(),
                                mapToReservationItemsDto(reservation.getReservationItemsList())
                        ))
                        .collect(Collectors.toList());
        }

        private ReservationItem mapToReservationItem(ReservationItemDto reservationItemDto) {
                ReservationItem reservationItem = new ReservationItem();
                reservationItem.setCodeId(reservationItemDto.getCodeId());
                reservationItem.setVisitorName(reservationItemDto.getVisitorName());
                reservationItem.setMovieTitle(reservationItemDto.getMovieTitle());
                reservationItem.setAuditoriumNumber(reservationItemDto.getAuditoriumNumber());
                return reservationItem;
        }

        private List<ReservationItemDto> mapToReservationItemsDto(List<ReservationItem> reservationItems) {
                return reservationItems.stream()
                        .map(reservationItem -> new ReservationItemDto(
                                reservationItem.getCodeId(),
                                reservationItem.getVisitorName(),
                                reservationItem.getMovieTitle(),
                                reservationItem.getAuditoriumNumber()
                        ))
                        .collect(Collectors.toList());
        }

}
