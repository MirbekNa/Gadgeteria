package peaksoft.service.serviceImpl;

import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import peaksoft.security.jwt.JwtService;
import peaksoft.dto.dtoAuthentication.AdminTokenRequest;
import peaksoft.dto.dtoAuthentication.AuthenticationRequest;
import peaksoft.dto.dtoAuthentication.AuthenticationResponse;
import peaksoft.dto.dtoAuthentication.SignIn;
import peaksoft.entity.Basket;
import peaksoft.entity.User;
import peaksoft.enums.Role;
import peaksoft.exceptions.NotFoundException;
import peaksoft.repository.BasketRepository;
import peaksoft.repository.UserRepository;
import peaksoft.service.AuthenticationService;

import java.time.ZonedDateTime;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final BasketRepository basketRepository;

    @Override
    public AuthenticationResponse adminToken(AdminTokenRequest adminTokenRequest) {
        User user1 = userRepository.findById(1L).orElseThrow(() -> new UsernameNotFoundException("user with email: 1 is not found!"));
        String token = jwtService.generateToken(user1);
        System.out.println(token);
        return AuthenticationResponse.builder()
                .email(user1.getEmail())
                .token(token)
                .role(user1.getRole())
                .build();
    }

    @Override
    public AuthenticationResponse signUp(AuthenticationRequest authenticationRequest) {

        User user = new User();
        user.setFirstName(authenticationRequest.firstName());
        user.setLastName(authenticationRequest.lastName());
        user.setEmail(authenticationRequest.email());
        user.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode(authenticationRequest.password()));
        user.setCreatedAt(ZonedDateTime.now());
        user.setUpdateDate(ZonedDateTime.now());
        userRepository.save(user);

        Basket basket = new Basket();
        basket.setUser(user);
        basketRepository.save(basket);

        String token = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(token)
                .role(user.getRole())
                .email(user.getEmail())
                .build();
    }

    @Override
    public AuthenticationResponse signIn(SignIn signIn) {
        User user = userRepository.getUsersByEmail(signIn.email()).orElseThrow(() -> new NotFoundException("user is not found"));
        if (signIn.email().isBlank()){
            throw new BadCredentialsException("email does not exist...");
        }
        if (!passwordEncoder.matches(signIn.password(), user.getPassword())){
            throw new BadCredentialsException("Incorrect password");
        }
        String token = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(token)
                .role(user.getRole())
                .email(user.getEmail())
                .build();
    }
    @PostConstruct
    public  void init(){
        User user = new User();
        user.setFirstName("Sayan");
        user.setLastName("Super");
        user.setPassword(passwordEncoder.encode("qwerty"));
        user.setEmail("mitokadze@gmail.com");
        user.setRole(Role.ADMIN);
        user.setCreatedAt(ZonedDateTime.now());
        user.setUpdateDate(ZonedDateTime.now());
        if (!userRepository.existsByEmail(user.getEmail())){
            userRepository.save(user);
        }
    }
}
