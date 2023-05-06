package lt.code.academy.blog.service;

import lt.code.academy.blog.dto.User;
import lt.code.academy.blog.entity.UserEntity;
import lt.code.academy.blog.exception.UserNotExistRuntimeException;
import lt.code.academy.blog.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createUser(User user) {
        userRepository.save(UserEntity.convert(user));
    }

    public Map<UUID,User> getAllUsers() {
        Map<UUID, User> usersMap = new HashMap<>();
        List<User> users = userRepository.findAll()
                .stream()
                .map(User::convert)
                .toList();

        for (User user: users) {
            usersMap.put(user.getId(), user);
        }

        return usersMap;
    }

    public User getUserById(UUID id) {
        return userRepository.findById(id)
                .map(User::convert)
                .orElseThrow(() -> new UserNotExistRuntimeException(id));
    }

    public void updateUser(User user) {
        userRepository.save(UserEntity.convert(user));
    }

    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }

}
