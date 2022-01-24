package com.epamjwd.provider.model.entity;

import java.util.Date;

public class SpecialOffer implements Identifiable {
    private long specialOfferId;
    private String title;
    private String description;
    private byte discount;
    private Date startDate;
    private Date expirationDate;
    private String image;

    public SpecialOffer() {
    }

    public SpecialOffer(String title, String description, byte discount,
                        Date startDate, Date expirationDate, String image) {
        this.title = title;
        this.description = description;
        this.discount = discount;
        this.startDate = startDate;
        this.expirationDate = expirationDate;
        this.image = image;
    }

    public SpecialOffer(String title) {
        this.title = title;
    }

    @Override
    public long getId() {
        return specialOfferId;
    }

    @Override
    public void setId(long id) {
        this.specialOfferId = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte getDiscount() {
        return discount;
    }

    public void setDiscount(byte discount) {
        this.discount = discount;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SpecialOffer that = (SpecialOffer) o;

        if (specialOfferId != that.specialOfferId) return false;
        if (discount != that.discount) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (startDate != null ? !startDate.equals(that.startDate) : that.startDate != null) return false;
        if (expirationDate != null ? !expirationDate.equals(that.expirationDate) : that.expirationDate != null)
            return false;
        return image != null ? image.equals(that.image) : that.image == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (specialOfferId ^ (specialOfferId >>> 32));
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + discount;
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (expirationDate != null ? expirationDate.hashCode() : 0);
        result = 31 * result + (image != null ? image.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SpecialOffer{");
        sb.append("specialOfferId=").append(specialOfferId);
        sb.append(", title='").append(title).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", discount=").append(discount);
        sb.append(", startDate=").append(startDate);
        sb.append(", expirationDate=").append(expirationDate);
        sb.append(", image='").append(image).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
