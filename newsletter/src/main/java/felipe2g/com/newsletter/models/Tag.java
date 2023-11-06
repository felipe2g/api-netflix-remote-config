package felipe2g.com.newsletter.models;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Tag {
    private String name;
}
