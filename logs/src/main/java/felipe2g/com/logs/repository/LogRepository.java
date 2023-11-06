package felipe2g.com.logs.repository;

import felipe2g.com.logs.models.Log;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRepository extends MongoRepository<Log, String> {
}
