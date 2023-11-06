package felipe2g.com.logs.models.dtos;

import lombok.*;

import java.io.Serializable;
import java.util.List;


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
}
