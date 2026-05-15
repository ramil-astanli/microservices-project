package az.company.profileservice.controller;

import az.company.profileservice.dto.request.ProfileRequest;
import az.company.profileservice.dto.response.ProfileResponse;
import az.company.profileservice.service.ProfileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(
        value = "/v1/profiles",
        produces = "application/vnd.profileapp+json"
)
@RequiredArgsConstructor
@Tag(name = "Profile Management", description = "User profile management APIs")
public class ProfileController {

    private final ProfileService profileService;

    @PostMapping
    @Operation(summary = "Create profile", description = "Creates a new user profile")
    public ResponseEntity<ProfileResponse> create(
            @Valid @RequestBody ProfileRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(profileService.createProfile(request));
    }

    @GetMapping
    @Operation(summary = "Get all profiles", description = "Returns all user profiles")
    public ResponseEntity<List<ProfileResponse>> getAllProfiles() {
        return ResponseEntity.ok(profileService.getAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get profile by Id", description = "Returns profile by given id")
    public ResponseEntity<ProfileResponse> getProfileById(
            @PathVariable("id") Long id) {
        return ResponseEntity.ok(profileService.getById(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update profile", description = "Updates existing user profile")
    public ResponseEntity<ProfileResponse> updateProfile(@PathVariable("id") Long id,
                                                         @Valid @RequestBody ProfileRequest request) {
        return ResponseEntity.ok(profileService.updateProfile(id, request));
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Delete profile", description = "Deletes user profile by given Id")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {
        profileService.deleteProfile(id);
        return ResponseEntity.noContent().build();
    }
}
