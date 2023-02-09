package com.albgott.authservice.commons.infrastructure.bus.event.rabbitMq;

import com.albgott.authservice.commons.infrastructure.bus.event.DomainEventSubscriberInformation;

public final class RabbitMqQueueNameFormatter {
    public static String format(DomainEventSubscriberInformation information) {
        return information.formatRabbitMqQueueName();
    }

    public static String formatRetry(DomainEventSubscriberInformation information) {
        return String.format("retry.%s", format(information));
    }

    public static String formatDeadLetter(DomainEventSubscriberInformation information) {
        return String.format("dead_letter.%s", format(information));
    }
}
