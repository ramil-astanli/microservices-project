package az.company.profileservice.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record ProfileRequest(
        @NotBlank(message = "Name can not be null")
        @Size(min = 2,max = 100, message = "Name must be between 2 and 100 characters")
        String name,

        @NotBlank(message = "Email can not be null")
        @Email(message = "Email format is not true")
        String email,

        @Size(max = 500, message = "Bio must be less than 500 characters")
        String bio
) {}
