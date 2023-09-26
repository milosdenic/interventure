package md.listings.service.model;

import java.util.List;
import java.util.Objects;

public class SearchFilters {

    private final Integer priceFrom;
    private final Integer priceTo;
    private final Integer mileageFrom;
    private final Integer mileageTo;
    private final String fuelType;
    private final List<String> bodyType;
    private final String gearBox;

    public SearchFilters(Integer priceFrom, Integer priceTo, Integer mileageFrom, Integer mileageTo, String fuelType,
                         List<String> bodyType, String gearBox) {
        this.priceFrom = priceFrom;
        this.priceTo = priceTo;
        this.mileageFrom = mileageFrom;
        this.mileageTo = mileageTo;
        this.fuelType = fuelType;
        this.bodyType = bodyType;
        this.gearBox = gearBox;
    }

    public Integer getPriceFrom() {
        return priceFrom;
    }

    public Integer getPriceTo() {
        return priceTo;
    }

    public Integer getMileageFrom() {
        return mileageFrom;
    }

    public Integer getMileageTo() {
        return mileageTo;
    }

    public String getFuelType() {
        return fuelType;
    }

    public List<String> getBodyType() {
        return bodyType;
    }

    public String getGearBox() {
        return gearBox;
    }

    @Override
    public String toString() {
        return "SearchParams{" +
                "priceFrom=" + priceFrom +
                ", priceTo=" + priceTo +
                ", mileageFrom=" + mileageFrom +
                ", mileageTo=" + mileageTo +
                ", fuelType=" + fuelType +
                ", bodyType=" + bodyType +
                ", gearBox='" + gearBox + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SearchFilters that = (SearchFilters) o;
        return Objects.equals(priceFrom, that.priceFrom)
                && Objects.equals(priceTo, that.priceTo)
                && Objects.equals(mileageFrom, that.mileageFrom)
                && Objects.equals(mileageTo, that.mileageTo)
                && Objects.equals(fuelType, that.fuelType)
                && Objects.equals(bodyType, that.bodyType)
                && Objects.equals(gearBox, that.gearBox);
    }

    @Override
    public int hashCode() {
        return Objects.hash(priceFrom, priceTo, mileageFrom, mileageTo, fuelType, bodyType, gearBox);
    }
}
