package peaksoft.api;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.request.SignUpRequest;
import peaksoft.dto.request.UserRequest;
import peaksoft.dto.response.UserResponse;
import peaksoft.exceptions.AlreadyExistException;
import peaksoft.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserApi {

    private final UserService userService;

    @PermitAll
    @PostMapping("/signUp")
    @Operation(summary = "Sign up", description = "To sign up fill ")
    UserResponse signUp(@RequestBody SignUpRequest signUpRequest) throws AlreadyExistException {
        return userService.signUp(signUpRequest);
    }

    @PermitAll
    @GetMapping()
    List<UserResponse> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long userId) {
        UserResponse user = userService.getUserById(userId);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PermitAll
    @PostMapping("/save")
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest userRequest) {
        UserResponse createdUser = userService.createUser(userRequest);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{userId}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable Long userId, @RequestBody UserRequest userRequest) {
        UserResponse updatedUser = userService.updateUser(userId, userRequest);
        if (updatedUser != null) {
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }
}
