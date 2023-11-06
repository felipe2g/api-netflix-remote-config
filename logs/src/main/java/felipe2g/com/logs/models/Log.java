package felipe2g.com.logs.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Document(collection = "newletterLogs")
public class Log<T> {
    @Id
    private String id;
    private String action;
    private Date date = new Date();
    private T object;
    private String classType;
    public Log(String action, Date date, T object, String classType){
        this.action = action;
        this.date = date;
        this.object = object;
        this.classType = classType;
    }
}