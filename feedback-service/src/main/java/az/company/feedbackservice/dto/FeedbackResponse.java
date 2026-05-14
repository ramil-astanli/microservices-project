package az.company.feedbackservice.dto;

import lombok.Builder;

import java.time.Instant;

@Builder
public record FeedbackResponse(
        Long id,
        String message,
        String authorEmail,
        Instant createdAt,
        String createdBy,
        Instant updatedAt,
        String updatedBy
) {}