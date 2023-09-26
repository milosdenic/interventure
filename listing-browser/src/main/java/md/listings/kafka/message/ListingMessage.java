package md.listings.kafka.message;

import md.listings.kafka.message.type.UpdateType;

import java.util.Objects;

public class ListingMessage {

    private UpdateType updateType;
    private String id;
    private Long userId;
    private String make;
    private String model;
    private Integer year;
    private Integer mileage;
    private Integer power;
    private String fuelType;
    private String gearBox;
    private String bodyType;
    private Integer price;
    private long timestamp;

    public ListingMessage() {

    }

    public UpdateType getUpdateType() {
        return updateType;
    }

    public void setUpdateType(UpdateType updateType) {
        this.updateType = updateType;
    }

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

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "ListingMessage{" +
                "updateType=" + updateType +
                ", id='" + id + '\'' +
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
                ", timestamp=" + timestamp +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListingMessage that = (ListingMessage) o;
        return timestamp == that.timestamp
                && updateType == that.updateType
                && Objects.equals(id, that.id)
                && Objects.equals(userId, that.userId)
                && Objects.equals(make, that.make)
                && Objects.equals(model, that.model)
                && Objects.equals(year, that.year)
                && Objects.equals(mileage, that.mileage)
                && Objects.equals(power, that.power)
                && Objects.equals(fuelType, that.fuelType)
                && Objects.equals(gearBox, that.gearBox)
                && Objects.equals(bodyType, that.bodyType)
                && Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(updateType, id, userId, make, model, year, mileage, power, fuelType, gearBox, bodyType, price, timestamp);
    }
}
