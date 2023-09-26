package md.listings.repository;

import md.listings.repository.document.Listing;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ListingRepository extends ElasticsearchRepository<Listing, String> {
}
