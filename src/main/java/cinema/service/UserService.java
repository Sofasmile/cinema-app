package cinema.service;

import java.util.Optional;

import cinema.model.User;

public interface UserService {
    User add(User user);

    Optional<User> findByEmail(String email);
}
