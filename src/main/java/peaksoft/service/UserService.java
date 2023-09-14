package peaksoft.service;

import peaksoft.dto.request.SignUpRequest;
import peaksoft.dto.request.UserRequest;
import peaksoft.dto.response.UserResponse;
import peaksoft.entity.User;
import peaksoft.exceptions.AlreadyExistException;

import java.util.List;

public interface UserService {
    UserResponse signUp(SignUpRequest signUpRequest) throws AlreadyExistException;
    void init();
    UserResponse createUser(UserRequest userRequest);
    UserResponse updateUser(Long userId, UserRequest userRequest);
    void deleteUser(Long userId);
    UserResponse getUserById(Long userId);
    List<UserResponse> getAllUsers();
}
