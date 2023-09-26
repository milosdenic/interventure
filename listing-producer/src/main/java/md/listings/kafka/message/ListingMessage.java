package md.listings.kafka.message;

import java.time.Instant;

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

    public ListingMessage(UpdateType updateType, String id, Long userId) {
        this.updateType = updateType;
        this.id = id;
        this.userId = userId;
        this.timestamp = Instant.now().toEpochMilli();
    }


    public UpdateType getUpdateType() {
        return updateType;
    }

    public void setUpdateType(UpdateType type) {
        this.updateType = type;
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
}
