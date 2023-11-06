package felipe2g.com.newsletter.models.dto;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class LogDTO<T> implements Serializable {
    private String action;
    private Date date = new Date();
    private T object;
    private String classType;

    public LogDTO(String action, T object){
        this.action = action;
        this.object = object;
        this.classType = object.getClass().toString();
    }
}
