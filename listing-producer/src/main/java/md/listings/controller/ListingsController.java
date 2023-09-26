package md.listings.controller;


import md.listings.controller.request.ListingRequest;
import md.listings.domain.Cars;
import md.listings.kafka.KafkaProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.security.SecureRandom;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping(path = "/listings")
public class ListingsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ListingsController.class);

    private final KafkaProducer kafkaProducer;

    public ListingsController(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    @PostMapping
    public String addListing(@RequestBody ListingRequest listingRequest) {
        kafkaProducer.createListing(listingRequest);
        LOGGER.info("Added new listing={}", listingRequest);
        return "Added new listing=" + listingRequest.toString();
    }

    @PutMapping(path = "/{id}")
    public String updateListing(@PathVariable String id, @RequestBody ListingRequest listingRequest) {
        kafkaProducer.updateListing(id, listingRequest);
        LOGGER.info("Updated listing={} with id={}", listingRequest, id);
        return "Updated listing with id=" + id + " -> " + listingRequest.toString();
    }

    @DeleteMapping(path = "/{userId}/{id}")
    public String deleteListing(@PathVariable Long userId, @PathVariable String id) {
        kafkaProducer.deleteListing(userId, id);
        LOGGER.info("Deleted listing with id={} for userId={}", id, userId);
        return "Deleted listing with id=" + id;
    }

    @PostMapping(path = "/random")
    public String addRandomListing() {
        ListingRequest listingRequest = generateRandomCar();
        kafkaProducer.createListing(listingRequest);
        LOGGER.info("Added random new listing={}", listingRequest);
        return "Added new listing: " + listingRequest.toString();
    }

    private ListingRequest generateRandomCar() {
        ListingRequest listingRequest = new ListingRequest();
        Random random = new SecureRandom();

        List<String> makes = Cars.MODELS.keySet().stream().toList();
        String make = makes.get(random.nextInt(makes.size()));
        List<String> models = Cars.MODELS.get(make);
        String model = models.get(random.nextInt(models.size()));

        listingRequest.setMake(make);
        listingRequest.setModel(model);
        listingRequest.setYear(2000 + random.nextInt(23));
        listingRequest.setMileage(random.nextInt(200_000));
        listingRequest.setPower(50 + random.nextInt(500));
        listingRequest.setFuelType(Cars.FUEL_TYPE.get(random.nextInt(Cars.FUEL_TYPE.size())));
        listingRequest.setGearBox(Cars.GEARBOX.get(random.nextInt(Cars.GEARBOX.size())));
        listingRequest.setBodyType(Cars.BODY_TYPE.get(random.nextInt(Cars.BODY_TYPE.size())));
        listingRequest.setPrice(10_000 + random.nextInt(50_000));
        listingRequest.setUserId(1L + random.nextInt(10));

        return listingRequest;
    }
}
