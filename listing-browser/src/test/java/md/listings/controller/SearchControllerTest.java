package md.listings.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import md.listings.repository.document.Listing;
import md.listings.service.SearchService;
import md.listings.service.model.SearchFilters;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static md.listings.data.TestData.buildListingDocument;
import static md.listings.data.TestData.buildPageable;
import static md.listings.data.TestData.buildSearchFilters;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class SearchControllerTest {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    private static final String RESPONSE = """
            [
               {
                  "id":"listingId",
                  "userId":12345,
                  "make":"BMW",
                  "model":"X6",
                  "year":2023,
                  "mileage":1000,
                  "power":350,
                  "fuelType":"Diesel",
                  "gearBox":"Automatic",
                  "bodyType":"SUV",
                  "price":100000,
                  "created":"1970-01-01T00:16:40.000+0000",
                  "updated":null
               }
            ]
            """;

    private final SearchService searchService = mock(SearchService.class);

    private final SearchController searchController = new SearchController(searchService);

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(searchController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .build();
        MAPPER.findAndRegisterModules();
    }

    @Test
    void shouldReturnListings() throws Exception {
        // given
        SearchFilters searchFilters = buildSearchFilters();
        Pageable pageable = buildPageable();
        Listing listing = buildListingDocument("listingId");
        when(searchService.getListings(any(), any())).thenReturn(List.of(listing));
        MockHttpServletRequestBuilder getRequest = get("/listings")
                .param("price_from", "10000")
                .param("price_to", "20000")
                .param("fuel_type", "Gasoline")
                .param("body_type", "Sedan,SUV")
                .param("gear_box", "Manual")
                .param("page", "1")
                .param("size", "10");

        // when
        mockMvc.perform(getRequest)
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().json(RESPONSE));

        // then
        verify(searchService).getListings(searchFilters, pageable);
    }

    @Test
    void shouldReturnListingsForMake() throws Exception {
        // given
        String make = "BMW";
        SearchFilters searchFilters = buildSearchFilters();
        Pageable pageable = buildPageable();
        Listing listing = buildListingDocument("listingId");
        when(searchService.getListings(any(), any(), any())).thenReturn(List.of(listing));
        MockHttpServletRequestBuilder getRequest = get("/listings/{make}", make)
                .param("price_from", "10000")
                .param("price_to", "20000")
                .param("fuel_type", "Gasoline")
                .param("body_type", "Sedan,SUV")
                .param("gear_box", "Manual")
                .param("page", "1")
                .param("size", "10");

        // when
        mockMvc.perform(getRequest)
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().json(RESPONSE));

        // then
        verify(searchService).getListings(make, searchFilters, pageable);
    }

    @Test
    void shouldReturnListingsForMakeAndModel() throws Exception {
        // given
        String make = "BMW";
        String model = "X6";
        SearchFilters searchFilters = buildSearchFilters();
        Pageable pageable = buildPageable();
        Listing listing = buildListingDocument("listingId");
        when(searchService.getListings(any(), any(), any(), any())).thenReturn(List.of(listing));
        MockHttpServletRequestBuilder getRequest = get("/listings/{make}/{model}", make, model)
                .param("price_from", "10000")
                .param("price_to", "20000")
                .param("fuel_type", "Gasoline")
                .param("body_type", "Sedan,SUV")
                .param("gear_box", "Manual")
                .param("page", "1")
                .param("size", "10");

        // when
        mockMvc.perform(getRequest)
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().json(RESPONSE));

        // then
        verify(searchService).getListings(make, model, searchFilters, pageable);
    }

    @Test
    void shouldReturnListingsForMakeAndModelAndYear() throws Exception {
        // given
        String make = "BMW";
        String model = "X6";
        Integer year = 2023;
        SearchFilters searchFilters = buildSearchFilters();
        Pageable pageable = buildPageable();
        Listing listing = buildListingDocument("listingId");
        when(searchService.getListings(any(), any(), any(), any(), any())).thenReturn(List.of(listing));
        MockHttpServletRequestBuilder getRequest = get("/listings/{make}/{model}/{year}", make, model, year)
                .param("price_from", "10000")
                .param("price_to", "20000")
                .param("fuel_type", "Gasoline")
                .param("body_type", "Sedan,SUV")
                .param("gear_box", "Manual")
                .param("page", "1")
                .param("size", "10");

        // when
        mockMvc.perform(getRequest)
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().json(RESPONSE));

        // then
        verify(searchService).getListings(make, model, year, searchFilters, pageable);
    }
}
