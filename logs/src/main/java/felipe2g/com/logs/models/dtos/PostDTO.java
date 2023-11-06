package felipe2g.com.logs.models.dtos;


import lombok.*;

import java.util.List;

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
}
