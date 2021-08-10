package cinema.dao;

import java.util.Optional;
import cinema.model.User;

public interface UserDao {
    User add(User user);

    Optional<User> findByEmail(String email);
}
