package md.listings.service.impl;

import md.listings.service.model.SearchFilters;
import md.listings.repository.document.Listing;
import md.listings.service.SearchService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.nonNull;

@Service
public class SearchServiceImpl implements SearchService {

    private final ElasticsearchOperations elasticsearchOperations;

    public SearchServiceImpl(ElasticsearchOperations elasticsearchOperations) {
        this.elasticsearchOperations = elasticsearchOperations;
    }

    public List<Listing> getListings(SearchFilters searchFilters, Pageable pageable) {
        return getListings(new Criteria(), searchFilters, pageable);
    }

    public List<Listing> getListings(String make, SearchFilters searchFilters, Pageable pageable) {
        Criteria criteria =  new Criteria()
                .and(new Criteria("make").is(make));

        return getListings(criteria, searchFilters, pageable);
    }

    public List<Listing> getListings(String make, String model, SearchFilters searchFilters, Pageable pageable) {
        Criteria criteria =  new Criteria()
                .and(new Criteria("make").is(make))
                .and(new Criteria("model").is(model));

        return getListings(criteria, searchFilters, pageable);
    }

    public List<Listing> getListings(String make, String model, Integer year, SearchFilters searchFilters, Pageable pageable) {
        Criteria criteria = new Criteria()
                .and(new Criteria("make").is(make))
                .and(new Criteria("model").is(model))
                .and(new Criteria("year").is(year));

        return getListings(criteria, searchFilters, pageable);
    }

    private List<Listing> getListings(Criteria criteria, SearchFilters searchFilters, Pageable pageable) {
        CriteriaQuery query = buildSearchQuery(criteria, searchFilters);
        query.setPageable(pageable);

        return elasticsearchOperations.search(query, Listing.class).stream()
                .map(SearchHit::getContent)
                .toList();
    }

    private CriteriaQuery buildSearchQuery(Criteria criteria, SearchFilters searchFilters) {
        if (nonNull(searchFilters.getBodyType())) {
            criteria.and(new Criteria("bodyType").in(searchFilters.getBodyType()));
        }
        if (nonNull(searchFilters.getFuelType())) {
            criteria.and(new Criteria("fuelType").is(searchFilters.getFuelType()));
        }
        if (nonNull(searchFilters.getGearBox())) {
            criteria.and(new Criteria("gearBox").is(searchFilters.getGearBox()));
        }
        if (nonNull(searchFilters.getPriceFrom()) || nonNull(searchFilters.getPriceTo())) {
            criteria.and(new Criteria("price").between(searchFilters.getPriceFrom(), searchFilters.getPriceTo()));
        }
        if (nonNull(searchFilters.getMileageFrom()) || nonNull(searchFilters.getMileageTo())) {
            criteria.and(new Criteria("mileage").between(searchFilters.getMileageFrom(), searchFilters.getMileageTo()));
        }

        return new CriteriaQuery(criteria);
    }

}
