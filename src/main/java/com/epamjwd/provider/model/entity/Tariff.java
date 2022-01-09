package com.epamjwd.provider.model.entity;

public class Tariff implements Identifiable {
    private long tariffId;
    private String name;
    private String description;
    private double price;
    private TariffStatus status;
    private double internetSpeed;
    private String image;
    private long specialOfferId;

    public Tariff() {
    }

    public Tariff(long tariffId, String name, String description,
                  double price, TariffStatus status, double internetSpeed,
                  String image, long specialOfferId) {
        this.tariffId = tariffId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.status = status;
        this.internetSpeed = internetSpeed;
        this.image = image;
        this.specialOfferId = specialOfferId;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
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

    public long getSpecialOfferId() {
        return specialOfferId;
    }

    public void setSpecialOfferId(long specialOfferId) {
        this.specialOfferId = specialOfferId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tariff tariff = (Tariff) o;

        if (tariffId != tariff.tariffId) return false;
        if (Double.compare(tariff.price, price) != 0) return false;
        if (Double.compare(tariff.internetSpeed, internetSpeed) != 0) return false;
        if (specialOfferId != tariff.specialOfferId) return false;
        if (name != null ? !name.equals(tariff.name) : tariff.name != null) return false;
        if (description != null ? !description.equals(tariff.description) : tariff.description != null) return false;
        if (status != tariff.status) return false;
        return image != null ? image.equals(tariff.image) : tariff.image == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (tariffId ^ (tariffId >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (status != null ? status.hashCode() : 0);
        temp = Double.doubleToLongBits(internetSpeed);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (image != null ? image.hashCode() : 0);
        result = 31 * result + (int) (specialOfferId ^ (specialOfferId >>> 32));
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Tariff{");
        sb.append("tariffId=").append(tariffId);
        sb.append(", name='").append(name).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", price=").append(price);
        sb.append(", status=").append(status);
        sb.append(", internetSpeed=").append(internetSpeed);
        sb.append(", image='").append(image).append('\'');
        sb.append(", specialOfferId=").append(specialOfferId);
        sb.append('}');
        return sb.toString();
    }
}
