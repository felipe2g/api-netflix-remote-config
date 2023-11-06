package felipe2g.com.logs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class LogsApplication {

	public static void main(String[] args) {
		SpringApplication.run(LogsApplication.class, args);
	}

}
