package az.company.feedbackservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record FeedbackRequest(

        @NotBlank(message = "Message can not be blank")
        @Size(max = 1000, message = "Message must be less than 1000 characters")
        String message,

        @NotBlank(message = "Author email can not be blank")
        @Email(message = "Author email format is not valid")
        String authorEmail

) {}