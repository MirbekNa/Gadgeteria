package peaksoft.service;



import peaksoft.entity.User;

import java.util.List;

public interface UserService {
    User createUser(User user);
    User updateUser(Long userId, User user);
    void deleteUser(Long userId);
    User getUserById(Long userId);
    List<User> getAllUsers();
}
