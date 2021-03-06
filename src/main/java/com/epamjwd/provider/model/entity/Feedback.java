package com.epamjwd.provider.model.entity;

public class Feedback implements Identifiable {
    private long feedbackId;
    private int rating;
    private String feedbackBody;
    private String authorName;
    private long authorId;
    private long tariffId;

    public Feedback() {
    }

    public Feedback(int rating, String feedbackBody, String authorName, long authorId, long tariffId) {
        this.rating = rating;
        this.feedbackBody = feedbackBody;
        this.authorName = authorName;
        this.authorId = authorId;
        this.tariffId = tariffId;
    }

    public Feedback(long feedbackId, int rating, String feedbackBody,
                    long feedbackAuthorId, long tariffId, String authorName) {
        this.feedbackId = feedbackId;
        this.rating = rating;
        this.feedbackBody = feedbackBody;
        this.authorId = feedbackAuthorId;
        this.tariffId = tariffId;
        this.authorName = authorName;
    }

    public Feedback(int rating, String feedbackBody, Long tariffId, Long authorId) {
        this.rating = rating;
        this.feedbackBody = feedbackBody;
        this.tariffId = tariffId;
        this.authorId = authorId;
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

    public long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(long feedbackAuthorId) {
        this.authorId = feedbackAuthorId;
    }

    public long getTariffId() {
        return tariffId;
    }

    public void setTariffId(long tariffId) {
        this.tariffId = tariffId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Feedback feedback = (Feedback) o;

        if (feedbackId != feedback.feedbackId) return false;
        if (rating != feedback.rating) return false;
        if (authorId != feedback.authorId) return false;
        if (tariffId != feedback.tariffId) return false;
        if (feedbackBody != null ? !feedbackBody.equals(feedback.feedbackBody) : feedback.feedbackBody != null)
            return false;
        return authorName != null ? authorName.equals(feedback.authorName) : feedback.authorName == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (feedbackId ^ (feedbackId >>> 32));
        result = 31 * result + rating;
        result = 31 * result + (feedbackBody != null ? feedbackBody.hashCode() : 0);
        result = 31 * result + (authorName != null ? authorName.hashCode() : 0);
        result = 31 * result + (int) (authorId ^ (authorId >>> 32));
        result = 31 * result + (int) (tariffId ^ (tariffId >>> 32));
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Feedback{");
        sb.append("feedbackId=").append(feedbackId);
        sb.append(", rating=").append(rating);
        sb.append(", feedbackBody='").append(feedbackBody).append('\'');
        sb.append(", authorName=").append(authorName);
        sb.append(", authorId=").append(authorId);
        sb.append(", tariffId=").append(tariffId);
        sb.append('}');
        return sb.toString();
    }
}
