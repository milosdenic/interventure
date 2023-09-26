package md.listings.controller;

import jakarta.validation.constraints.Min;
import md.listings.repository.document.Listing;
import md.listings.service.SearchService;
import md.listings.service.model.SearchFilters;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "listings", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class SearchController {

    private final SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping
    public List<Listing> getListings(@RequestParam(value = "price_from", required = false) @Min(0) Integer priceFrom,
                                     @RequestParam(value = "price_to", required = false) @Min(0) Integer priceTo,
                                     @RequestParam(value = "mileage_from", required = false) @Min(0) Integer mileageFrom,
                                     @RequestParam(value = "mileage_to", required = false) @Min(0) Integer mileageTo,
                                     @RequestParam(value = "fuel_type", required = false) String fuelType,
                                     @RequestParam(value = "body_type", required = false) List<String> bodyType,
                                     @RequestParam(value = "gear_box", required = false) String gearBox,
                                     @PageableDefault(size = Integer.MAX_VALUE) Pageable pageable) {

        SearchFilters searchFilters = new SearchFilters(priceFrom, priceTo, mileageFrom, mileageTo, fuelType, bodyType, gearBox);
        return searchService.getListings(searchFilters, pageable);
    }

    @GetMapping(path = "/{make}")
    public List<Listing> getListingsForMake(@PathVariable(name = "make") String make,
                                            @RequestParam(value = "price_from", required = false) @Min(0) Integer priceFrom,
                                            @RequestParam(value = "price_to", required = false) @Min(0) Integer priceTo,
                                            @RequestParam(value = "mileage_from", required = false) @Min(0) Integer mileageFrom,
                                            @RequestParam(value = "mileage_to", required = false) @Min(0) Integer mileageTo,
                                            @RequestParam(value = "fuel_type", required = false) String fuelType,
                                            @RequestParam(value = "body_type", required = false) List<String> bodyType,
                                            @RequestParam(value = "gear_box", required = false) String gearBox,
                                            @PageableDefault(size = Integer.MAX_VALUE) Pageable pageable) {

        SearchFilters searchFilters = new SearchFilters(priceFrom, priceTo, mileageFrom, mileageTo, fuelType, bodyType, gearBox);
        return searchService.getListings(make, searchFilters, pageable);
    }

    @GetMapping(path = "/{make}/{model}")
    public List<Listing> getListingsForMakeAndModel(@PathVariable(name = "make") String make,
                                                    @PathVariable(name = "model") String model,
                                                    @RequestParam(value = "price_from", required = false) @Min(0) Integer priceFrom,
                                                    @RequestParam(value = "price_to", required = false) @Min(0) Integer priceTo,
                                                    @RequestParam(value = "mileage_from", required = false) @Min(0) Integer mileageFrom,
                                                    @RequestParam(value = "mileage_to", required = false) @Min(0) Integer mileageTo,
                                                    @RequestParam(value = "fuel_type", required = false) String fuelType,
                                                    @RequestParam(value = "body_type", required = false) List<String> bodyType,
                                                    @RequestParam(value = "gear_box", required = false) String gearBox,
                                                    @PageableDefault(size = Integer.MAX_VALUE) Pageable pageable) {

        SearchFilters searchFilters = new SearchFilters(priceFrom, priceTo, mileageFrom, mileageTo, fuelType, bodyType, gearBox);
        return searchService.getListings(make, model, searchFilters, pageable);
    }

    @GetMapping(path = "/{make}/{model}/{year}")
    public List<Listing> getListingsForMakeAndModel(@PathVariable(name = "make") String make,
                                                    @PathVariable(name = "model") String model,
                                                    @PathVariable(name = "year") Integer year,
                                                    @RequestParam(value = "price_from", required = false) @Min(0) Integer priceFrom,
                                                    @RequestParam(value = "price_to", required = false) @Min(0) Integer priceTo,
                                                    @RequestParam(value = "mileage_from", required = false) @Min(0) Integer mileageFrom,
                                                    @RequestParam(value = "mileage_to", required = false) @Min(0) Integer mileageTo,
                                                    @RequestParam(value = "fuel_type", required = false) String fuelType,
                                                    @RequestParam(value = "body_type", required = false) List<String> bodyType,
                                                    @RequestParam(value = "gear_box", required = false) String gearBox,
                                                    @PageableDefault(size = Integer.MAX_VALUE) Pageable pageable) {

        SearchFilters searchFilters = new SearchFilters(priceFrom, priceTo, mileageFrom, mileageTo, fuelType, bodyType, gearBox);
        return searchService.getListings(make, model, year, searchFilters, pageable);
    }
}
