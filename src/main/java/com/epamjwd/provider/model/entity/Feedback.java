package com.epamjwd.provider.model.entity;

public class Feedback implements Identifiable {
    private long feedbackId;
    private int rating;
    private String feedbackBody;
    private User feedbackAuthor; //key userId
    private Tariff tariff; //key tariffId

    public Feedback() {
    }

    public Feedback(long feedbackId, int rating, String feedbackBody, User feedbackAuthor, Tariff tariff) {
        this.feedbackId = feedbackId;
        this.rating = rating;
        this.feedbackBody = feedbackBody;
        this.feedbackAuthor = feedbackAuthor;
        this.tariff = tariff;
    }

    @Override
    public long getId() {
        return feedbackId;
    }

    @Override
    public void setId(long id) {
        this.feedbackId = id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getFeedbackBody() {
        return feedbackBody;
    }

    public void setFeedbackBody(String feedbackBody) {
        this.feedbackBody = feedbackBody;
    }

    public User getFeedbackAuthor() {
        return feedbackAuthor;
    }

    public void setFeedbackAuthor(User feedbackAuthor) {
        this.feedbackAuthor = feedbackAuthor;
    }

    public Tariff getTariff() {
        return tariff;
    }

    public void setTariff(Tariff tariff) {
        this.tariff = tariff;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Feedback feedback = (Feedback) o;

        if (feedbackId != feedback.feedbackId) return false;
        if (rating != feedback.rating) return false;
        if (feedbackBody != null ? !feedbackBody.equals(feedback.feedbackBody) : feedback.feedbackBody != null)
            return false;
        if (feedbackAuthor != null ? !feedbackAuthor.equals(feedback.feedbackAuthor) : feedback.feedbackAuthor != null)
            return false;
        return tariff != null ? tariff.equals(feedback.tariff) : feedback.tariff == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (feedbackId ^ (feedbackId >>> 32));
        result = 31 * result + rating;
        result = 31 * result + (feedbackBody != null ? feedbackBody.hashCode() : 0);
        result = 31 * result + (feedbackAuthor != null ? feedbackAuthor.hashCode() : 0);
        result = 31 * result + (tariff != null ? tariff.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Feedback{");
        sb.append("feedbackId=").append(feedbackId);
        sb.append(", rating=").append(rating);
        sb.append(", feedbackBody='").append(feedbackBody).append('\'');
        sb.append(", feedbackAuthor=").append(feedbackAuthor);
        sb.append(", tariff=").append(tariff);
        sb.append('}');
        return sb.toString();
    }
}
