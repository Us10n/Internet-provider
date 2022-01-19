package com.epamjwd.provider.model.entity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class Tariff implements Identifiable {
    private long tariffId;
    private String name;
    private String description;
    private BigDecimal price;
    private BigDecimal newPrice;
    private TariffStatus status;
    private double internetSpeed;
    private double rating;
    private String image;
    private Optional<SpecialOffer> specialOffer;

    public Tariff() {
    }

    public Tariff(String name, String description, BigDecimal price,
                  TariffStatus status, double internetSpeed, String image,
                  double rating, SpecialOffer specialOffer) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.status = status;
        this.internetSpeed = internetSpeed;
        this.image = image;
        this.rating = rating;
        this.specialOffer = Optional.ofNullable(specialOffer);
    }

    public Tariff(long tariffId, String name, String description, BigDecimal price,
                  TariffStatus status, double internetSpeed, String image,
                  double rating, SpecialOffer specialOffer) {
        this.tariffId = tariffId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.status = status;
        this.internetSpeed = internetSpeed;
        this.image = image;
        this.rating = rating;
        this.specialOffer = Optional.ofNullable(specialOffer);
    }

    @Override
    public long getId() {
        return tariffId;
    }

    @Override
    public void setId(long id) {
        this.tariffId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public TariffStatus getStatus() {
        return status;
    }

    public void setStatus(TariffStatus status) {
        this.status = status;
    }

    public double getInternetSpeed() {
        return internetSpeed;
    }

    public void setInternetSpeed(double internetSpeed) {
        this.internetSpeed = internetSpeed;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setSpecialOffer(SpecialOffer specialOffer) {
        this.specialOffer = Optional.ofNullable(specialOffer);
    }

    public Optional<SpecialOffer> getSpecialOffer() {
        return specialOffer;
    }

    public BigDecimal getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(BigDecimal newPrice) {
        this.newPrice = newPrice;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tariff tariff = (Tariff) o;
        if (tariffId != tariff.tariffId) return false;
        if (Double.compare(tariff.internetSpeed, internetSpeed) != 0) return false;
        if (Double.compare(tariff.rating, rating) != 0) return false;
        if (name != null ? !name.equals(tariff.name) : tariff.name != null) return false;
        if (description != null ? !description.equals(tariff.description) : tariff.description != null) return false;
        if (price != null ? !price.equals(tariff.price) : tariff.price != null) return false;
        if (newPrice != null ? !newPrice.equals(tariff.newPrice) : tariff.newPrice != null) return false;
        if (status != tariff.status) return false;
        if (image != null ? !image.equals(tariff.image) : tariff.image != null) return false;
        return specialOffer.isPresent() ? specialOffer.equals(tariff.specialOffer) : !tariff.specialOffer.isPresent();
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (tariffId ^ (tariffId >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (newPrice != null ? newPrice.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        temp = Double.doubleToLongBits(internetSpeed);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(rating);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (image != null ? image.hashCode() : 0);
        result = 31 * result + (specialOffer.isPresent() ? specialOffer.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Tariff{");
        sb.append("tariffId=").append(tariffId);
        sb.append(", name='").append(name).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", price=").append(price);
        sb.append(", newPrice=").append(newPrice);
        sb.append(", status=").append(status);
        sb.append(", internetSpeed=").append(internetSpeed);
        sb.append(", rating=").append(rating);
        sb.append(", image='").append(image).append('\'');
        sb.append(", specialOffer=").append(specialOffer);
        sb.append('}');
        return sb.toString();
    }
}
