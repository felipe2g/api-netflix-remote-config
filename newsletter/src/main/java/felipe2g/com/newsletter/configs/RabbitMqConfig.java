package felipe2g.com.newsletter.configs;

import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.core.Exchange;

@Configuration
public class RabbitMqConfig {
    @Value("${newletter.rabbitmq.exchange}")
    private String exchange;

    @Bean
    public Exchange declareExchange(){
        return ExchangeBuilder
                .directExchange(exchange)
                .durable(true)
                .build();
    }
    @Bean
    public MessageConverter jsonMessageConverter(){
        return new Jackson2JsonMessageConverter();
    }
}
