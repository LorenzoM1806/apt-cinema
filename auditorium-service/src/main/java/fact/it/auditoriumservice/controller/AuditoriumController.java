package fact.it.auditoriumservice.controller;

import fact.it.auditoriumservice.dto.AuditoriumRequest;
import fact.it.auditoriumservice.dto.AuditoriumResponse;
import fact.it.auditoriumservice.service.AuditoriumService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auditorium")
@RequiredArgsConstructor
public class AuditoriumController {

    private final AuditoriumService auditoriumService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void createAuditorium
            (@RequestBody AuditoriumRequest auditoriumRequest) {
        auditoriumService.createAuditorium(auditoriumRequest);
    }
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void updateAuditorium
            (@RequestBody AuditoriumRequest auditoriumRequest) {
        auditoriumService.updateAuditorium(auditoriumRequest);
    }
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<AuditoriumResponse> getAllAuditoriumsByCodeId
            (@RequestParam List<String> codeId) {
       return auditoriumService.getAllAuditoriumsByCodeId(codeId);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<AuditoriumResponse> getAllAuditoriums() {
        return auditoriumService.getAllAuditoriums();
    }


}
