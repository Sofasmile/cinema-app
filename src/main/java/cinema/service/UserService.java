package cinema.service;

import cinema.model.User;

public interface UserService {
    User add(User user);

    User get(Long id);

    User findByEmail(String email);
}
