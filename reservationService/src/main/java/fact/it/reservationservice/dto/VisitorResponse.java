package fact.it.reservationservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VisitorResponse {
    private String id;
    private String codeId;
    private String name;
    private String email;
    private String phone;
}
