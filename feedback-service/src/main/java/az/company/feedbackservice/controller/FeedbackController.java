package az.company.feedbackservice.controller;

import az.company.feedbackservice.dto.FeedbackRequest;
import az.company.feedbackservice.dto.FeedbackResponse;
import az.company.feedbackservice.service.FeedbackService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/feedback")
@RequiredArgsConstructor
@Tag(name = "Feedback", description = "User feedback management APIs")
public class FeedbackController {

    private final FeedbackService feedbackService;

    @PostMapping
    @Operation(summary = "Create feedback", description = "Submits a new feedback entry")
    public ResponseEntity<FeedbackResponse> create(
            @Valid @RequestBody FeedbackRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(feedbackService.create(request));
    }

    @GetMapping
    @Operation(summary = "Get all feedback", description = "Returns all feedback entries")
    public ResponseEntity<List<FeedbackResponse>> getAll() {
        return ResponseEntity.ok(feedbackService.getAll());
    }
}