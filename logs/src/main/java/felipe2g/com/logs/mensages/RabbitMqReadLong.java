package felipe2g.com.logs.mensages;

import felipe2g.com.logs.models.dtos.LogDTO;
import felipe2g.com.logs.services.LogService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqReadLong {
    @Autowired
    private LogService logService;

    @RabbitListener(queues = "${newletter.rabbitmq.queue}")
    public void receiveLog(@Payload LogDTO logDTO){
        if(logDTO != null)
            logService.save(logDTO);
    }
}
