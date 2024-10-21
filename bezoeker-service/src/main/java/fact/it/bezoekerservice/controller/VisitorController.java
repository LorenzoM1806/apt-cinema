package fact.it.bezoekerservice.controller;

import fact.it.bezoekerservice.dto.VisitorRequest;
import fact.it.bezoekerservice.dto.VisitorResponse;
import fact.it.bezoekerservice.service.VisitorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/visitor")
@RequiredArgsConstructor
public class VisitorController {

    private final VisitorService visitorService;

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<VisitorResponse> getAllVisitors() { return visitorService.getAllVisitors();}

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<VisitorResponse> getAllVisitorsByCodeId
            (@RequestParam List<String> codeId) {
        return visitorService.getAllVisitorsByCodeId(codeId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void createVisitor
            (@RequestBody VisitorRequest visitorRequest) {
        visitorService.createVisitor(visitorRequest);
    }
}

