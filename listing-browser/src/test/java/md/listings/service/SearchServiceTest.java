package md.listings.service;

import md.listings.repository.document.Listing;
import md.listings.service.impl.SearchServiceImpl;
import md.listings.service.model.SearchFilters;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static md.listings.data.TestData.buildListingDocument;
import static md.listings.data.TestData.buildPageable;
import static md.listings.data.TestData.buildSearchFilters;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

public class SearchServiceTest {

    private static final Listing LISTING = buildListingDocument("listingId");

    private final ElasticsearchOperations elasticsearchOperations = mock(ElasticsearchOperations.class);

    private final SearchService searchService = new SearchServiceImpl(elasticsearchOperations);

    @BeforeEach
    public void mockElasticsearchResult() {
        SearchHits searchHits = mock(SearchHits.class);
        SearchHit searchHit = mock(SearchHit.class);
        when(searchHits.stream()).thenReturn(List.of(searchHit).stream());
        when(searchHit.getContent()).thenReturn(LISTING);
        when(elasticsearchOperations.search(any(CriteriaQuery.class), eq(Listing.class))).thenReturn(searchHits);
    }

    @Test
    void shouldReturnListings() {
        // given
        SearchFilters searchFilters = buildSearchFilters();
        Pageable pageable = buildPageable();
        ArgumentCaptor<CriteriaQuery> captor = ArgumentCaptor.forClass(CriteriaQuery.class);

        // when
        List<Listing> result = searchService.getListings(searchFilters, pageable);

        // then
        assertEquals(List.of(LISTING), result);
        verify(elasticsearchOperations).search(captor.capture(), eq(Listing.class));
        verifyNoMoreInteractions(elasticsearchOperations);

        Pageable capturedPageable = captor.getValue().getPageable();
        assertEquals(pageable, capturedPageable);

        List<Criteria> capturedCriteria = captor.getValue().getCriteria().getCriteriaChain();
        assertEquals(4, capturedCriteria.size());

        // validate if criteria is correct
        assertEquals("bodyType", Objects.requireNonNull(capturedCriteria.get(0).getField()).getName());
        Optional<Criteria.CriteriaEntry> entry0 = capturedCriteria.get(0).getQueryCriteriaEntries().stream().findFirst();
        assertTrue(entry0.isPresent());
        assertEquals("IN", entry0.get().getKey().name());
        assertEquals(List.of("Sedan", "SUV"), entry0.get().getValue());

        assertEquals("fuelType", Objects.requireNonNull(capturedCriteria.get(1).getField()).getName());
        Optional<Criteria.CriteriaEntry> entry1 = capturedCriteria.get(1).getQueryCriteriaEntries().stream().findFirst();
        assertTrue(entry1.isPresent());
        assertEquals("EQUALS", entry1.get().getKey().name());
        assertEquals("Gasoline", entry1.get().getValue());

        assertEquals("gearBox", Objects.requireNonNull(capturedCriteria.get(2).getField()).getName());
        Optional<Criteria.CriteriaEntry> entry2 = capturedCriteria.get(2).getQueryCriteriaEntries().stream().findFirst();
        assertTrue(entry2.isPresent());
        assertEquals("EQUALS", entry2.get().getKey().name());
        assertEquals("Manual", entry2.get().getValue());

        assertEquals("price", Objects.requireNonNull(capturedCriteria.get(3).getField()).getName());
        Optional<Criteria.CriteriaEntry> entry3 = capturedCriteria.get(3).getQueryCriteriaEntries().stream().findFirst();
        assertTrue(entry3.isPresent());
        assertEquals("BETWEEN", entry3.get().getKey().name());
        assertEquals(10_000, ((Object[]) entry3.get().getValue())[0]);
        assertEquals(20_000, ((Object[]) entry3.get().getValue())[1]);
    }

    @Test
    void shouldReturnListingsByMake() {
        // given
        String make = "BMW";
        SearchFilters searchFilters = buildSearchFilters();
        Pageable pageable = buildPageable();
        ArgumentCaptor<CriteriaQuery> captor = ArgumentCaptor.forClass(CriteriaQuery.class);

        // when
        List<Listing> result = searchService.getListings(make, searchFilters, pageable);

        // then
        assertEquals(List.of(LISTING), result);
        verify(elasticsearchOperations).search(captor.capture(), eq(Listing.class));
        verifyNoMoreInteractions(elasticsearchOperations);

        Pageable capturedPageable = captor.getValue().getPageable();
        assertEquals(pageable, capturedPageable);

        List<Criteria> capturedCriteria = captor.getValue().getCriteria().getCriteriaChain();
        assertEquals(5, capturedCriteria.size());

        // validate if make part of criteria is correct, rest of it is tested in the first test
        assertEquals("make", Objects.requireNonNull(capturedCriteria.get(0).getField()).getName());
        Optional<Criteria.CriteriaEntry> entry0 = capturedCriteria.get(0).getQueryCriteriaEntries().stream().findFirst();
        assertTrue(entry0.isPresent());
        assertEquals("EQUALS", entry0.get().getKey().name());
        assertEquals(make, entry0.get().getValue());
    }

