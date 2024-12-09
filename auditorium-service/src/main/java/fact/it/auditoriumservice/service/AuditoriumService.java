package fact.it.auditoriumservice.service;

import fact.it.auditoriumservice.dto.AuditoriumRequest;
import fact.it.auditoriumservice.dto.AuditoriumResponse;
import fact.it.auditoriumservice.model.Auditorium;
import fact.it.auditoriumservice.repository.AuditoriumRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuditoriumService {
    private final AuditoriumRepository auditoriumRepository;

    public List<AuditoriumResponse> getAllAuditoriums() {
        List<Auditorium> movieList = auditoriumRepository.findAll();
        return movieList.stream().map(this::mapToAuditoriumResponse).toList();
    }

    public List<AuditoriumResponse> getAllAuditoriumsByCodeId(List<String> codeId) {
        List<Auditorium> movieList = auditoriumRepository.findByCodeIdIn(codeId);
        return movieList.stream().map(this::mapToAuditoriumResponse).toList();
    }

    public void updateAuditorium(AuditoriumRequest auditoriumRequest){
        List<String> auditoriumCodeIds = new ArrayList<>();
        auditoriumCodeIds.add(auditoriumRequest.getCodeId());
        if(auditoriumRepository.findByCodeIdIn(auditoriumCodeIds).stream().findFirst().isPresent()) {
            Auditorium auditorium = auditoriumRepository.findByCodeIdIn(auditoriumCodeIds).stream().findFirst().get();
            auditorium.setAuditoriumNumber(auditoriumRequest.getAuditoriumNumber());
            auditorium.setAuditoriumType(auditoriumRequest.getAuditoriumType());
            auditoriumRepository.save(auditorium);
        }
    }

    private AuditoriumResponse mapToAuditoriumResponse(Auditorium auditorium) {
        return AuditoriumResponse.builder()
                .id(auditorium.getId())
                .codeId(auditorium.getCodeId())
                .auditoriumNumber(auditorium.getAuditoriumNumber())
                .auditoriumType(auditorium.getAuditoriumType())
                .build();
    }

    public void createAuditorium(AuditoriumRequest auditoriumRequest){
        Auditorium auditorium = Auditorium.builder()
                .codeId(auditoriumRequest.getCodeId())
                .auditoriumNumber(auditoriumRequest.getAuditoriumNumber())
                .auditoriumType(auditoriumRequest.getAuditoriumType())
                .build();

        auditoriumRepository.save(auditorium);
    }
}
