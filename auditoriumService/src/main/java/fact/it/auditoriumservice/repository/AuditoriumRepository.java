package fact.it.auditoriumservice.repository;


import fact.it.auditoriumservice.model.Auditorium;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuditoriumRepository extends JpaRepository<Auditorium, String> {
    List<Auditorium> findByCodeIdIn(List<String> codeId);
}
