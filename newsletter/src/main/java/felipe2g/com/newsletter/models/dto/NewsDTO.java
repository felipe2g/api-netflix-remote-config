package felipe2g.com.newsletter.models.dto;

import felipe2g.com.newsletter.models.News;
import felipe2g.com.newsletter.models.Post;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class NewsDTO implements Serializable {
    private String id;
    private String title;
    private String date;
    private String editorName;
    private List<PostDTO> posts;

    public NewsDTO(News news){
        if(news.getId() != null)
            this.id = news.getId().toString();

        this.title = news.getTitle();
        this.date = news.getDate();
        this.editorName = news.getEditorName();

        if(news.getPosts() != null){
            this.posts = news.getPosts().stream().map(post -> new PostDTO(post)).collect(Collectors.toList());
        }
    }

    public News toNews(){
        ObjectId id = null;
        if(this.id != null)
            id = new ObjectId(this.id);

        List<Post> posts = null;
        if(this.posts != null){
            posts = this.posts.stream().map(postDTO -> postDTO.toPost()).collect(Collectors.toList());
        }
        return new News(id, this.title, this.date, this.editorName, posts);
    }
}
