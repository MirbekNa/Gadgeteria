package peaksoft.service.impl;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import peaksoft.dto.request.SignUpRequest;
import peaksoft.dto.request.UserRequest;
import peaksoft.dto.response.UserResponse;
import peaksoft.entity.User;
import peaksoft.enums.Role;
import peaksoft.exceptions.AlreadyExistException;
import peaksoft.repository.UserRepository;
import peaksoft.security.jwt.JwtService;
import peaksoft.service.UserService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    @Override
    public UserResponse signUp(SignUpRequest signUpRequest) throws AlreadyExistException {
        if (userRepository.existsByEmail(signUpRequest.email())) {
            throw new AlreadyExistException("User with email:" + signUpRequest.email() + " already exists !");
        }
        User user = User.builder().email(signUpRequest.email()).password(passwordEncoder.encode(signUpRequest.password())).role(signUpRequest.role()).build();
        userRepository.save(user);
        String token = jwtService.generateToken((UserDetails) user);
        return UserResponse.builder().token(token).email(user.getEmail()).build();
    }
    @PostConstruct
    @Override
    public void init() {
        User user = new User();
        user.setRole(Role.ADMIN);
        user.setEmail("admin@gmail.com");
        user.setPassword(passwordEncoder.encode("admin"));
        if (!userRepository.existsByEmail("admin@gmail.com")) {
            userRepository.save(user);
        }
    }

    @Override
    public UserResponse createUser(UserRequest userRequest) {
        User user = new User();
        BeanUtils.copyProperties(userRequest, user);
        User savedUser = userRepository.save(user);
        UserResponse userResponse = new UserResponse();
        BeanUtils.copyProperties(savedUser, userResponse);
        return userResponse;
    }

    @Override
    public UserResponse updateUser(Long userId, UserRequest userRequest) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            BeanUtils.copyProperties(userRequest, user);
            User updatedUser = userRepository.save(user);
            UserResponse userResponse = new UserResponse();
            BeanUtils.copyProperties(updatedUser, userResponse);
            return userResponse;
        } else {
            return null;
        }
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public UserResponse getUserById(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            UserResponse userResponse = new UserResponse();
            BeanUtils.copyProperties(user, userResponse);
            return userResponse;
        } else {
            return null;
        }
    }

    @Override
    public List<UserResponse> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> {
                    UserResponse userResponse = new UserResponse();
                    BeanUtils.copyProperties(user, userResponse);
                    return userResponse;
                })
                .collect(Collectors.toList());
    }
}
