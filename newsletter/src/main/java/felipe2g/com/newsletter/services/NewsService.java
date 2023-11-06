package felipe2g.com.newsletter.services;

import felipe2g.com.newsletter.mensages.RabbitMqSendLog;
import felipe2g.com.newsletter.models.News;
import felipe2g.com.newsletter.models.NotificationMessage;
import felipe2g.com.newsletter.models.dto.LogDTO;
import felipe2g.com.newsletter.models.dto.NewsDTO;
import felipe2g.com.newsletter.repositories.NewsRepository;
import felipe2g.com.newsletter.repositories.PostRepository;
import felipe2g.com.newsletter.repositories.TagRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NewsService {
    @Autowired
    private NewsRepository newsRepository;
    @Autowired
    private RabbitMqSendLog rabbitMqSendLog;

    @Value("${firebase.token}")
    private String tokenFirebase;

    @Autowired
    private NotificationService notificationService;

    public ResponseEntity<List<NewsDTO>> findAll() {
        var dbNews = newsRepository.findAll();
        if(dbNews.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var dbNewsDTO = dbNews.stream().map(news -> new NewsDTO(news)).collect(Collectors.toList());
        return ResponseEntity.ok(dbNewsDTO);
    }

    public ResponseEntity<NewsDTO> findOne(ObjectId id){
        if(id == null)
            return ResponseEntity.badRequest().build();

        var dbNews = newsRepository.findById(String.valueOf(id));
        if(dbNews.isEmpty())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(new NewsDTO(dbNews.get()));
    }

    public ResponseEntity<NewsDTO> save(NewsDTO newsDTO){
        if(newsDTO == null)
            return ResponseEntity.badRequest().build();
        System.out.println(newsDTO.toNews());
        var dbNews = newsRepository.save(newsDTO.toNews());



        rabbitMqSendLog.sendLog(new LogDTO<NewsDTO>("created", new NewsDTO(dbNews)));

        notificationService.sendNotification(
                new NotificationMessage(
                        tokenFirebase,
                        "SAVE",
                        "NOVA NEWSLETTER",
                        "image",
                        new HashMap<>()
                ));
        return ResponseEntity.ok(new NewsDTO(dbNews));
    }

    public ResponseEntity<NewsDTO> update(NewsDTO newsDTO){
        if(newsDTO == null)
            return ResponseEntity.badRequest().build();

        var news = newsRepository.findById(String.valueOf(newsDTO.getId()));
        if(news.isEmpty())
            return ResponseEntity.notFound().build();

        var dbNews = newsRepository.save(newsDTO.toNews());

        rabbitMqSendLog.sendLog(new LogDTO<NewsDTO>("update", new NewsDTO(dbNews)));

        return ResponseEntity.ok(new NewsDTO(dbNews));
    }

    public ResponseEntity<?> delete(ObjectId id){
        if(id == null)
            return ResponseEntity.badRequest().build();

        var dbNews = newsRepository.findById(String.valueOf(id));

        if(dbNews.isEmpty())
            return ResponseEntity.notFound().build();

        newsRepository.delete(dbNews.get());

        rabbitMqSendLog.sendLog(new LogDTO<NewsDTO>("delete", new NewsDTO(dbNews.get())));

        notificationService.sendNotification(
                new NotificationMessage(
                        tokenFirebase,
                        "DELETE",
                        "NEWS LETTER DELETADA",
                        "image",
                        new HashMap<>()
                ));

        return ResponseEntity.ok().build();
    }
}
