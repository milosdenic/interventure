package md.listings.kafka;

import md.listings.config.TestConfig;
import md.listings.kafka.message.ListingMessage;
import md.listings.service.ListingService;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;

import static md.listings.data.TestData.buildListingMessage;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;

@SpringBootTest(classes = {TestConfig.class})
@DirtiesContext
@EmbeddedKafka(partitions = 1, brokerProperties = {"listeners=PLAINTEXT://localhost:9092", "port=9092"})
@TestPropertySource(properties = {
        "spring.kafka.producer.key-serializer = org.apache.kafka.common.serialization.LongSerializer",
        "spring.kafka.producer.value-serializer = org.springframework.kafka.support.serializer.JsonSerializer",
        "spring.kafka.consumer.key-deserializer = org.apache.kafka.common.serialization.LongDeserializer",
        "spring.kafka.consumer.value-deserializer = org.springframework.kafka.support.serializer.JsonDeserializer",
        "spring.kafka.consumer.auto-offset-reset=earliest"
})
public class ListingConsumerTest {

    private static final String TOPIC = "Listings";

    @Autowired
    private KafkaTemplate<Long, ListingMessage> kafkaTemplate;

    @SpyBean
    @Autowired
    private ListingService listingService;

    @Test
    public void shouldReceiveMessage() {
        // given
        ListingMessage message = buildListingMessage();
        ProducerRecord<Long, ListingMessage> record = new ProducerRecord<>(TOPIC, 12345L, message);

        // when
        kafkaTemplate.send(record);

        // then
        verify(listingService, timeout(5_000)).processMessage(message);
    }
}
