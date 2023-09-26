package md.listings.kafka;


import md.listings.controller.request.ListingRequest;
import md.listings.kafka.message.UpdateType;
import md.listings.kafka.message.ListingMessage;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {

    private static final String TOPIC = "Listings";

    private final KafkaTemplate<Long, ListingMessage> kafkaTemplate;

    public KafkaProducer(KafkaTemplate<Long, ListingMessage> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void createListing(ListingRequest listingRequest) {
        ListingMessage message = buildMessage(UpdateType.CREATE, null, listingRequest);
        kafkaTemplate.send(TOPIC, listingRequest.getUserId(), message);
    }

    public void updateListing(String id, ListingRequest listingRequest) {
        ListingMessage message = buildMessage(UpdateType.UPDATE, id, listingRequest);
        kafkaTemplate.send(TOPIC, listingRequest.getUserId(), message);
    }

    public void deleteListing(Long userId, String id) {
        ListingMessage message = new ListingMessage(UpdateType.DELETE, id, userId);
        kafkaTemplate.send(TOPIC, userId, message);
    }

    private ListingMessage buildMessage(UpdateType updateType, String id, ListingRequest listingRequest) {
        ListingMessage listing = new ListingMessage(updateType, id, listingRequest.getUserId());
        listing.setMake(listingRequest.getMake());
        listing.setModel(listingRequest.getModel());
        listing.setYear(listingRequest.getYear());
        listing.setBodyType(listingRequest.getBodyType());
        listing.setFuelType(listingRequest.getFuelType());
        listing.setMileage(listingRequest.getMileage());
        listing.setPower(listingRequest.getPower());
        listing.setGearBox(listingRequest.getGearBox());
        listing.setPrice(listingRequest.getPrice());

        return listing;
    }
}
