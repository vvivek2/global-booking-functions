package com.globalbooking.loginEmail.controller;

import com.globalbooking.loginEmail.dto.GoogleTokenRequest;
import com.globalbooking.loginEmail.repository.User;
import com.globalbooking.loginEmail.repository.UserRepository;
import com.globalbooking.loginEmail.service.GoogleAuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class GoogleSignIn {

    private final GoogleAuthService googleAuthService;
    private final UserRepository userRepository;

    public GoogleSignIn(GoogleAuthService googleAuthService, UserRepository userRepository) {
        this.googleAuthService = googleAuthService;
        this.userRepository = userRepository;
    }

    @PostMapping("/googleSignIn")
    public ResponseEntity<?> googleSignIn(@RequestBody GoogleTokenRequest request) {
        try {
            var payload = googleAuthService.verify(request.getIdToken());

            if (payload == null) {
                return ResponseEntity.status(401).body("Invalid Google token");
            }

            String email = payload.getEmail();
            String fullName = (String) payload.get("name");
            String picture = (String) payload.get("picture");
            String googleId = payload.getSubject(); // unique Google ID

            // Split name into first + last
            String firstName = fullName != null ? fullName.split(" ")[0] : "";
            String lastName = fullName != null && fullName.contains(" ")
                    ? fullName.substring(fullName.indexOf(" ") + 1)
                    : "";

            var userOpt = userRepository.findByEmailId(email);
            User user;

            if (userOpt.isPresent()) {
                user = userOpt.get();
            } else {
                user = new User();
                user.setEmailId(email);
                user.setFirstName(firstName);
                user.setLastName(lastName);
                user.setProfilePic(picture);
                user.setGoogleId(googleId);
                user.setAuthProvider("GOOGLE");
                userRepository.save(user);
            }

            return ResponseEntity.ok(user);

        } catch (Exception e) {
            return ResponseEntity.status(500).body("Google login failed: " + e.getMessage());
        }
    }
}

