package felipe2g.com.newsletter.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class NotificationMessage implements Serializable {
    private String recipientToken;
    private String title;
    private String body;
    private String image;
    private Map<String, String> data;

}