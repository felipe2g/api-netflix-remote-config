package felipe2g.com.newsletter.models.dto;

import felipe2g.com.newsletter.models.Post;
import felipe2g.com.newsletter.models.Tag;
import lombok.*;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class PostDTO {
    private String title;
    private String authorName;
    private String body;
    private List<TagDTO> tags;

    public PostDTO(Post post){
        this.title = post.getTitle();
        this.authorName = post.getAuthorName();
        this.body = post.getBody();
        if(post.getTags() != null){
            this.tags = post.getTags().stream().map(tag -> new TagDTO(tag)).collect(Collectors.toList());
        }
    }

    public Post toPost(){

        List<Tag> tags = null;
        if(this.tags != null){
            tags = this.tags.stream().map(tagDTO -> tagDTO.toTag()).collect(Collectors.toList());
        }
        return new Post( this.title, this.authorName, this.body, tags);
    }
}
