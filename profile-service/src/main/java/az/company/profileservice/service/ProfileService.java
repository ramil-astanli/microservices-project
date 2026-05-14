package az.company.profileservice.service;

import az.company.profileservice.dto.request.ProfileRequest;
import az.company.profileservice.dto.response.ProfileResponse;
import az.company.profileservice.entity.Profile;
import az.company.profileservice.exception.DuplicateEmailException;
import az.company.profileservice.exception.ProfileNotFoundException;
import az.company.profileservice.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileRepository profileRepository;

    public ProfileResponse createProfile(ProfileRequest request) {
        if(profileRepository.findByEmail(request.email()).isPresent()) {
            throw new DuplicateEmailException(request.email());
        }
        Profile profile = Profile.builder()
                .name(request.name())
                .email(request.email())
                .bio(request.bio())
                .build();
        return toResponse(profileRepository.save(profile));
    }

    public List<ProfileResponse> getAll() {
        return profileRepository.findAll()
                .stream()
                .map(this :: toResponse)
                .collect(Collectors.toList());
    }

    public ProfileResponse getById(Long id) {
        return toResponse(findById(id));
    }

    public ProfileResponse updateProfile(Long id, ProfileRequest request) {
        Profile profile = findById(id);
        profile.setName(request.name());
        profile.setEmail(request.email());
        profile.setBio(request.bio());
        return toResponse(profileRepository.save(profile));
    }

    public void deleteProfile(Long id) {
        findById(id);
        profileRepository.deleteById(id);
    }

    private Profile findById(Long id) {
        return profileRepository.findById(id)
                .orElseThrow(() -> new ProfileNotFoundException(id));
    }

    private ProfileResponse toResponse(Profile profile) {
        return ProfileResponse.builder()
                .id(profile.getId())
                .name(profile.getName())
                .email(profile.getEmail())
                .bio(profile.getBio())
                .createdAt(profile.getCreatedAt())
                .createdBy(profile.getCreatedBy())
                .updatedAt(profile.getUpdatedAt())
                .updatedBy(profile.getUpdatedBy())
                .build();

    }

}
