package az.company.feedbackservice.service;

import az.company.feedbackservice.dto.FeedbackRequest;
import az.company.feedbackservice.dto.FeedbackResponse;
import az.company.feedbackservice.entity.Feedback;
import az.company.feedbackservice.exception.FeedbackNotFoundException;
import az.company.feedbackservice.repository.FeedbackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;

    public FeedbackResponse create(FeedbackRequest request) {
        Feedback feedback = Feedback.builder()
                .message(request.message())
                .authorEmail(request.authorEmail())
                .build();
        return toResponse(feedbackRepository.save(feedback));
    }

    public List<FeedbackResponse> getAll() {
        return feedbackRepository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    private Feedback findById(Long id) {
        return feedbackRepository.findById(id)
                .orElseThrow(() -> new FeedbackNotFoundException(id));
    }

    private FeedbackResponse toResponse(Feedback feedback) {
        return FeedbackResponse.builder()
                .id(feedback.getId())
                .message(feedback.getMessage())
                .authorEmail(feedback.getAuthorEmail())
                .createdAt(feedback.getCreatedAt())
                .createdBy(feedback.getCreatedBy())
                .updatedAt(feedback.getUpdatedAt())
                .updatedBy(feedback.getUpdatedBy())
                .build();
    }
}