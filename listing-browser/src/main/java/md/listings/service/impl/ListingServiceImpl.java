package md.listings.service.impl;

import md.listings.kafka.message.ListingMessage;
import md.listings.repository.ListingRepository;
import md.listings.repository.document.Listing;
import md.listings.service.ListingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
public class ListingServiceImpl implements ListingService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ListingServiceImpl.class);

    private final ListingRepository listingRepository;

    public ListingServiceImpl(ListingRepository listingRepository) {
        this.listingRepository = listingRepository;
    }

    public void processMessage(ListingMessage listingMessage) {
        switch (listingMessage.getUpdateType()) {
            case CREATE -> createListing(listingMessage);
            case UPDATE -> updateListing(listingMessage);
            case DELETE -> deleteListing(listingMessage.getId());
        }
    }

    private void createListing(ListingMessage listingMessage) {
        Listing listing = new Listing();
        listing.setUserId(listingMessage.getUserId());
        listing.setCreated(Instant.ofEpochMilli(listingMessage.getTimestamp()));
        updateListingDocument(listing, listingMessage);

        listingRepository.save(listing);
        LOGGER.info("Saved new listing={}", listing);
    }

    private void updateListing(ListingMessage listingMessage) {
        Optional<Listing> listingOptional = listingRepository.findById(listingMessage.getId());
        if (listingOptional.isPresent()) {
            Listing listing = listingOptional.get();
            listing.setUpdated(Instant.ofEpochMilli(listingMessage.getTimestamp()));
            updateListingDocument(listing, listingMessage);
            listingRepository.save(listing);
            LOGGER.info("Updated listing={}", listing);
        } else {
            LOGGER.error("Listing with id={} not found. Could not update", listingMessage.getId());
        }
    }

    private void deleteListing(String id) {
        Optional<Listing> listingOptional = listingRepository.findById(id);
        if (listingOptional.isPresent()) {
            Listing listing = listingOptional.get();
            listingRepository.delete(listing);
            LOGGER.info("Deleted listing={}", listing);
        } else {
            LOGGER.error("Listing with id={} not found. Could not delete", id);
        }
    }


    private void updateListingDocument(Listing listing, ListingMessage listingMessage) {
        listing.setMake(listingMessage.getMake());
        listing.setModel(listingMessage.getModel());
        listing.setYear(listingMessage.getYear());
        listing.setMileage(listingMessage.getMileage());
        listing.setPower(listingMessage.getPower());
        listing.setBodyType(listingMessage.getBodyType());
        listing.setFuelType(listingMessage.getFuelType());
        listing.setPrice(listingMessage.getPrice());
        listing.setGearBox(listingMessage.getGearBox());
    }
}
