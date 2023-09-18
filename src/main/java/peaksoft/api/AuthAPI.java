package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import peaksoft.dto.dtoAuthentication.AdminTokenRequest;
import peaksoft.dto.dtoAuthentication.AuthenticationRequest;
import peaksoft.dto.dtoAuthentication.AuthenticationResponse;
import peaksoft.dto.dtoAuthentication.SignIn;
import peaksoft.service.AuthenticationService;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthAPI {
    private final AuthenticationService service;

    @PostMapping
    public AuthenticationResponse getAdminToken(AdminTokenRequest adminTokenResponse){
        return service.adminToken(adminTokenResponse);
    }

    @PostMapping("/singUp")
    public AuthenticationResponse signUp(@RequestBody AuthenticationRequest authenticationRequest){
        return service.signUp(authenticationRequest);
    }
    @PostMapping("/signIn")
    public AuthenticationResponse signIn(@RequestBody SignIn signIn){
        return service.signIn(signIn);
    }
}
