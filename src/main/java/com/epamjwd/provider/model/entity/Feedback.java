package com.epamjwd.provider.model.entity;

public class Feedback implements Identifiable {
    private long feedbackId;
    private int rating;
    private String feedbackBody;
    private long userId;
    private long tariffPlanId;

    public Feedback() {
    }

    public Feedback(long id, int rating, String feedbackBody, long userId, long tariffPlanId) {
        this.feedbackId=id;
        this.rating = rating;
        this.feedbackBody = feedbackBody;
        this.userId = userId;
        this.tariffPlanId = tariffPlanId;
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

    public void setFeedbackBody(String feedBackBody) {
        this.feedbackBody = feedBackBody;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getTariffPlanId() {
        return tariffPlanId;
    }

    public void setTariffPlanId(long tariffPlanId) {
        this.tariffPlanId = tariffPlanId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Feedback feedback = (Feedback) o;

        if (feedbackId != feedback.feedbackId) return false;
        if (rating != feedback.rating) return false;
        if (userId != feedback.userId) return false;
        if (tariffPlanId != feedback.tariffPlanId) return false;
        return feedbackBody != null ? feedbackBody.equals(feedback.feedbackBody) : feedback.feedbackBody == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (feedbackId ^ (feedbackId >>> 32));
        result = 31 * result + rating;
        result = 31 * result + (feedbackBody != null ? feedbackBody.hashCode() : 0);
        result = 31 * result + (int) (userId ^ (userId >>> 32));
        result = 31 * result + (int) (tariffPlanId ^ (tariffPlanId >>> 32));
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Feedback{");
        sb.append("feedbackId=").append(feedbackId);
        sb.append(", rating=").append(rating);
        sb.append(", feedbackBody='").append(feedbackBody).append('\'');
        sb.append(", userId=").append(userId);
        sb.append(", tariffPlanId=").append(tariffPlanId);
        sb.append('}');
        return sb.toString();
    }
}
