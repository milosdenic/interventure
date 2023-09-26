package md.listings.data;

import md.listings.kafka.message.ListingMessage;
import md.listings.kafka.message.type.UpdateType;
import md.listings.repository.document.Listing;
import md.listings.service.model.SearchFilters;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.Instant;
import java.util.List;

public class TestData {

    private TestData() {

    }

    public static Listing buildListingDocument(String id) {
        Listing listing = new Listing();
        listing.setId(id);
        listing.setCreated(Instant.ofEpochMilli(1_000_000));
        listing.setMake("BMW");
        listing.setModel("X6");
        listing.setYear(2023);
        listing.setBodyType("SUV");
        listing.setFuelType("Diesel");
        listing.setGearBox("Automatic");
        listing.setMileage(1_000);
        listing.setPower(350);
        listing.setPrice(100_000);
        listing.setUserId(12345L);

        return listing;
    }

    public static ListingMessage buildListingMessage() {
        ListingMessage listingMessage = new ListingMessage();
        listingMessage.setId(null);
        listingMessage.setMake("BMW");
        listingMessage.setModel("X6");
        listingMessage.setYear(2023);
        listingMessage.setBodyType("SUV");
        listingMessage.setFuelType("Diesel");
        listingMessage.setGearBox("Automatic");
        listingMessage.setMileage(1_000);
        listingMessage.setPower(350);
        listingMessage.setPrice(100_000);
        listingMessage.setUserId(12345L);
        listingMessage.setUpdateType(UpdateType.CREATE);
        listingMessage.setTimestamp(1_000_000L);

        return listingMessage;
    }

    public static Pageable buildPageable() {
        return PageRequest.of(1, 10);
    }

    public static SearchFilters buildSearchFilters() {
        return new SearchFilters(10_000, 20_000, null, null, "Gasoline", List.of("Sedan", "SUV"), "Manual");
    }
}
