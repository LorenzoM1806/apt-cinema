package fact.it.auditoriumservice.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "auditorium")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Auditorium {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String codeId;
    private Integer auditoriumNumber;
    private Integer auditoriumType;
}