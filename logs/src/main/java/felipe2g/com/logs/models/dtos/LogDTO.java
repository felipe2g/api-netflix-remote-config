package felipe2g.com.logs.models.dtos;

import felipe2g.com.logs.models.Log;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class LogDTO<T> implements Serializable {

    private String id;
    private String action;
    private Date date = new Date();
    private T object;
    private String classType;

    public LogDTO(String action, Date date, T object, String classType){
        this.action = action;
        this.date = date;
        this.object = object;
        this.classType = classType;
    }
    public LogDTO(Log log){
        this.id = log.getId();
        this.action = log.getAction();
        this.date = log.getDate();
        this.classType = log.getClassType();
        this.object = (T)log.getObject();
    }

    public Log toLog(){
        String id;

        var log = new Log<T>();
        log.setAction(this.action);
        log.setDate(this.date);
        log.setObject(this.object);

        if(this.id != null && !this.id.isBlank())
            log.setId(this.getId());

        if(this.classType != null && !this.classType.isBlank())
            log.setClassType(this.classType);

        return log;
    }
}
