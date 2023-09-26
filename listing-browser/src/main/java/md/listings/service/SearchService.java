package md.listings.service;

import md.listings.service.model.SearchFilters;
import md.listings.repository.document.Listing;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SearchService {

    List<Listing> getListings(SearchFilters searchFilters, Pageable pageable);

    List<Listing> getListings(String make, SearchFilters searchFilters, Pageable pageable);

    List<Listing> getListings(String make, String model, SearchFilters searchFilters, Pageable pageable);

    List<Listing> getListings(String make, String model, Integer year, SearchFilters searchFilters, Pageable pageable);
}
