package md.listings.kafka;

import md.listings.kafka.message.ListingMessage;
import md.listings.service.ListingService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

@Service
public class ListingConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(ListingConsumer.class);

    private final ListingService listingService;

    public ListingConsumer(ListingService listingService) {
        this.listingService = listingService;
    }

    @KafkaListener(topics = "Listings", groupId = "listing_group")
    @Retryable(backoff = @Backoff(value = 2_000, multiplier = 1.5, maxDelay = 15_000),
            maxAttempts = 5,
            retryFor = DataAccessException.class)
    public void listingListener(ConsumerRecord<Long, ListingMessage> record) {
        Long userId = record.key();
        ListingMessage listingMessage = record.value();
        LOGGER.info("Received listing={} for userId={}", listingMessage, userId);
        listingService.processMessage(listingMessage);
        LOGGER.info("Listing message for userId={} processed", userId);
    }
}
