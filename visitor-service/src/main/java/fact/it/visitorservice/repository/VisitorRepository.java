package fact.it.visitorservice.repository;

import fact.it.visitorservice.model.Visitor;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface VisitorRepository extends MongoRepository<Visitor, String> {
    List<Visitor> findByCodeIdIn(String idCode);
}
