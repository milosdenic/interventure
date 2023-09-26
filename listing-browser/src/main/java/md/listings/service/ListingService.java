package md.listings.service;

import md.listings.kafka.message.ListingMessage;

public interface ListingService {

    void processMessage(ListingMessage listingMessage);

}
