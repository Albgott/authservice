package com.albgott.authservice.shared.infrastructure.event.rabbitMq;

import com.albgott.authservice.shared.domain.event.DomainEvent;
import com.albgott.authservice.shared.domain.event.EventBus;
import org.springframework.amqp.AmqpException;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Primary
@Service("rabbit")
public class RabbitMqEventBus implements EventBus {
    private final RabbitMqPublisher publisher;
    private final String exchangeName;

    public RabbitMqEventBus(RabbitMqPublisher publisher) {
        this.publisher = publisher;
        this.exchangeName = "domain_events";
    }

    @Override
    public void publish(List<DomainEvent> events) {
        events.forEach(this::publish);
    }

    private void publish(DomainEvent event) {
        try{
            this.publisher.publish(event, exchangeName);
        }catch (AmqpException error){
            //TODO: Add fail over pusblisher
        }
    }
}