    @Test
    void shouldReturnListingsByMakeAndModel() {
        // given
        String make = "BMW";
        String model = "X6";
        SearchFilters searchFilters = buildSearchFilters();
        Pageable pageable = buildPageable();
        ArgumentCaptor<CriteriaQuery> captor = ArgumentCaptor.forClass(CriteriaQuery.class);

        // when
        List<Listing> result = searchService.getListings(make, model, searchFilters, pageable);

        // then
        assertEquals(List.of(LISTING), result);
        verify(elasticsearchOperations).search(captor.capture(), eq(Listing.class));
        verifyNoMoreInteractions(elasticsearchOperations);

        Pageable capturedPageable = captor.getValue().getPageable();
        assertEquals(pageable, capturedPageable);

        List<Criteria> capturedCriteria = captor.getValue().getCriteria().getCriteriaChain();
        assertEquals(6, capturedCriteria.size());

        // validate if make and model part of criteria is correct, rest of it is tested in the first test
        assertEquals("make", Objects.requireNonNull(capturedCriteria.get(0).getField()).getName());
        Optional<Criteria.CriteriaEntry> entry0 = capturedCriteria.get(0).getQueryCriteriaEntries().stream().findFirst();
        assertTrue(entry0.isPresent());
        assertEquals("EQUALS", entry0.get().getKey().name());
        assertEquals(make, entry0.get().getValue());

        assertEquals("model", Objects.requireNonNull(capturedCriteria.get(1).getField()).getName());
        Optional<Criteria.CriteriaEntry> entry1 = capturedCriteria.get(1).getQueryCriteriaEntries().stream().findFirst();
        assertTrue(entry1.isPresent());
        assertEquals("EQUALS", entry1.get().getKey().name());
        assertEquals(model, entry1.get().getValue());
    }

    @Test
    void shouldReturnListingsByMakeAndModelAndYear() {
        // given
        String make = "BMW";
        String model = "X6";
        Integer year = 2023;
        SearchFilters searchFilters = buildSearchFilters();
        Pageable pageable = buildPageable();
        ArgumentCaptor<CriteriaQuery> captor = ArgumentCaptor.forClass(CriteriaQuery.class);

        // when
        List<Listing> result = searchService.getListings(make, model, year, searchFilters, pageable);

        // then
        assertEquals(List.of(LISTING), result);
        verify(elasticsearchOperations).search(captor.capture(), eq(Listing.class));
        verifyNoMoreInteractions(elasticsearchOperations);

        Pageable capturedPageable = captor.getValue().getPageable();
        assertEquals(pageable, capturedPageable);

        List<Criteria> capturedCriteria = captor.getValue().getCriteria().getCriteriaChain();
        assertEquals(7, capturedCriteria.size());

        // validate if make, model and year part of criteria is correct, rest of it is tested in the first test
        assertEquals("make", Objects.requireNonNull(capturedCriteria.get(0).getField()).getName());
        Optional<Criteria.CriteriaEntry> entry0 = capturedCriteria.get(0).getQueryCriteriaEntries().stream().findFirst();
        assertTrue(entry0.isPresent());
        assertEquals("EQUALS", entry0.get().getKey().name());
        assertEquals(make, entry0.get().getValue());

        assertEquals("model", Objects.requireNonNull(capturedCriteria.get(1).getField()).getName());
        Optional<Criteria.CriteriaEntry> entry1 = capturedCriteria.get(1).getQueryCriteriaEntries().stream().findFirst();
        assertTrue(entry1.isPresent());
        assertEquals("EQUALS", entry1.get().getKey().name());
        assertEquals(model, entry1.get().getValue());

        assertEquals("year", Objects.requireNonNull(capturedCriteria.get(2).getField()).getName());
        Optional<Criteria.CriteriaEntry> entry2 = capturedCriteria.get(2).getQueryCriteriaEntries().stream().findFirst();
        assertTrue(entry2.isPresent());
        assertEquals("EQUALS", entry2.get().getKey().name());
        assertEquals(year, entry2.get().getValue());
    }


}
