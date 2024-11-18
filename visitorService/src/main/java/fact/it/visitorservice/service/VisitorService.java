package fact.it.visitorservice.service;

import fact.it.visitorservice.dto.VisitorRequest;
import fact.it.visitorservice.dto.VisitorResponse;
import fact.it.visitorservice.model.Visitor;
import fact.it.visitorservice.repository.VisitorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VisitorService {

    private final VisitorRepository visitorRepository;
    public List<VisitorResponse> getAllVisitors() {
        List<Visitor> visitors = visitorRepository.findAll();
        return visitors.stream().map(this::mapToVisitorResponse).toList();
    }
    public List<VisitorResponse> getAllVisitorsByCodeId(List<String> codeId) {
        List<Visitor> visitors = visitorRepository.findByCodeIdIn(codeId);
        return visitors.stream().map(this::mapToVisitorResponse).toList();
    }
    private VisitorResponse mapToVisitorResponse(Visitor visitor) {
        return VisitorResponse.builder()
                .id(visitor.getId())
                .codeId(visitor.getCodeId())
                .name(visitor.getName())
                .email(visitor.getEmail())
                .phone(visitor.getPhone())
                .resevations(visitor.getResevations())
                .build();
    }

    public void createVisitor(VisitorRequest visitorRequest) {
        Visitor visitor = Visitor.builder()
                .codeId(visitorRequest.getCodeId())
                .name(visitorRequest.getName())
                .email(visitorRequest.getEmail())
                .phone(visitorRequest.getPhone())
                .resevations(visitorRequest.getResevations())
                .build();
        visitorRepository.save(visitor);
    }
}
