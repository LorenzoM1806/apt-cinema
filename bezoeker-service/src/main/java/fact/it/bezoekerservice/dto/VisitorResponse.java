package fact.it.bezoekerservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VisitorResponse {
    private String id;
    private String codeId;
    private String name;
    private String email;
    private String phone;
    private List<String> resevations;
}
