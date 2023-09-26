package md.listings.repository.document;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.Instant;
import java.util.Objects;

@Document(indexName = "listing")
public class Listing {

    @Id
    private String id;

    @Field(type = FieldType.Keyword, name = "userId ")
    private Long userId;

    @Field(type = FieldType.Keyword, name = "make")
    private String make;

    @Field(type = FieldType.Keyword, name = "model")
    private String model;

    @Field(type = FieldType.Integer, name = "year")
    private Integer year;

    @Field(type = FieldType.Integer, name = "mileage")
    private Integer mileage;

    @Field(type = FieldType.Integer, name = "power")
    private Integer power;

    @Field(type = FieldType.Text, name = "fuelType")
    private String fuelType;

    @Field(type = FieldType.Text, name = "gearBox")
    private String gearBox;

    @Field(type = FieldType.Text, name = "bodyType")
    private String bodyType;

    @Field(type = FieldType.Integer, name = "price")
    private Integer price;

    @Field(type = FieldType.Date, name = "created", format = DateFormat.date_time, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="yyyy-MM-dd'T'HH:mm:ss.SSSZZ", timezone = "UTC")
    private Instant created;

    @Field(type = FieldType.Date, name = "updated", format = DateFormat.date_time, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="yyyy-MM-dd'T'HH:mm:ss.SSSZZ", timezone = "UTC")
    private Instant updated;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMileage() {
        return mileage;
    }

    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }

    public Integer getPower() {
        return power;
    }

    public void setPower(Integer power) {
        this.power = power;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getGearBox() {
        return gearBox;
    }

    public void setGearBox(String gearBox) {
        this.gearBox = gearBox;
    }

    public String getBodyType() {
        return bodyType;
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Instant getCreated() {
        return created;
    }

    public void setCreated(Instant created) {
        this.created = created;
    }

    public Instant getUpdated() {
        return updated;
    }

    public void setUpdated(Instant updated) {
        this.updated = updated;
    }

    @Override
    public String toString() {
        return "Listing{" +
                "id='" + id + '\'' +
                ", userId=" + userId +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                ", mileage=" + mileage +
                ", power=" + power +
                ", fuelType='" + fuelType + '\'' +
                ", gearBox='" + gearBox + '\'' +
                ", bodyType='" + bodyType + '\'' +
                ", price=" + price +
                ", created=" + created +
                ", updated=" + updated +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Listing listing = (Listing) o;
        return Objects.equals(id, listing.id)
                && Objects.equals(userId, listing.userId)
                && Objects.equals(make, listing.make)
                && Objects.equals(model, listing.model)
                && Objects.equals(year, listing.year)
                && Objects.equals(mileage, listing.mileage)
                && Objects.equals(power, listing.power)
                && Objects.equals(fuelType, listing.fuelType)
                && Objects.equals(gearBox, listing.gearBox)
                && Objects.equals(bodyType, listing.bodyType)
                && Objects.equals(price, listing.price)
                && Objects.equals(created, listing.created)
                && Objects.equals(updated, listing.updated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, make, model, year, mileage, power, fuelType, gearBox, bodyType, price, created, updated);
    }
}
