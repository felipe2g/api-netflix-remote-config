package felipe2g.com.newsletter.mensages;

import felipe2g.com.newsletter.models.dto.LogDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqSendLog {
    @Value("${newletter.rabbitmq.exchange}")
    private String exchange;
    @Value("${newletter.rabbitmq.routingKey}")
    private String routingKey;
    @Value("${newletter.rabbitmq.queue}")
    private String queue;

    public final RabbitTemplate rabbitTemplate;

    @Autowired
    public RabbitMqSendLog(RabbitTemplate rabbitTemplate){
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendLog(LogDTO logDTO){
        rabbitTemplate.execute(channel -> {
            channel.exchangeDeclare(exchange, "direct", true);
            channel.queueDeclare(queue, true, false, false, null);
            channel.queueBind(queue, exchange, routingKey);
            return null;
        });
        rabbitTemplate.convertAndSend(exchange, routingKey, logDTO);
    }
}
