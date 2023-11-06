package felipe2g.com.newsletter.repositories;

import felipe2g.com.newsletter.models.News;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepository extends MongoRepository<News, String>{
}
