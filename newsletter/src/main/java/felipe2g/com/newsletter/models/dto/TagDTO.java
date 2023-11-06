package felipe2g.com.newsletter.models.dto;

import felipe2g.com.newsletter.models.Tag;
import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class TagDTO implements Serializable {
    private String name;

    public TagDTO(Tag tag){
        this.name = tag.getName();
    }
    public Tag toTag(){
        var tag = new Tag();
        tag.setName(this.name);
        return tag;
    }
}
