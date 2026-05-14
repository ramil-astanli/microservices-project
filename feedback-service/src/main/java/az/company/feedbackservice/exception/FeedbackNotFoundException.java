package az.company.feedbackservice.exception;

public class FeedbackNotFoundException extends RuntimeException {
    public FeedbackNotFoundException(Long id) {
        super("Feedback not found: " + id);
    }
}