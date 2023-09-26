package md.listings.service;

import md.listings.kafka.message.ListingMessage;
import md.listings.kafka.message.type.UpdateType;
import md.listings.repository.ListingRepository;
import md.listings.repository.document.Listing;
import md.listings.service.impl.ListingServiceImpl;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.Optional;

import static md.listings.data.TestData.buildListingDocument;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

public class ListingServiceTest {

    private final ListingRepository listingRepository = mock(ListingRepository.class);

    private final ListingService listingService = new ListingServiceImpl(listingRepository);

    @Test
    void shouldSaveNewListing() {
        // given
        ListingMessage listingMessage = buildListingMessage(UpdateType.CREATE, null);
        Listing expected = buildListingDocument(null);

        // when
        listingService.processMessage(listingMessage);

        // then
        verify(listingRepository).save(expected);
        verifyNoMoreInteractions(listingRepository);
    }

    @Test
    void shouldUpdateListing() {
        // given
        String id = "someRandomListingId";
        ListingMessage listingMessage = buildListingMessage(UpdateType.UPDATE, id);
        Listing existing = buildListingDocument(id);
        when(listingRepository.findById(id)).thenReturn(Optional.of(existing));
        Listing expected = buildListingDocument(id);
        expected.setUpdated(Instant.ofEpochMilli(2_000_000L));
        expected.setPrice(90_000);

        // when
        listingService.processMessage(listingMessage);

        // then
        verify(listingRepository).findById(id);
        verify(listingRepository).save(expected);
        verifyNoMoreInteractions(listingRepository);
    }

    @Test
    void shouldNotUpdateListingIfNotExist() {
        // given
        String id = "someRandomListingId";
        ListingMessage listingMessage = buildListingMessage(UpdateType.UPDATE, id);
        when(listingRepository.findById(id)).thenReturn(Optional.empty());

        // when
        listingService.processMessage(listingMessage);

        // then
        verify(listingRepository).findById(id);
        verifyNoMoreInteractions(listingRepository);
    }

    @Test
    void shouldDeleteListing() {
        // given
        String id = "someRandomListingId";
        ListingMessage listingMessage = buildListingMessage(UpdateType.DELETE, id);
        Listing expected = buildListingDocument(null);
        when(listingRepository.findById(id)).thenReturn(Optional.of(expected));

        // when
        listingService.processMessage(listingMessage);

        // then
        verify(listingRepository).findById(id);
        verify(listingRepository).delete(expected);
        verifyNoMoreInteractions(listingRepository);
    }

    @Test
    void shouldNotDeleteListingIfNotExist() {
        // given
        String id = "someRandomListingId";
        ListingMessage listingMessage = buildListingMessage(UpdateType.DELETE, id);
        when(listingRepository.findById(id)).thenReturn(Optional.empty());

        // when
        listingService.processMessage(listingMessage);

        // then
        verify(listingRepository).findById(id);
        verifyNoMoreInteractions(listingRepository);
    }


    private ListingMessage buildListingMessage(UpdateType updateType, String id) {
        ListingMessage listingMessage = new ListingMessage();
        listingMessage.setId(id);
        listingMessage.setMake("BMW");
        listingMessage.setModel("X6");
        listingMessage.setYear(2023);
        listingMessage.setBodyType("SUV");
        listingMessage.setFuelType("Diesel");
        listingMessage.setGearBox("Automatic");
        listingMessage.setMileage(1_000);
        listingMessage.setPower(350);
        listingMessage.setPrice(updateType == UpdateType.CREATE ? 100_000 : 90_000);
        listingMessage.setUserId(12345L);
        listingMessage.setUpdateType(updateType);
        listingMessage.setTimestamp(updateType == UpdateType.CREATE ? 1_000_000L : 2_000_000L);

        return listingMessage;
    }
}
