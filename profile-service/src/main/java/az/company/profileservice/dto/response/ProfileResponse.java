package az.company.profileservice.dto.response;

import lombok.Builder;

import java.time.Instant;
import java.time.LocalDateTime;

@Builder
public record ProfileResponse(
        Long id,
        String name,
        String email,
        String bio,
        Instant createdAt,
        String createdBy,
        Instant updatedAt,
        String updatedBy
) {}
