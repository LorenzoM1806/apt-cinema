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
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;
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
                Reservation reservation = new Reservation();
                reservation.setCodeId(UUID.randomUUID().toString());

                List<ReservationItem> reservationItems = reservationRequest.getReservationItemDtoList()
                        .stream()
                        .map(this::mapToReservationItem)
                        .toList();

                reservation.setReservationItemsList(reservationItems);

                List<String> codeIds = reservation.getReservationItemsList().stream()
                        .map(ReservationItem::getCodeId)
                        .toList();

                //Fetch responses
                AuditoriumResponse[] auditoriumResponseArray = webClient.get()
                        .uri("http://" + auditoriumServiceBaseUrl + "/api/auditorium",
                                uriBuilder -> uriBuilder.queryParam("codeId", codeIds).build())
                        .retrieve()
                        .bodyToMono(AuditoriumResponse[].class)
                        .block();

                MovieResponse[] movieResponseArray = webClient.get()
                        .uri("http://" + movieServiceBaseUrl + "/api/movie",
                                uriBuilder -> uriBuilder.queryParam("codeId", codeIds).build())
                        .retrieve()
                        .bodyToMono(MovieResponse[].class)
                        .block();

                VisitorResponse[] visitorResponseArray = webClient.get()
                        .uri("http://" + visitorServiceBaseUrl + "/api/visitor",
                                uriBuilder -> uriBuilder.queryParam("codeId", codeIds).build())
                        .retrieve()
                        .bodyToMono(VisitorResponse[].class)
                        .block();

                //Map responses to ReservationItems
                Map<String, AuditoriumResponse> auditoriumResponseMap = Arrays.stream(auditoriumResponseArray)
                        .collect(Collectors.toMap(AuditoriumResponse::getCodeId, response -> response));

                Map<String, MovieResponse> movieResponseMap = Arrays.stream(movieResponseArray)
                        .collect(Collectors.toMap(MovieResponse::getCodeId, response -> response));

                Map<String, VisitorResponse> visitorResponseMap = Arrays.stream(visitorResponseArray)
                        .collect(Collectors.toMap(VisitorResponse::getCodeId, response -> response));


                reservationItems.forEach(item -> {
                        item.setAuditoriumNumber(
                                auditoriumResponseMap.get(item.getAuditoriumNumber()).getAuditoriumNumber().toString());
                        item.setMovieTitle(
                                movieResponseMap.get(item.getMovieTitle()).getTitle().toString());
                        item.setVisitorName(
                                visitorResponseMap.get(item.getVisitorName()).getName());

                });

                reservationRepository.save(reservation);

                return true;
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
                reservationItem.setVisitorName(reservationItemDto.getVisitorName());
                reservationItem.setAuditoriumNumber(reservationItemDto.getAuditoriumNumber());
                reservationItem.setMovieTitle(reservationItemDto.getMovieTitle());
                return reservationItem;
        }

        private List<ReservationItemDto> mapToReservationItemsDto(List<ReservationItem> reservationItems) {
                return reservationItems.stream()
                        .map(reservationItem -> new ReservationItemDto(
                                reservationItem.getId(),
                                reservationItem.getCodeId(),
                                reservationItem.getVisitorName(),
                                reservationItem.getMovieTitle(),
                                reservationItem.getAuditoriumNumber()
                        ))
                        .collect(Collectors.toList());
        }

}
