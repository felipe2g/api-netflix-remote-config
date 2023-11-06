package felipe2g.com.logs.models.dtos;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class TagDTO implements Serializable {
    private String name;
}
