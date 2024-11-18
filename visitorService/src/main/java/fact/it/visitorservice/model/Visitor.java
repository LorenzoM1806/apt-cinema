package fact.it.visitorservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(value = "bezoeker")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Visitor {
    private String id;
    private String codeId;
    private String name;
    private String email;
    private String phone;
    private List<String> resevations;
}
